package com.jjt.wechat.auth;

import org.apache.commons.httpclient.Header;

import com.alibaba.fastjson.JSONObject;

public class JjtResponseBean {
	public JSONObject resultObj;
	public Header header;
	public boolean isSuccess;
	public String message;
}
