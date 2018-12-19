package com.xxx.wechat.front.service;

import java.util.List;

import com.xxx.wechat.core.dao.entity.NameConfig;
import com.xxx.wechat.core.exception.AppException;

public interface INameConfigService {

	List<NameConfig> list() throws AppException;
	
	NameConfig insert(NameConfig nameConfig) throws AppException;
}
