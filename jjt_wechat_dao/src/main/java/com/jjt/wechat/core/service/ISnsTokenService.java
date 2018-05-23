package com.jjt.wechat.core.service;

import java.util.List;

import com.jjt.wechat.core.dao.entity.SnsToken;

public interface ISnsTokenService {

	List<SnsToken> findAll();
	
	void save(SnsToken snsToken);
	
	SnsToken findFirstByOrderByCreateDateDesc();
}
