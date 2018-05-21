package com.jjt.wechat.front.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jjt.wechat.core.dao.entity.User;
import com.jjt.wechat.core.service.impl.UserServiceImpl;
import com.jjt.wechat.front.dao.UserDao;
@Service("userService")
public class UserService extends UserServiceImpl {
	@Autowired
	UserDao userDao;
	
	public User findByOpenId(String openId) {
		return userDao.findByOpenId(openId);
	}

}