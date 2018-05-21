package com.jjt.wechat.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jjt.wechat.core.dao.WechatTokenRepository;
import com.jjt.wechat.core.dao.entity.WechatToken;
import com.jjt.wechat.core.service.WechatTokenService;

@Service
public class WechatTokenServiceImpl implements WechatTokenService{
	@Autowired
	WechatTokenRepository wechatTokenRepository;

	@Override
	public List<WechatToken> findAll() {
		return wechatTokenRepository.findAll();
	}

	@Override
	public void save(WechatToken wechatToken) {
		wechatTokenRepository.save(wechatToken);
	}

	@Override
	public WechatToken findFirstByOrderByCreateDateDesc() {
		return wechatTokenRepository.findFirstByOrderByCreateDateDesc();
	}

	
}
