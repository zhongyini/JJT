package com.jjt.wechat.helper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:message.properties")
public class MessageHelper {
	/**
	 * 请输入用户名
	 */
	@Value("${message.info.0001}")
	public String messageInfo0001;

	/**
	 * 请输入密码
	 */
	@Value("${message.info.0002}")
	public String messageInfo0002;

	/**
	 * 用户名或密码错误
	 */
	@Value("${message.info.0003}")
	public String messageInfo0003;

	/**
	 * 请输入初始密码
	 */
	@Value("${message.info.0004}")
	public String messageInfo0004;

	/**
	 * 再次获取初始密码失败，请重新注册。
	 */
	@Value("${message.info.0005}")
	public String messageInfo0005;

	/**
	 * 初始密码获取失败，请重新获取
	 */
	@Value("${message.info.0006}")
	public String messageInfo0006;

	/**
	 * 请输入手机号
	 */
	@Value("${message.info.0007}")
	public String messageInfo0007;

	/**
	 * 请输入验证码
	 */
	@Value("${message.info.0008}")
	public String messageInfo0008;

	/**
	 * 验证码输入错误
	 */
	@Value("${message.info.0009}")
	public String messageInfo0009;

	/**
	 * 登录失败，请稍候重试
	 */
	@Value("${message.error.0001}")
	public String messageError0001;
}
