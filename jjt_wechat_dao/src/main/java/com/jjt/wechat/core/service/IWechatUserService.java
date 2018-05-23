package com.jjt.wechat.core.service;

import com.jjt.wechat.core.dao.entity.WechatUser;

public interface UserService {
	
	WechatUser findByOpenId(String openId);

}
