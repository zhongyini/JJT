package com.xxx.wechat.common.wechat.api.response;

import com.alibaba.fastjson.annotation.JSONField;

public class CardCordResponse {
	
	private String code;
	@JSONField(name = "card_id")
	private  String cardId;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getCardId() {
		return cardId;
	}
	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
}
