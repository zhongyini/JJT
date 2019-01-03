package com.xxx.wechat.core.service;

import java.util.List;

import com.xxx.wechat.core.dao.entity.Configuration;
import com.xxx.wechat.core.exception.AppException;


public interface IConfigurationService {
	List<Configuration> findAll() throws AppException;
}
