package com.jjt.wechat.front.job;

import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class QuartzDemo {
	
	@Scheduled(cron = "0 0/1 * * * ?") // 每分钟执行一次
	public void work() throws Exception {
		System.out.println("执行调度任务：" + new Date());
	}

	@Scheduled(fixedRate = 5000) // 每5秒执行一次
	public void play() throws Exception {
		System.out.println("执行Quartz定时器任务：" + new Date());
	}

	@Scheduled(cron = "0 0 0/1 * * ? ") // 每一小时执行一次
	public void goWork() throws Exception {
		System.out.println("每一小时执行一次的定时任务：" + new Date());
	}
}
