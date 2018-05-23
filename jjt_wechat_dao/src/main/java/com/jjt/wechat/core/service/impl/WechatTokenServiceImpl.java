package com.jjt.wechat.core.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jjt.wechat.common.utils.CheckUtils;
import com.jjt.wechat.core.dao.WechatTokenDao;
import com.jjt.wechat.core.dao.entity.WechatToken;
import com.jjt.wechat.core.service.IWechatTokenService;

import tk.mybatis.mapper.entity.Example;

@Service
public class WechatTokenServiceImpl implements IWechatTokenService {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	WechatTokenDao wechatTokenDao;
	
	@Override
	public List<WechatToken> findAll() {
		return wechatTokenDao.selectAll();
	}

	@Override
	public int add(WechatToken wechatToken) {
		return wechatTokenDao.insert(wechatToken);
	}

	@Override
	public WechatToken findFirstByOrderByCreateDateDesc() {
		try {
			Example example = new Example(WechatToken.class);
			example.setOrderByClause("CREATE_DATE desc");
			List<WechatToken> wechatTokenList = wechatTokenDao.selectByExample(example);
			if (!CheckUtils.isNullOrEmpty(wechatTokenList)) {
				return wechatTokenList.get(0);
			}
			return null;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
}
