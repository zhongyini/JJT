package com.jjt.wechat.front.service;

import com.jjt.wechat.core.dao.entity.WechatUser;

public interface IUserService {
	
	WechatUser findByOpenId(String openId);
	
	void insert(WechatUser wechatUser);

}
