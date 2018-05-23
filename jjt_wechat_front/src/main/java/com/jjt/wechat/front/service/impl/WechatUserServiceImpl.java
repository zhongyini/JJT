package com.jjt.wechat.front.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jjt.wechat.core.dao.WechatUserDao;
import com.jjt.wechat.core.dao.entity.WechatUser;
import com.jjt.wechat.front.service.IWechatUserService;

@Service
public class WechatUserServiceImpl implements IWechatUserService {
	
	@Autowired
	private WechatUserDao wechatUserDao;

	@Override
	public WechatUser findByOpenId(String openId) {
		return wechatUserDao.selectByOpenId(openId);
	}

	@Override
	public int insert(WechatUser wechatUser) {
		return wechatUserDao.insert(wechatUser);
	}
}
