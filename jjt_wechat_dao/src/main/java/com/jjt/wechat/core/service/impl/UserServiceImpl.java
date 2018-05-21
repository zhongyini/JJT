package com.jjt.wechat.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jjt.wechat.core.dao.UserRepository;
import com.jjt.wechat.core.dao.entity.User;
import com.jjt.wechat.core.service.UserService;
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;

	@Override
	public User findByOpenId(String openId) {
		return userRepository.findByOpenId(openId);
	}

}