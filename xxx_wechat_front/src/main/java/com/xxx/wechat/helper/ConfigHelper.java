package com.xxx.wechat.helper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:config.properties")
public class ConfigHelper {
	
	@Value(value="${xxx.cookie}")
	public String cookieKey;
	
	@Value(value="${xxx.appid}")
	public String appId;
	
	@Value(value="${xxx.appsecret}")
	public String appSecret;
	
	@Value(value="${xxx.state}")
	public String state;

	@Value(value="${xxx.host.url}")
	public String hostUrl;
	
	@Value(value="${xxx.wechat.token}")
	public String wechatToken;
}
