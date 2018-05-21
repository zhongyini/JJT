package com.jjt.wechat.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jjt.wechat.core.dao.SnsTokenRepository;
import com.jjt.wechat.core.dao.entity.SnsToken;
import com.jjt.wechat.core.service.SnsTokenService;

@Service
public class SnsTokenServiceImpl implements SnsTokenService {

	@Autowired
	SnsTokenRepository snsTokenRepository;
	
	@Override
	public List<SnsToken> findAll() {
		return snsTokenRepository.findAll();
	}

	@Override
	public void save(SnsToken snsToken) {
		snsTokenRepository.save(snsToken);

	}

	@Override
	public SnsToken findFirstByOrderByCreateDateDesc() {
		return snsTokenRepository.findFirstByOrderByCreateDateDesc();
	}

}
