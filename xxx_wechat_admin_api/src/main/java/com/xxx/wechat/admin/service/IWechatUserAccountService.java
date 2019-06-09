package com.xxx.wechat.admin.service;

import com.xxx.wechat.core.exception.AppException;

public interface IWechatUserAccountService {
	/**
	 * 查询该用户是否有开通账户,如果不存在则进行添加,如果有推荐人则修改推荐人的余额
	 * @param openId
	 * @param moeny
	 * @throws AppException
	 */
	void selectUserAccount(String openId,String moeny) throws AppException;
	
	/**
	 * 添加账户
	 * @param openId
	 * @param unionId
	 * @return
	 * @throws AppException
	 */
	int saveUserAccount(String openId,String unionId) throws AppException;
	
	/**
	 * 修改推荐人余额
	 * @param openId
	 * @param money
	 * @return
	 * @throws AppException
	 */
	int updateUserAccount(String openId,Integer money) throws AppException;
}
