package com.jjt.wechat.helper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:config.properties")
public class ConfigHelper {
	
	@Value(value="${jjt.cookie}")
	public String cookieKey;
	
	@Value(value="${jjt.appid}")
	public String appId;
	
	@Value(value="${jjt.appsecret}")
	public String appSecret;
	
	@Value(value="${jjt.state}")
	public String state;

	@Value(value="${jjt.host.url}")
	public String hostUrl;
}
