package org.swz.com.family.repository.mybatis.plugs;

public abstract class Dialect {

	public static enum Type { 
		MYSQL, 
		ORACLE,
		HSQL,
		SQLSERVER
	}

	public abstract String getLimitString(String sql, int skipResults,
			int maxResults);

}
