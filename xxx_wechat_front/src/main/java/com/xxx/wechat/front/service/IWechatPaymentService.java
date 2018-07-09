package com.xxx.wechat.front.service;

import com.xxx.wechat.front.vo.ResultVo;

public interface IWechatPaymentService {

    /**
     * 支付
     *
     * @param openId 客户openId
     */
    ResultVo payment(String openId);

    /**
     * 支付履历
     *
     * @param openId 客户openId
     */
    ResultVo listPayment(String openId);

}
