package com.xxx.wechat.front.job;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.xxx.wechat.common.utils.CheckUtils;
import com.xxx.wechat.core.config.WechatConfig;
import com.xxx.wechat.core.dao.entity.WechatToken;

@Component
public class QuartzDemo {
	
	//日志
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Scheduled(cron = "0 0 0/1 * * ? ") // 每一小时执行一次
	public void goWork() throws Exception {
		logger.info("每一小时执行一次的定时任务：GET accesstoken begin" + new Date());
		for (int i = 0; i < 3; i++) {
			try {
				WechatToken wechatToken = WechatConfig.getInstance().getWechatTokenOnline();
				if (!CheckUtils.isNull(wechatToken)) {
					logger.info("GET accesstoken over");
					return;
				}
			} catch (Exception e) {
				logger.error("Get WechatToken Error.次数：" + i + ",错误信息："+e.getMessage());
			}
		}
	}
}
