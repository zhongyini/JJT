package com.jjt.wechat.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jjt.wechat.core.dao.SnsTokenDao;
import com.jjt.wechat.core.dao.entity.SnsToken;
import com.jjt.wechat.core.service.ISnsTokenService;

@Service
public class SnsTokenServiceImpl implements ISnsTokenService {

	@Autowired
	SnsTokenDao snsTokenRepository;
	
	@Override
	public List<SnsToken> findAll() {
		return snsTokenRepository.selectAll();
	}

	@Override
	public void save(SnsToken snsToken) {
		snsTokenRepository.insert(snsToken);
	}

	@Override
	public SnsToken findFirstByOrderByCreateDateDesc() {
		return snsTokenRepository.findFirstByOrderByCreateDateDesc();
	}

}
