package com.xxx.wechat.front.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xxx.wechat.common.constant.Constant;
import com.xxx.wechat.common.utils.DateUtils;
import com.xxx.wechat.core.dao.WechatUserDao;
import com.xxx.wechat.core.dao.entity.WechatUser;
import com.xxx.wechat.core.exception.AppException;
import com.xxx.wechat.front.service.IWechatUserService;

@Service
public class WechatUserServiceImpl implements IWechatUserService {
	
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private WechatUserDao wechatUserDao;

	@Override
	public WechatUser findByOpenid(String openid) throws AppException {
		return wechatUserDao.selectByPrimaryKey(openid);
	}

	@Override
	public int insert(WechatUser wechatUser) throws AppException {
		return wechatUserDao.insert(wechatUser);
	}

	@Override
	public int update(WechatUser wechatUser) throws AppException {
		wechatUser.setUpdatetime(DateUtils.getNowTimestamp());
		return wechatUserDao.updateByPrimaryKeySelective(wechatUser);
	}

	@Override
	public int addShareNumber(String openid) throws AppException {
		WechatUser wechatUser = findByOpenid(openid);
		wechatUser.setShareNumber(wechatUser.getShareNumber()+Constant.Num.INT_ONE);
		wechatUser.setUpdatetime(DateUtils.getNowTimestamp());
		return wechatUserDao.updateByPrimaryKeySelective(wechatUser);
	}

}
