package com.jjt.wechat.core.dao;

import org.apache.ibatis.annotations.Mapper;

import com.jjt.wechat.core.BaseDao;
import com.jjt.wechat.core.dao.entity.WechatUserInfo;

@Mapper
public interface WechatUserDao extends BaseDao<WechatUserInfo> {
	
	WechatUserInfo selectByOpenId(String openId);

}
