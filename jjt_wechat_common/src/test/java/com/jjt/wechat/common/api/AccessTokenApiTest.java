package com.jjt.wechat.common.api;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.junit.Test;

import com.jjt.wechat.common.http.HttpResponseNullException;
import com.jjt.wechat.common.wechat.api.AccessTokenApi;
import com.jjt.wechat.common.wechat.api.response.AccessTokenResponse;

public class AccessTokenApiTest {
	private static final String APPID = "wx01bec1a9056e42b9";
	
	private static final String APPSECRET = "5617b3faba4eff0065f19b95daad81a1";
	
	@Test
	/**
	 * 测试：
	 * 		获取Token测试
	 */
	public void getAccessTokenTest() throws ClientProtocolException, IOException, HttpResponseNullException{
		AccessTokenApi token = new AccessTokenApi();
		
		AccessTokenResponse response = token.getAccessToken(APPID, APPSECRET);
		System.out.println(response.toJsonString());
		
		System.out.println(response.getAccess_token());
		System.out.println(response.getExpires_in());
	}
}
