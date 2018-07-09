package com.xxx.wechat.core.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class MyAbstractRoutingDataSource extends AbstractRoutingDataSource {
	
	@Override
	protected Object determineCurrentLookupKey() {
		String typeKey = DataSourceContextHolder.getJdbcType();
		if (typeKey==null) {
			return DataSourceType.write.getType();
		}
		if (typeKey.equals(DataSourceType.write.getType()))
			return DataSourceType.write.getType();
		// 读 简单负载均衡

		return DataSourceType.read.getType();
	}

}
