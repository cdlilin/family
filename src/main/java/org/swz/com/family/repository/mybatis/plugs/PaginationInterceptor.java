package org.swz.com.family.repository.mybatis.plugs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.RowBounds;

@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class})})
public class PaginationInterceptor implements Interceptor {
    private static final Log logger = LogFactory.getLog(PaginationInterceptor.class);
    private static final ObjectFactory DEFAULT_OBJECT_FACTORY = new DefaultObjectFactory();
    private static final ObjectWrapperFactory DEFAULT_OBJECT_WRAPPER_FACTORY = new DefaultObjectWrapperFactory();
    private static final  String defaultDialect = "hsql"; 
    private static final String defaultPageSqlId = ".*Page$";
	public static final String DEFAULT_PAGE_PARAM_KEY = "page_param_key";
    
    public Object intercept(Invocation invocation) throws Throwable {
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
        MetaObject metaStatementHandler = MetaObject.forObject(statementHandler, DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY);
        // 分离代理对象链(由于目标类可能被多个拦截器拦截，从而形成多次代理，通过下面的两次循环
        // 可以分离出最原始的的目标类)
        while (metaStatementHandler.hasGetter("h")) {
            Object object = metaStatementHandler.getValue("h");
            metaStatementHandler = MetaObject.forObject(object, DEFAULT_OBJECT_FACTORY, 
            DEFAULT_OBJECT_WRAPPER_FACTORY);
        }
        // 分离最后一个代理对象的目标类
        while (metaStatementHandler.hasGetter("target")) {
            Object object = metaStatementHandler.getValue("target");
            metaStatementHandler = MetaObject.forObject(object, DEFAULT_OBJECT_FACTORY, 
            DEFAULT_OBJECT_WRAPPER_FACTORY);
        }
        Configuration configuration = (Configuration) metaStatementHandler.
        getValue("delegate.configuration"); 
        
        String dialectName = configuration.getVariables().getProperty("dialect");
        if (null == dialectName || "".equals(dialectName)) {
            logger.warn("Property dialect is not setted,use default 'mysql' ");
            dialectName = defaultDialect;
        } 
        
        String pageSqlId = configuration.getVariables().getProperty("pageSqlId");
        String pageParamKey = configuration.getVariables().getProperty("pageParamKey");
        if (null == pageSqlId || "".equals(pageSqlId)) {
            logger.warn("Property pageSqlId is not setted,use default '.*Page$' ");
            pageSqlId = defaultPageSqlId;
        }
        
        if (null == pageParamKey || "".equals(pageParamKey)) {
            logger.warn("Property pageSqlId is not setted,use default '.*Page$' ");
            pageParamKey = DEFAULT_PAGE_PARAM_KEY;
        }
        MappedStatement mappedStatement = (MappedStatement) 
        metaStatementHandler.getValue("delegate.mappedStatement");
        // 只重写需要分页的sql语句。通过MappedStatement的ID匹配，默认重写以Page结尾的
        //  MappedStatement的sql  
        if (mappedStatement.getId().matches(pageSqlId)) {
            BoundSql boundSql = (BoundSql) metaStatementHandler.getValue("delegate.boundSql");
            Object parameterObject = boundSql.getParameterObject(); 
            
            
            if (parameterObject == null) {
                throw new NullPointerException("parameterObject is null!");
            } else { 
                // 分页参数作为参数对象parameterObject的一个属性  
                Page page = (Page) metaStatementHandler  
                        .getValue("delegate.boundSql.parameterObject." + pageParamKey);    
                
                System.out.println("-------------111-----" + page);
                
                Dialect.Type databaseType  = null;  
				try{  
					databaseType = Dialect.Type.valueOf(dialectName.toUpperCase());  
				} catch(Exception e){  
				//ignore  
				}  
				if(databaseType == null){  
					throw new RuntimeException("the value of the dialect property in configuration.xml is not defined : " + configuration.getVariables().getProperty("dialect"));  
				}  
				Dialect dialect = null;  
				switch(databaseType){  
					case ORACLE:  
					    dialect = new OracleDialect();  
					    break;  
					case HSQL://需要实现MySQL的分页逻辑  
						dialect = new HsqlDialect(); 
					    break;   
					case MYSQL://需要实现MySQL的分页逻辑  
						dialect = new MySqlDialect(); 
					    break;  
					case SQLSERVER:
						dialect = new SQLServerDialect();
						 break;  
				} 
				
			     //获取当前sql
                String sql = boundSql.getSql();  
                // 重写sql  
                String pageSql = dialect.getLimitString(sql, page.getStartRow(), page.getPageSize());   
                metaStatementHandler.setValue("delegate.boundSql.sql", pageSql);  
                // 采用物理分页后，就不需要mybatis的内存分页了，所以重置下面的两个参数  
                metaStatementHandler.setValue("delegate.rowBounds.offset",   
                RowBounds.NO_ROW_OFFSET);  
                metaStatementHandler.setValue("delegate.rowBounds.limit", RowBounds.NO_ROW_LIMIT);  
                Connection connection = (Connection) invocation.getArgs()[0];  
                // 重设分页参数里的总页数等  
                if(page.isPageCountGetFlag()){
                	setPageParameter(sql, connection, mappedStatement, boundSql, page); 
                }
            }
        }
       
        // 将执行权交给下一个拦截器
        return invocation.proceed();
    } 
    
