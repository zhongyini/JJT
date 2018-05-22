package com.jjt.wechat.front.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jjt.wechat.core.dao.UserRepository;
import com.jjt.wechat.core.dao.entity.WechatUser;
import com.jjt.wechat.front.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public WechatUser findByOpenId(String openId) {
		return userRepository.findByOpenId(openId);
	}

	@Override
	public void insert(WechatUser wechatUser) {
		wechatUser = userRepository.save(wechatUser);
	}

}
