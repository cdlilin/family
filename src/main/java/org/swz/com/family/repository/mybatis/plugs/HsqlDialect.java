package org.swz.com.family.repository.mybatis.plugs;

public class HsqlDialect extends Dialect{

	@Override
	public String getLimitString(String sql, int skipResults, int maxResults) {
		 
		return sql + " LIMIT " + maxResults + " OFFSET " + skipResults; 
	}

}
