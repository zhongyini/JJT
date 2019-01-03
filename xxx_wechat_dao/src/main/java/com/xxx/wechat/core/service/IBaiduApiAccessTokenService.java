package com.xxx.wechat.core.service;

import com.xxx.wechat.core.exception.AppException;

public interface IBaiduApiAccessTokenService {

	boolean getAccessToken(String appkey, String appsecret) throws AppException;
}
