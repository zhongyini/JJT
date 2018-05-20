package com.jjt.wechat.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:config.properties")
public class AppConfig {

	/**
	 * 用户默认密码
	 */
	@Value("${user.default.pwd}")
	public String defaultPwd;

	/**
	 * 密码最小长度
	 */
	@Value("${user.pwd.minlength}")
	public String pwdMinLength;

	/*
	 * 密码保存天数
	 */
	@Value("${user.pwd.keep.day}")
	public int pwdKeepDay;

	/**
	 * 密码更新天数
	 */
	@Value("${user.pwd.update.keep.day}")
	public int pwdUpdateKeepDay;
	/**
	 * 用户头像图片最大单位M
	 */
	@Value("${user.image.max.size}")
	public int userImageMaxSize;
	/**
	 * 用户头像图片保存路径
	 */
	@Value("${user.image.save.path}")
	public String userImageSavePath;

	/**
	 * 图文图片保存路径
	 */
	@Value("${material.news.image.save.path}")
	public String materialNewsImageSavePath;
	/**
	 * token保存时间毫秒单位
	 */
	@Value("${app.token.active.time}")
	public int appTokenActiveTime;

	/**
	 * token 发行人
	 */
	@Value("${app.token.issuer}")
	public String appTokenIssuer;

	/**
	 * csv文件大小 单位M
	 */
	@Value("${csv.file.max.size}")
	public int csvMaxSize;

	/**
	 * csv文件名格式
	 */
	@Value("${csv.file.name.format}")
	public String csvFileNameFormat;
	
	/**
	 * 群推csv文件名格式
	 */
	@Value("${pushcsv.file.name.format}")
	public String pushcsvFileNameFormat;
	
	/**
	 * 服务变更csv文件名格式
	 */
	@Value("${modelcsv.file.name.format}")
	public String modelcsvFileNameFormat;

	/**
	 * csv文件done后缀名
	 */
	@Value("${csv.file.name.done}")
	public String csvFileNameDone;

	/**
	 * csv文件done后缀名
	 */
	@Value("${app.allow.origin}")
	public String allowOrigin;
	
	/**
	 * 错误csv文件下载路径
	 */
	@Value("${error.csv.url}")
	public String errorCsvUrl;
	
	/**
	 * 图文图片url
	 */
	@Value("${teletext.image.url}")
	public String teletextImageUrl;
}
