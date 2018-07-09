package com.xxx.wechat.front.service;

import com.xxx.wechat.core.dao.entity.WechatQrcode;
import com.xxx.wechat.core.exception.AppException;

public interface IWechatQrcodeService {

	int add(WechatQrcode wechatQrcode) throws AppException;
	
	WechatQrcode selectByOpenid(String openid) throws AppException;
}
