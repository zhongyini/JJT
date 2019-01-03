package com.xxx.wechat.core.service.impl;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xxx.wechat.common.baidu.api.AccessTokenApi;
import com.xxx.wechat.common.baidu.api.response.AccessTokenResponse;
import com.xxx.wechat.common.http.HttpResponseNullException;
import com.xxx.wechat.common.utils.DateUtils;
import com.xxx.wechat.core.dao.BaiduApiAccessTokenDao;
import com.xxx.wechat.core.dao.entity.BaiduApiAccessToken;
import com.xxx.wechat.core.exception.AppException;
import com.xxx.wechat.core.service.IBaiduApiAccessTokenService;

@Service
public class BaiduApiAccessTokenServiceImpl implements IBaiduApiAccessTokenService {

	@Autowired
	private BaiduApiAccessTokenDao baiduApiAccessTokenDao;
	
	@Override
	public boolean getAccessToken(String appkey, String appsecret) throws AppException {
		AccessTokenApi accessTokenApi = new AccessTokenApi();
		try {
			AccessTokenResponse accessTokenResponse = accessTokenApi.getAccessToken(appkey, appsecret);
			BaiduApiAccessToken baiduApiAccessToken = new BaiduApiAccessToken();
			baiduApiAccessToken.setAccessToken(accessTokenResponse.getAccess_token());
			baiduApiAccessToken.setCreate_date(DateUtils.getNowTimestamp());
			baiduApiAccessToken.setExpiresIn(accessTokenResponse.getExpires_in());
			baiduApiAccessToken.setRefreshToken(accessTokenResponse.getRefresh_token());
			baiduApiAccessToken.setScope(accessTokenResponse.getScope());
			baiduApiAccessToken.setSession_key(accessTokenResponse.getSession_key());
			baiduApiAccessToken.setSessionSecret(accessTokenResponse.getSession_secret());
			baiduApiAccessTokenDao.insert(baiduApiAccessToken);
			return true;
		} catch (IOException | HttpResponseNullException e) {
			throw new AppException(e.getMessage());
		}
	}

}
