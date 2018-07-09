package com.xxx.wechat.common.wechat.api;

import java.util.HashMap;
import java.util.Map;


import com.xxx.wechat.common.utils.BeanUtils;
import com.xxx.wechat.common.utils.JsonUtils;
import com.xxx.wechat.common.wechat.api.response.BaseResponse;
import com.xxx.wechat.common.wechat.api.response.CouponResponse;

public class CouponApi extends BaseApi{
	
	public CouponApi(String accessToken) {
		super(accessToken);
	}
	/**
	 * 查询code状态接口
	 * @param code 卡券号*
	 * @param cardId 卡券ID
	 * @return
	 */
	public CouponResponse queryCode (String code,String cardId) {
		logger.debug("查询卡券状态......");
		BeanUtils.requireNonNull(code, "核销卡券号不能为空");
		String url = BASE_API_URL + "card/code/get?access_token=#";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("code", code);
		params.put("card_id", cardId);
		//设置true时，通过返回值errcode或user_card_status来判断卡券状态
		//设置false时，只能通过返回值user_card_status来判断卡券状态
		params.put("check_consume", false);
		BaseResponse response = executePost(url, JsonUtils.toJson(params));
		String resultJson = isSuccess(response.getErrcode()) ? response.getErrmsg() : response.toJsonString();
		CouponResponse cancel = JsonUtils.toBean(resultJson, CouponResponse.class);
		return cancel;
	}
	
	/**
	 * 核销code接口
	 * @param code 卡券号*
	 * @param cardId 卡券ID
	 * @return
	 */
	public CouponResponse CancelAfterVerification(String code,String cardId) {
		logger.debug("核销卡券......");
		BeanUtils.requireNonNull(code, "核销卡券号不能为空");
		String url = BASE_API_URL + "card/code/consume?access_token=#";
		Map<String, String> params = new HashMap<String, String>();
		params.put("code", code);
		params.put("card_id", cardId);
		BaseResponse response = executePost(url, JsonUtils.toJson(params));
		String resultJson = isSuccess(response.getErrcode()) ? response.getErrmsg() : response.toJsonString();
		CouponResponse cancel = JsonUtils.toBean(resultJson, CouponResponse.class);
		return cancel;
	}
	
	/**
	 * 获取用户已领取卡券接口
	 * @param openId 用户ID*
	 * @param cardId 卡券ID
	 * @return
	 */
	public CouponResponse queryUserCode(String openId,String cardId) {
		logger.debug("获取用户已领取卡券......");
		BeanUtils.requireNonNull(openId, "openId不能为空");
		String url = BASE_API_URL + "card/user/getcardlist?access_token=#";
		Map<String, String> params = new HashMap<String, String>();
		params.put("openid", openId);
		params.put("card_id", cardId);
		BaseResponse response = executePost(url, JsonUtils.toJson(params));
		String resultJson = isSuccess(response.getErrcode()) ? response.getErrmsg() : response.toJsonString();
		CouponResponse cancel = JsonUtils.toBean(resultJson, CouponResponse.class);
		return cancel;
	}
}
