package com.xxx.wechat.front.service.impl;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xxx.wechat.common.utils.CheckUtils;
import com.xxx.wechat.common.utils.DateUtils;
import com.xxx.wechat.core.dao.WechatUserAccountDao;
import com.xxx.wechat.core.dao.WechatUserDao;
import com.xxx.wechat.core.dao.WechatUserInfoDao;
import com.xxx.wechat.core.dao.entity.WechatUser;
import com.xxx.wechat.core.dao.entity.WechatUserAccount;
import com.xxx.wechat.core.dao.entity.WechatUserInfo;
import com.xxx.wechat.core.exception.AppException;
import com.xxx.wechat.front.service.IWechatUserInfoService;

@Service
public class WechatUserInfoServiceImpl implements IWechatUserInfoService {
	
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private WechatUserInfoDao wechatUserInfoDao;
	
	@Autowired
	private WechatUserDao wechatUserDao;
	
	@Autowired
	WechatUserAccountDao wechatUserAccountDao;

	@Override
	public WechatUserInfo findByOpenid(String openid) throws AppException {
		return wechatUserInfoDao.selectByPrimaryKey(openid);
	}

	@Override
	@Transactional
	public void insert(WechatUserInfo wechatUserInfo) throws AppException {
		WechatUser wechatUser = new WechatUser();
		try {
			wechatUserInfo.setUpdatetime(DateUtils.getNowTimestamp());
			WechatUserInfo oldWechatUserInfo = findByOpenid(wechatUserInfo.getOpenid());
			if (CheckUtils.isNull(oldWechatUserInfo)) {
				wechatUserInfoDao.insert(wechatUserInfo);
			} else {
				wechatUserInfoDao.updateByPrimaryKeySelective(wechatUserInfo);
			}
			BeanUtils.copyProperties(wechatUser, wechatUserInfo);
			WechatUser oldWechatUser =  wechatUserDao.selectByPrimaryKey(wechatUser.getOpenid());
			if (CheckUtils.isNull(oldWechatUser)) {
				wechatUserDao.insert(wechatUser);
			} else {
				wechatUserDao.updateByPrimaryKeySelective(wechatUser);
			}
			WechatUserAccount wechatUserAccount = wechatUserAccountDao.get(wechatUserInfo.getOpenid());
			if (CheckUtils.isNull(wechatUserAccount)) {
				wechatUserAccount = new WechatUserAccount();
				wechatUserAccount.setBalance(0l);
				wechatUserAccount.setCreateDatetime(DateUtils.getNowTimestamp());
				wechatUserAccount.setUpdateDatetime(DateUtils.getNowTimestamp());
				wechatUserAccount.setOpenid(wechatUserInfo.getOpenid());
				if (!CheckUtils.isNullOrEmpty(wechatUserInfo.getUnionid())) {
					wechatUserAccount.setUnionid(wechatUserInfo.getUnionid());
				} else {
					wechatUserAccount.setUnionid(wechatUserInfo.getOpenid());
				}
				wechatUserAccountDao.insert(wechatUserAccount);
			}
		} catch (IllegalAccessException | InvocationTargetException e) {
			logger.error("用户信息转换失败" + e.getMessage());
			throw new AppException(e);
		}
	}

}
