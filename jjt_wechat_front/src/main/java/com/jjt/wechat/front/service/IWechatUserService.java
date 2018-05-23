package com.jjt.wechat.front.service;

import com.jjt.wechat.core.dao.entity.WechatUser;

public interface IWechatUserService {
	
	WechatUser findByOpenId(String openId);
	
	int insert(WechatUser wechatUser);

}
