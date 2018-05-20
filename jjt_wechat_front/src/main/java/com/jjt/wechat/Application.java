package com.jjt.wechat;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

/**
 * 
 * @author liub
 *
 */
@SuppressWarnings("deprecation")
@SpringBootApplication
@MapperScan(basePackages = "com.jjt.wechat.core.dao")
@ComponentScan("com.jjt")
public class Application extends SpringBootServletInitializer {


	@Override
	protected SpringApplicationBuilder configure(
			SpringApplicationBuilder application) {
	
		return application.sources(Application.class);
	}
	
	
	public static void main(String[] args) throws Exception {
		SpringApplication.run(Application.class, args);

	}


}