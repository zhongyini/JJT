package com.jjt.wechat.core.dao;

import org.apache.ibatis.annotations.Mapper;

import com.jjt.wechat.core.BaseDao;
import com.jjt.wechat.core.dao.entity.WechatToken;

@Mapper
public interface WechatTokenDao extends BaseDao<WechatToken>{
	/**
	 * 获取最新的Token
	 * @return
	 */
	WechatToken findFirstByOrderByCreateDateDesc();
}