    /** 
     * 从数据库里查询总的记录数并计算总页数，回写进分页参数<code>PageParameter</code>,这样调用  
     * 者就可用通过 分页参数<code>PageParameter</code>获得相关信息。 
     *  
     * @param sql 
     * @param connection 
     * @param mappedStatement 
     * @param boundSql 
     * @param page 
     */  
    private void setPageParameter(String sql, Connection connection, MappedStatement mappedStatement,  
            BoundSql boundSql, Page page) {  
        // 记录总记录数  
        String countSql = "select count(0) from (" + sql + ") as total";  
        PreparedStatement countStmt = null;  
        ResultSet rs = null;  
        try {  
            countStmt = connection.prepareStatement(countSql);  
            BoundSql countBS = new BoundSql(mappedStatement.getConfiguration(), countSql,  
                    boundSql.getParameterMappings(), boundSql.getParameterObject());  
            setParameters(countStmt, mappedStatement, countBS, boundSql.getParameterObject());  
            rs = countStmt.executeQuery();  
            int totalCount = 0;  
            if (rs.next()) {  
                totalCount = rs.getInt(1);  
            }  
            page.setTotal(totalCount);  
            int totalPage = totalCount / page.getPageSize() + ((totalCount % page.getPageSize() == 0) ? 0 : 1);  
            page.setPages(totalPage);  
        } catch (SQLException e) {  
            logger.error("Ignore this exception", e);  
        } finally {  
            try {  
                rs.close();  
            } catch (SQLException e) {  
                logger.error("Ignore this exception", e);  
            }  
            try {  
                countStmt.close();  
            } catch (SQLException e) {  
                logger.error("Ignore this exception", e);  
            }  
        }  
    }  
      
    /** 
     * 对SQL参数(?)设值 
     *  
     * @param ps 
     * @param mappedStatement 
     * @param boundSql 
     * @param parameterObject 
     * @throws SQLException 
     */  
    private void setParameters(PreparedStatement ps, MappedStatement mappedStatement, BoundSql boundSql,  
            Object parameterObject) throws SQLException {  
        ParameterHandler parameterHandler = new DefaultParameterHandler(mappedStatement, parameterObject, boundSql);  
        parameterHandler.setParameters(ps);  
    }  


    public Object plugin(Object target) {
        if (target instanceof StatementHandler) {
            return Plugin.wrap(target, this);
        } else {
            return target;
        }
    }

    public void setProperties(Properties properties) {

    } 
}