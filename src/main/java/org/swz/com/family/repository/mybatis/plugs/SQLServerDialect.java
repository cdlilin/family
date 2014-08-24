package org.swz.com.family.repository.mybatis.plugs;

public class SQLServerDialect extends Dialect{

	@Override
	public String getLimitString(String sql, int skipResults, int maxResults) {
		sql = sql.trim();  
        StringBuffer pageSql = new StringBuffer(sql.length() + 100);  
        // 其实这里还是有一点问题的，就是排序问题，指定死了，有解决的提供一下，等复习到Hibernate看看Hibernat内部是如何实现的。  
        pageSql.append("select * from(select a.*,row_number() over (order by id desc) rownum from( ");  
        pageSql.append(sql);  
        pageSql.append(") a )b where rownum> " + skipResults + " and rownum <= " + maxResults);  
        return pageSql.toString();  
	}
}