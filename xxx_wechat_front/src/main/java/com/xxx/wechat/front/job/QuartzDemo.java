package com.xxx.wechat.front.job;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.xxx.wechat.common.constant.ConfigurationEnum;
import com.xxx.wechat.common.utils.CheckUtils;
import com.xxx.wechat.core.config.ConfigurationConfig;
import com.xxx.wechat.core.config.WechatTokenConfig;
import com.xxx.wechat.core.dao.entity.WechatToken;
import com.xxx.wechat.core.exception.AppException;
import com.xxx.wechat.core.service.IBaiduApiAccessTokenService;
import com.xxx.wechat.core.util.AppContextUtils;

@Component
public class QuartzDemo {
	
	//日志
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Scheduled(cron = "0 0 0/1 * * ? ") // 每一小时执行一次
	public void goWork() throws Exception {
		logger.info("每一小时执行一次的定时任务：GET accesstoken begin" + new Date());
		for (int i = 0; i < 3; i++) {
			try {
				WechatToken wechatToken = WechatTokenConfig.getInstance().getWechatTokenOnline();
				if (!CheckUtils.isNull(wechatToken)) {
					logger.info("GET accesstoken over");
					return;
				}
			} catch (Exception e) {
				logger.error("Get WechatToken Error.次数：" + i + ",错误信息："+e.getMessage());
			}
		}
	}
	
	@Scheduled(cron = "0 0 0 */1 * ?") // 每天天执行一次
	public void goBaiduAccessToken() {
		logger.info("执行定时任务，获取百度accesstoken：GET accesstoken begin" + new Date());
		String appkey = ConfigurationConfig.getInstance().getProperty(ConfigurationEnum.BAIDU_APPKEY);
		String appsecret = ConfigurationConfig.getInstance().getProperty(ConfigurationEnum.BAIDU_APPSECRET);
		if (CheckUtils.isNullOrEmpty(appkey)) {
			appkey = "1BlRbyM9ZcQuvy7FbjazsGQw";
		}
		if (CheckUtils.isNullOrEmpty(appsecret)) {
			appsecret = "tG1f474lKkVoIBESkWGMXZwX22awRfpA";
		}
		for (int i = 0; i < 3; i++) {
			try {
				IBaiduApiAccessTokenService wechatTokenService = AppContextUtils.getBean(IBaiduApiAccessTokenService.class);
				boolean success = wechatTokenService.getAccessToken(appkey, appsecret);
				if (success) {
					return;
				}
			} catch (AppException e) {
				logger.error("Get WechatToken Error.次数：" + i + ",错误信息："+e.getMessage());
			}
		}
	}
}
