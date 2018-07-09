package com.xxx.wechat.front.service;

import com.xxx.wechat.core.dao.entity.WechatUser;
import com.xxx.wechat.core.exception.AppException;

public interface IWechatUserService {
	
	WechatUser findByOpenid(String openid) throws AppException;
	
	int insert(WechatUser wechatUser) throws AppException;
	
	int update(WechatUser wechatUser) throws AppException;
	
	int addShareNumber(String openid) throws AppException;
	
}
