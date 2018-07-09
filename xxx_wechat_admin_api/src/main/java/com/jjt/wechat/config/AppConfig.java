package com.xxx.wechat.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:config.properties")
public class AppConfig {

	/**
	 * token 发行人
	 */
	@Value("${app.token.issuer}")
	public String appTokenIssuer;
	
	/**
	 * token保存时间毫秒单位
	 */
	@Value("${app.token.active.time}")
	public int appTokenActiveTime;
	
	/**
	 * 用户默认密码
	 */
	@Value("${user.default.pwd}")
	public String defaultPwd;
}
