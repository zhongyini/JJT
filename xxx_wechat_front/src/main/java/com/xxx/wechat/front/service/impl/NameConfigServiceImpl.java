package com.xxx.wechat.front.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xxx.wechat.common.utils.DateUtils;
import com.xxx.wechat.core.dao.NameConfigDao;
import com.xxx.wechat.core.dao.entity.NameConfig;
import com.xxx.wechat.core.exception.AppException;
import com.xxx.wechat.front.service.INameConfigService;

@Service
public class NameConfigServiceImpl implements INameConfigService {

	@Autowired
	private NameConfigDao nameConfigDao;
	
	@Override
	public List<NameConfig> list() throws AppException {
		return nameConfigDao.list();
	}

	@Override
	public NameConfig insert(NameConfig nameConfig) throws AppException {
		nameConfig.setUpdateTime(DateUtils.getNowTimestamp());
		nameConfig.setUpdateUser("system");
		nameConfig.setDeleteFlag(0);
		nameConfigDao.insertNameConfig(nameConfig);
		return nameConfig;
	}

}
