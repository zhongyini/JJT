package com.xxx.wechat.front.service;

import com.xxx.wechat.core.dao.entity.WechatUserAccount;
import com.xxx.wechat.core.exception.AppException;

public interface IWechatUserAccountService {

	WechatUserAccount get(String openid) throws AppException;
	
	int insert(WechatUserAccount wechatUserAccount) throws AppException;
}
