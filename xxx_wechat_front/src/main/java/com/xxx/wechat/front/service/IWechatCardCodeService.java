package com.xxx.wechat.front.service;

import java.util.List;

import com.xxx.wechat.core.dao.entity.WechatCardCode;
import com.xxx.wechat.core.exception.AppException;

public interface IWechatCardCodeService {

	int addWechatCard(WechatCardCode wechatCardCode) throws AppException;
	
	List<WechatCardCode> select(WechatCardCode wechatCardCode) throws AppException;
	
	String selectAmountCashByRecOpenid(String recOpenid) throws AppException;
	
	int selectCountNum() throws AppException;
}
