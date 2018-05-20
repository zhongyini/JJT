package com.jjt.wechat.front.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jjt.common.utils.CheckUtils;
import com.jjt.wechat.core.dao.WxTokenDao;
import com.jjt.wechat.core.entity.WxToken;
import com.jjt.wechat.front.service.IAccessTokenService;

import tk.mybatis.mapper.entity.Example;

@Service("accessTokenService")
public class AccessTokenService implements IAccessTokenService {
	
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());


	@Autowired
	WxTokenDao wxTokenDao;

	@Override
	public WxToken getAccessToken(){
		List<WxToken> list = search();
		if (!CheckUtils.isNullOrEmpty(list)) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<WxToken> search() {
		try {
			Example example = new Example(WxToken.class);
			example.setOrderByClause("CREATE_DATE desc");
			return wxTokenDao.selectByExample(example);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
		
	}

//	@Override
//	public void delete(WxToken token) {
//		wxTokenDao.delete(token);
//	}

	@Override
	public int addWxToken(WxToken wxToken) {
		return wxTokenDao.insert(wxToken);
	}

}
