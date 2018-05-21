package com.jjt.wechat.common.wechat.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseApi {
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	protected String accessToken;

	public BaseApi(String accessToken) {
		super();
		this.accessToken = accessToken;
	}
	
	public BaseApi(){
		
	}

	protected void logInfoParam(String method, String param) {
		logger.info("Method is " + method + " , Param is " + param);
	}
	
	protected void logInfoResult(String method, String result) {
		logger.info("Method is " + method + " , result is " + result);
	}
}
