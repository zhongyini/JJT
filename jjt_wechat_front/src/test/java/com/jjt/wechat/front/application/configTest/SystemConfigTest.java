package com.jjt.wechat.front.application.configTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.jjt.wechat.common.constant.Constant;
import com.jjt.wechat.core.config.SystemConfig;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SystemConfigTest {

	@Test
	
	/*
	 * 系统测试配置
	 */
	public void ConfigTest() {
		String appid = SystemConfig.getInstance().getProperty(Constant.Configuration.APPID);
		String appsecret = SystemConfig.getInstance().getProperty(Constant.Configuration.APPSECRET);
		System.out.println("Test appid : "+appid);
		System.out.println("Test appsecret :"+appsecret);
	}

}
