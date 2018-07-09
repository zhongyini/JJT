package com.xxx.wechat.front.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class ScheduledJobOne implements Job {

    @Override
    public void execute(JobExecutionContext context)
            throws JobExecutionException {
         System.err.println("这是第一个任务 is running");  
    }
}
