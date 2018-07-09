package com.xxx.wechat.core.dao;

import com.xxx.wechat.core.BaseDao;
import com.xxx.wechat.core.dao.entity.WechatUserAccount;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WechatUserAccountDao extends BaseDao<WechatUserAccount> {

    WechatUserAccount get(String openId);

    Integer updateAccountBalance(WechatUserAccount wechatUserAccount);

}
