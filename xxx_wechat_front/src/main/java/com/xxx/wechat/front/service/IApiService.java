package com.xxx.wechat.front.service;

import com.xxx.wechat.common.baidu.api.response.AccessTokenResponse;
import com.xxx.wechat.core.exception.AppException;

public interface IApiService {

	AccessTokenResponse getAccessToken(String appkey, String appsecret) throws AppException;
}
