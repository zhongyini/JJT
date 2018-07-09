package com.xxx.wechat.front.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xxx.wechat.common.utils.DateUtils;
import com.xxx.wechat.core.dao.WechatUserAccountDao;
import com.xxx.wechat.core.dao.entity.WechatUserAccount;
import com.xxx.wechat.core.exception.AppException;
import com.xxx.wechat.front.service.IWechatUserAccountService;

@Service("wechatUserAccountService")
public class WechatUserAccountServiceImpl implements IWechatUserAccountService {
	
	@Autowired
	WechatUserAccountDao wechatUserAccountDao;
	
	@Override
	public WechatUserAccount get(String openid) throws AppException {
		return wechatUserAccountDao.get(openid);
	}

	@Override
	public int insert(WechatUserAccount wechatUserAccount) throws AppException {
		wechatUserAccount.setBalance(0l);
		wechatUserAccount.setCreateDatetime(DateUtils.getNowTimestamp());
		wechatUserAccount.setUpdateDatetime(DateUtils.getNowTimestamp());
		return wechatUserAccountDao.insert(wechatUserAccount);
	}

}
