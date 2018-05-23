package com.jjt.wechat.core.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

@Configuration
public class MybatisDataSourceConfiguration {

	@Value("${mybatis.typeAliasesPackage}")
	private String typeAliasesPackage;

	@Value("${mybatis.mapperLocations}")
	private String mapperLocations;

	@Value("${datasource.type}")
	private Class<? extends DataSource> dataSourceType;

	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean fb = new SqlSessionFactoryBean();
		fb.setDataSource(roundRobinDataSouceProxy());
		fb.setTypeAliasesPackage(typeAliasesPackage);// 指定基包
		fb.setMapperLocations(new PathMatchingResourcePatternResolver()
				.getResources(mapperLocations));//

		return fb.getObject();
	}

	@Bean(name = "writeDataSource")
	@ConfigurationProperties(prefix = "datasource.write")
	public DataSource writeDataSource() {
		return DataSourceBuilder.create().type(dataSourceType).build();
	}

	@Bean(name = "readDataSource")
	@Primary
	@ConfigurationProperties(prefix = "datasource.read")
	public DataSource readDataSourceOne() {
		return DataSourceBuilder.create().type(dataSourceType).build();
	}

	@Bean
	public AbstractRoutingDataSource roundRobinDataSouceProxy() {

		MyAbstractRoutingDataSource proxy = new MyAbstractRoutingDataSource();
		Map<Object, Object> targetDataSources = new HashMap<Object, Object>();
		targetDataSources
				.put(DataSourceType.write.getType(), writeDataSource());

		targetDataSources.put(DataSourceType.read.getType(),
				readDataSourceOne());

		proxy.setDefaultTargetDataSource(writeDataSource());
		proxy.setTargetDataSources(targetDataSources);
		return proxy;
	}

	/**
	 * 配置事务管理器
	 */
	@Bean
	public DataSourceTransactionManager transactionManager(
			AbstractRoutingDataSource dataSource) throws Exception {
		return new DataSourceTransactionManager(dataSource);
	}
}
