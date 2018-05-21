package com.jjt.wechat.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jjt.wechat.core.dao.ConfigurationRepository;
import com.jjt.wechat.core.dao.entity.Configuration;
import com.jjt.wechat.core.service.ConfigurationService;

@Service
public class ConfigurationServiceImpl implements ConfigurationService{
	@Autowired
	ConfigurationRepository configurationRepository;
	
	public List<Configuration> findAll(){
		return configurationRepository.findAll();
	}
}
