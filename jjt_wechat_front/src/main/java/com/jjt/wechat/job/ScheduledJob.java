package com.jjt.wechat.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ScheduledJob implements Job {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		System.err.println("这是第一个任务 is running…………………………………… ");
		//logger.debug("get accesstoken");
	}

}
