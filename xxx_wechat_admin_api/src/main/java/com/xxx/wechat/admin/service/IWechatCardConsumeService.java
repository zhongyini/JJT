package com.xxx.wechat.admin.service;

import com.xxx.wechat.admin.dto.LoginReq;
import com.xxx.wechat.common.wechat.api.response.CouponResponse;
import com.xxx.wechat.core.exception.AppException;

public interface IWechatCardConsumeService {
	/**
	 * 添加核销履历
	 * @param appAdmin
	 * @param response
	 * @param money
	 * @param code
	 * @return
	 * @throws AppException
	 */
	int saveCardConsume(LoginReq appAdmin,CouponResponse response,String money,String code) throws AppException;
}
