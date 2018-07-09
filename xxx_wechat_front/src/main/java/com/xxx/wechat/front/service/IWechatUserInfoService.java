package com.xxx.wechat.front.service;

import com.xxx.wechat.core.dao.entity.WechatUserInfo;
import com.xxx.wechat.core.exception.AppException;

public interface IWechatUserInfoService {
	
	WechatUserInfo findByOpenid(String openid) throws AppException;
	
	void insert(WechatUserInfo wechatUser) throws AppException;

}
