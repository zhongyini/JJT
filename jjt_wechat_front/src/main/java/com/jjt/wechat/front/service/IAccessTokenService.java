package com.jjt.wechat.front.service;

import java.util.List;

import com.jjt.wechat.core.entity.WxToken;
import com.jjt.wechat.core.exception.AppException;

public interface IAccessTokenService {

	WxToken getAccessToken() throws AppException;

	List<WxToken> search();

//	void delete(WxToken token);
	
	int addWxToken(WxToken wxToken);
}
