package com.xxx.wechat.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xxx.wechat.core.dao.ConfigurationDao;
import com.xxx.wechat.core.dao.entity.Configuration;
import com.xxx.wechat.core.exception.AppException;
import com.xxx.wechat.core.service.IConfigurationService;

@Service
public class ConfigurationServiceImpl implements IConfigurationService{
	
	@Autowired
	ConfigurationDao configurationDao;
	
	public List<Configuration> findAll() throws AppException {
		return configurationDao.selectAll();
	}
}
