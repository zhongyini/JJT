package com.xxx.wechat.core.dao;

import org.apache.ibatis.annotations.Mapper;

import com.xxx.wechat.core.BaseDao;
import com.xxx.wechat.core.dao.entity.WechatQrcode;

@Mapper
public interface WechatQrcodeDao extends BaseDao<WechatQrcode> {

	public WechatQrcode selectByOpenid(String openid);
}
