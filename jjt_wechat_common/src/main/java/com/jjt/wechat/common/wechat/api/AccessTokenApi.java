package com.jjt.wechat.common.wechat.api;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jjt.wechat.common.constant.Constant;
import com.jjt.wechat.common.http.HttpRequest;
import com.jjt.wechat.common.http.HttpResponseNullException;
import com.jjt.wechat.common.utils.CheckUtils;
import com.jjt.wechat.common.utils.JsonUtils;
import com.jjt.wechat.common.wechat.api.response.AccessTokenResponse;

public class AccessTokenApi extends BaseApi{
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public AccessTokenApi() {
		
	}


	/**
	 * 获取AccessToken
	 * @param appId
	 * @param appSecret
	 * @return
	 * @throws HttpResponseNullException 
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	public AccessTokenResponse getAccessToken(String appId, String appSecret) throws ClientProtocolException, IOException, HttpResponseNullException{
		String method = "getAccessToken";
		String requestUrl = String.format(Constant.WechatUrl.GET_ACCESS_TOKEN_GET, appId, appSecret);
		
		String result = HttpRequest.httpGetRequest(requestUrl);
		
		logInfoResult(method, result);
		
		AccessTokenResponse response = JsonUtils.parseObject(result, AccessTokenResponse.class);
		// 若errcode为空（请求成功）时，返回response
		if(CheckUtils.isNullOrEmpty(response.getErrcode())){
			return response;
		}
		return null;
	}
}
