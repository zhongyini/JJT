package com.xxx.wechat.common.baidu.api;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

import com.xxx.wechat.common.baidu.api.response.AccessTokenResponse;
import com.xxx.wechat.common.http.HttpRequest;
import com.xxx.wechat.common.http.HttpResponseNullException;
import com.xxx.wechat.common.utils.CheckUtils;
import com.xxx.wechat.common.utils.JsonUtils;

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
	public AccessTokenResponse getAccessToken(String appKey, String appSecret) throws ClientProtocolException, IOException, HttpResponseNullException{
		String method = "getAccessToken";
		String requestUrl = String.format(BASE_API_URL, "client_credentials", appKey, appSecret);
		String result = HttpRequest.httpGetRequest(requestUrl);
		
		logInfoResult(method, result);
		
		AccessTokenResponse response = JsonUtils.parseObject(result, AccessTokenResponse.class);
		// 若errcode为空或0（请求成功）时，返回response
		if(CheckUtils.isNullOrEmpty(response.getError())){
			return response;
		}
		return null;
	}
}
