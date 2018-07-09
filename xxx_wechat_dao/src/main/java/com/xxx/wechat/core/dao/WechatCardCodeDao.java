package com.xxx.wechat.core.dao;

import org.apache.ibatis.annotations.Mapper;

import com.xxx.wechat.core.BaseDao;
import com.xxx.wechat.core.dao.entity.WechatCardCode;

@Mapper
public interface WechatCardCodeDao extends BaseDao<WechatCardCode>{
	
	WechatCardCode queryCode(String code);
	
	Integer selectCodeNumByRecEdOpenid(String recEdOpenid);
	
	String selectAmountCashByRecOpenid(String recOpenid);
	
	Integer selectCountNum();
}
