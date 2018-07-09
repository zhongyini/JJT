package com.xxx.wechat.common.wechat.api.entity;

import com.alibaba.fastjson.annotation.JSONField;

public class Card extends BaseModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = -139746891978835820L;

	@JSONField(name = "card_id")
	 private String cardId;
	 
	 @JSONField(name = "begin_time")
	 private String beginTime;
	 
	 @JSONField(name = "end_time")
	 private String endTime;

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	 
}
