package com.xxx.wechat.core.dao;

import com.xxx.wechat.core.BaseDao;
import com.xxx.wechat.core.dao.entity.WechatPayApiLog;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WechatPayApiLogDao extends BaseDao<WechatPayApiLog> {

    @Override
    int insert(WechatPayApiLog wechatPayApiLog);

}
