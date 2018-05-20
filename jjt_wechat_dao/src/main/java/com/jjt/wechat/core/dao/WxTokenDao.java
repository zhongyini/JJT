package com.jjt.wechat.core.dao;

import org.apache.ibatis.annotations.Mapper;

import com.jjt.wechat.core.BaseDao;
import com.jjt.wechat.core.entity.WxToken;

@Mapper
public interface WxTokenDao extends BaseDao<WxToken> {
}
