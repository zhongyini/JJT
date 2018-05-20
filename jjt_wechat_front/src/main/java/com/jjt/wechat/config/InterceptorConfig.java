package com.jjt.wechat.config;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import com.jjt.wechat.auth.AuthInterceptor;

@EnableWebMvc
@Configuration
public class InterceptorConfig extends WebMvcConfigurerAdapter {
	private static final Logger logger = LoggerFactory
			.getLogger(InterceptorConfig.class);

	@Bean
	AuthInterceptor authInterceptor() {
		return new AuthInterceptor();
	}
	
	@Bean(name="captchaProducer")
	public DefaultKaptcha getKaptchaBean(){
		DefaultKaptcha defaultKaptcha=new DefaultKaptcha();  
        Properties properties=new Properties();
        properties.setProperty("kaptcha.border", "no"/* kborder */);// 无边框
		properties.setProperty("kaptcha.session.key", "kaptcha.code");// session// key
		properties.setProperty("kaptcha.textproducer.font.color", "black");
		properties.setProperty("kaptcha.textproducer.font.size", "25");
		properties.setProperty("kaptcha.obscurificator.impl", "com.google.code.kaptcha.impl.ShadowGimpy");
		properties.setProperty("kaptcha.noise.impl", "com.google.code.kaptcha.impl.NoNoise");
		properties.setProperty("kaptcha.image.width", "90");
		properties.setProperty("kaptcha.image.height", "43");
		properties.setProperty("kaptcha.textproducer.char.length", "4");
		properties.setProperty("kaptcha.textproducer.char.space", "5");
		properties.setProperty("kaptcha.background.clear.from", "247,247,247"); // 和登录框背景颜色一致
		properties.setProperty("kaptcha.background.clear.to", "247,247,247");
		Config config = new Config(properties);
		defaultKaptcha.setConfig(config);
		return defaultKaptcha;
	}
//	@Bean   
//    public ServletRegistrationBean servletRegistrationBean() throws ServletException{  
//		 ServletRegistrationBean servlet = new ServletRegistrationBean(new KaptchaServlet(),"/static/img/kaptcha.jpg");  
//		 	
//		servlet.addInitParameter("kaptcha.border", "no"/* kborder */);// 无边框
//		servlet.addInitParameter("kaptcha.session.key", "kaptcha.code");// session// key
//		servlet.addInitParameter("kaptcha.textproducer.font.color", "black");
//		servlet.addInitParameter("kaptcha.textproducer.font.size", "25");
//		servlet.addInitParameter("kaptcha.obscurificator.impl", "com.google.code.kaptcha.impl.ShadowGimpy");
//		servlet.addInitParameter("kaptcha.noise.impl", "com.google.code.kaptcha.impl.NoNoise");
//		servlet.addInitParameter("kaptcha.image.width", "90");
//		servlet.addInitParameter("kaptcha.image.height", "43");
//		servlet.addInitParameter("kaptcha.textproducer.char.length", "4");
//		servlet.addInitParameter("kaptcha.textproducer.char.space", "5");
//		servlet.addInitParameter("kaptcha.background.clear.from", "247,247,247"); // 和登录框背景颜色一致
//		servlet.addInitParameter("kaptcha.background.clear.to", "247,247,247");
//		return servlet;
//    }  
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/static/**")
				.addResourceLocations("classpath:/static/");
		registry.addResourceHandler("/**.txt")
		.addResourceLocations("classpath:/");
		// registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
		// registry.addResourceHandler("/static/js/**").addResourceLocations("/WEB-INF/static/js/");
	}

	/***
	 * 拦截器配置
	 * 
	 * @param registry
	 * 
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		logger.info("InterceptorRegistry");
		registry.addInterceptor(authInterceptor())
				.addPathPatterns("/*", "/*/*", "/*/*/*", "/*/*/*/*")
			.excludePathPatterns("/user/doLogin","/logout", "/static/**/**");
	}

	@Bean
	public ViewResolver viewResolver() {
		logger.info("ViewResolver");
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/jsp/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}
}
