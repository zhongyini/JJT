package com.jjt.wechat.core.service;

import java.util.List;

import com.jjt.wechat.core.dao.entity.Configuration;


public interface IConfigurationService {
	List<Configuration> findAll();
}
