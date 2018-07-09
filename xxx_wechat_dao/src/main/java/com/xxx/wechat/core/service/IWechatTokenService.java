package com.xxx.wechat.core.service;

import java.util.List;

import com.xxx.wechat.core.dao.entity.WechatToken;
import com.xxx.wechat.core.exception.AppException;

public interface IWechatTokenService {

	List<WechatToken> findAll() throws AppException;
	
	WechatToken findFirstByOrderByCreateDateDesc() throws AppException;
	
	int add(WechatToken wechatToken) throws AppException;
	
	void deleteAll() throws AppException;
	
}
