package com.xxx.wechat.core.dao;

import com.xxx.wechat.core.BaseDao;
import com.xxx.wechat.core.dao.entity.WechatPayment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WechatPaymentDao extends BaseDao<WechatPayment> {

    @Override
    int insert(WechatPayment wechatPayment);

    int update(WechatPayment wechatPayment);

    /**
     * 正常的红包
     */
    List<WechatPayment> listRedPack(String openId);

    /**
     * 未领取的红包
     */
    List<WechatPayment> listUnclaimedRedPack(String openId);

}
