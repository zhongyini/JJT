package com.xxx.wechat.front.service;

import java.util.List;

import com.xxx.wechat.core.dao.entity.WechatCard;
import com.xxx.wechat.core.exception.AppException;

public interface IWechatCardService {

	int addWechatCard(WechatCard wechatCard) throws AppException;
	
	List<WechatCard> search(WechatCard wechatCard) throws AppException;
}
