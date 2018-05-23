package com.jjt.wechat.core.dao;

import org.apache.ibatis.annotations.Mapper;

import com.jjt.wechat.core.BaseDao;
import com.jjt.wechat.core.dao.entity.SnsToken;

@Mapper
public interface SnsTokenDao extends BaseDao<SnsToken>{
	/**
	 * 获取最新的Token
	 * @return
	 */
	SnsToken findFirstByOrderByCreateDateDesc();
}
