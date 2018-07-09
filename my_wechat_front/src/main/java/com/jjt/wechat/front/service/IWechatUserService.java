package com.jjt.wechat.front.service;

import com.jjt.wechat.core.dao.entity.WechatUserInfo;

public interface IWechatUserService {
	
	WechatUserInfo findByOpenId(String openId);
	
	int insert(WechatUserInfo wechatUser);

}
