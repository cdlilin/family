package org.swz.com.family.repository.mybatis.plugs;

public class MySqlDialect extends Dialect{

	@Override
	public String getLimitString(String sql, int skipResults, int maxResults) {
		 
		return sql + " LIMIT " + skipResults + "," + maxResults;
	}
}