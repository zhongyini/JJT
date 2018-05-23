package com.jjt.wechat.core.dao;

import org.apache.ibatis.annotations.Mapper;

import com.jjt.wechat.core.BaseDao;
import com.jjt.wechat.core.dao.entity.WechatUser;

@Mapper
public interface WechatUserDao extends BaseDao<WechatUser> {
	
	WechatUser selectByOpenId(String openId);

}
