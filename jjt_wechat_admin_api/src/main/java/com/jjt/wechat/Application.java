package com.jjt.wechat;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 
 * @author sc
 * 
 */
@SuppressWarnings("deprecation")
@EnableTransactionManagement
@SpringBootApplication
@MapperScan(basePackages = "com.jjt.wechat.core.dao")
@ComponentScan("com.jjt")
public class Application extends SpringBootServletInitializer {
	private static Logger logger = LoggerFactory.getLogger(Application.class);

	@Override
	protected SpringApplicationBuilder configure(
			SpringApplicationBuilder application) {
		return application.sources(Application.class);
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(Application.class, args);
		logger.info("sdmin start");
	}

}
