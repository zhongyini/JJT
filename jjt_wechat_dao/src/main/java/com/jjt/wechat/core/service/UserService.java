package com.jjt.wechat.core.service;

import com.jjt.wechat.core.dao.entity.User;

public interface UserService {
	
	User findByOpenId(String openId);

}
