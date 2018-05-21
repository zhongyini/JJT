package com.jjt.wechat.core.service;

import java.util.List;

import com.jjt.wechat.core.dao.entity.WechatToken;

public interface WechatTokenService {
	List<WechatToken> findAll();
	
	void save(WechatToken wechatToken);
	
	WechatToken findFirstByOrderByCreateDateDesc();
}
