package com.xxx.wechat.admin.service;

import com.xxx.wechat.common.wechat.api.response.CouponResponse;
import com.xxx.wechat.core.exception.AppException;

public interface IWechatCardCodeService {
	/**
	 * 查询本地卡券code是否存在,如果不存在则进行添加
	 * @param response
	 * @param code
	 * @throws AppException
	 */
	void queryCardCode(CouponResponse response,String code) throws AppException;
	
	/**
	 * 修改卡券核销状态及返点金额
	 * @param code
	 * @param money
	 * @return
	 * @throws AppException
	 */
	int updateCardCode(String code,String money) throws AppException;
	
	/**
	 * 添加卡号
	 * @param response
	 * @param code
	 * @return
	 * @throws AppException
	 */
	int saveCardCode(CouponResponse response,String code) throws AppException;
}
