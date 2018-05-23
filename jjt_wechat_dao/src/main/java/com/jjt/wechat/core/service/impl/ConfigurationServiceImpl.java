package com.jjt.wechat.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jjt.wechat.core.dao.ConfigurationDao;
import com.jjt.wechat.core.dao.entity.Configuration;
import com.jjt.wechat.core.service.IConfigurationService;

@Service
public class ConfigurationServiceImpl implements IConfigurationService{
	
	@Autowired
	ConfigurationDao configurationDao;
	
	public List<Configuration> findAll(){
		return configurationDao.selectAll();
	}
}
