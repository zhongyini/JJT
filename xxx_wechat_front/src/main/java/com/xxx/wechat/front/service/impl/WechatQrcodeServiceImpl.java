package com.xxx.wechat.front.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xxx.wechat.core.dao.WechatQrcodeDao;
import com.xxx.wechat.core.dao.entity.WechatQrcode;
import com.xxx.wechat.core.exception.AppException;
import com.xxx.wechat.front.service.IWechatQrcodeService;

@Service("wechatQrcodeService")
public class WechatQrcodeServiceImpl implements IWechatQrcodeService {

	@Autowired
	WechatQrcodeDao wechatQrcodeDao;
	
	@Override
	public int add(WechatQrcode wechatQrcode) throws AppException {
		return wechatQrcodeDao.insert(wechatQrcode);
	}

	@Override
	public WechatQrcode selectByOpenid(String openid) throws AppException {
		return wechatQrcodeDao.selectByOpenid(openid);
	}

}
