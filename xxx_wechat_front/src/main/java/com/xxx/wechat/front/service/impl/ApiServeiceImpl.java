package com.xxx.wechat.front.service.impl;

import java.io.IOException;

import org.springframework.stereotype.Service;

import com.xxx.wechat.common.baidu.api.AccessTokenApi;
import com.xxx.wechat.common.baidu.api.response.AccessTokenResponse;
import com.xxx.wechat.common.http.HttpResponseNullException;
import com.xxx.wechat.core.exception.AppException;
import com.xxx.wechat.front.service.IApiService;

@Service
public class ApiServeiceImpl implements IApiService {

	@Override
	public AccessTokenResponse getAccessToken(String appkey, String appsecret) throws AppException {
		AccessTokenApi accessTokenApi = new AccessTokenApi();
		try {
			AccessTokenResponse accessTokenResponse = accessTokenApi.getAccessToken(appkey, appsecret);
			return accessTokenResponse;
		} catch (IOException | HttpResponseNullException e) {
			throw new AppException(e.getMessage());
		}
	}

}
