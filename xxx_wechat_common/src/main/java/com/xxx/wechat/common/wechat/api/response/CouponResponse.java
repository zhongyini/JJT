package com.xxx.wechat.common.wechat.api.response;

import com.alibaba.fastjson.annotation.JSONField;
import com.xxx.wechat.common.wechat.api.entity.Card;

public class CouponResponse extends BaseResponse {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6545158072693214011L;
	
    private Card card;
    
	private String openid;
	
	@JSONField(name = "can_consume")
	private String canConsume;
	
	@JSONField(name = "user_card_status")
	private String userCardStatus;

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getCanConsume() {
		return canConsume;
	}

	public void setCanConsume(String canConsume) {
		this.canConsume = canConsume;
	}

	public String getUserCardStatus() {
		return userCardStatus;
	}

	public void setUserCardStatus(String userCardStatus) {
		this.userCardStatus = userCardStatus;
	}
	
}

