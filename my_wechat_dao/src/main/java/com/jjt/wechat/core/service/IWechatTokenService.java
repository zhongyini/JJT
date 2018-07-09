package com.jjt.wechat.core.service;

import java.util.List;

import com.jjt.wechat.core.dao.entity.WechatToken;

public interface IWechatTokenService {

	List<WechatToken> findAll();
	
	WechatToken findFirstByOrderByCreateDateDesc();
	
	int add(WechatToken wechatToken);
	
}
