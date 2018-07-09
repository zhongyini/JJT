package com.xxx.wechat.core.dao;

import org.apache.ibatis.annotations.Mapper;

import com.xxx.wechat.core.BaseDao;
import com.xxx.wechat.core.dao.entity.WechatUserInfo;

@Mapper
public interface WechatUserInfoDao extends BaseDao<WechatUserInfo> {
	
	WechatUserInfo selectByOpenid(String openid);

}
