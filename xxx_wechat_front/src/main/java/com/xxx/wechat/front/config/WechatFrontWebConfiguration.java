package com.xxx.wechat.front.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.xxx.wechat.front.authentication.AuthInterceptor;

@Configuration
public class WechatFrontWebConfiguration extends WebMvcConfigurerAdapter {
	
	/**
	 * 拦截器配置
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		AuthInterceptor authInterceptor = new AuthInterceptor();
		registry.addInterceptor(authInterceptor).addPathPatterns("/**");
//		registry.addInterceptor(authInterceptor).excludePathPatterns("/");
		super.addInterceptors(registry);
	}

}
