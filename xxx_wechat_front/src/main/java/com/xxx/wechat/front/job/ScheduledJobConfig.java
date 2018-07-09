package com.xxx.wechat.front.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

@Configuration
@Component // 此注解必加
@EnableScheduling // 此注解必加
public class ScheduledJobConfig {
	private static final Logger LOGGER = LoggerFactory.getLogger(ScheduledJobConfig.class);

	public void sayHello() {
		LOGGER.info("Hello world, i'm the king of the world!!!");
	}
}
