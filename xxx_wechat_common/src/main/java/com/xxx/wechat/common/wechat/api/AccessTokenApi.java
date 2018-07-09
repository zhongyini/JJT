package com.xxx.wechat.common.wechat.api;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

import com.xxx.wechat.common.constant.Constant;
import com.xxx.wechat.common.http.HttpRequest;
import com.xxx.wechat.common.http.HttpResponseNullException;
import com.xxx.wechat.common.utils.CheckUtils;
import com.xxx.wechat.common.utils.JsonUtils;
import com.xxx.wechat.common.wechat.api.response.AccessTokenResponse;

public class AccessTokenApi extends BaseApi {
	
	public AccessTokenApi(String accessToken) {
		super(accessToken);
	}
	
	public AccessTokenApi() {
		super();
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
		// 若errcode为空或0（请求成功）时，返回response
		if(CheckUtils.isNullOrEmpty(response.getErrcode())){
			return response;
		}
		return null;
	}
	
	public AccessTokenResponse getAccessTokenByCode(String appId, String appSecret, String code) throws ClientProtocolException, IOException, HttpResponseNullException{
		String method = "getAccessTokenByCode";
		String requestUrl = String.format(Constant.WechatUrl.ACCESS_TOKEN_OAUTH2_GET, appId, appSecret, code);
		String result = HttpRequest.httpGetRequest(requestUrl);
		logInfoResult(method, result);
		AccessTokenResponse response = JsonUtils.parseObject(result, AccessTokenResponse.class);
		// 若errcode为空或0（请求成功）时，返回response
		if(isSuccess(response.getErrcode())){
			return response;
		}
		return null;
	}
}
