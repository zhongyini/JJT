package com.xxx.wechat.common.wechat.api.response;

import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

public class CardResponse extends BaseResponse {

	private static final long serialVersionUID = 7281975954396875805L;

	@JSONField(name = "card_list")
	private List<CardCordResponse> cardList;
	@JSONField(name = "has_share_card")
	private boolean hasShareCard;
	public List<CardCordResponse> getCardList() {
		return cardList;
	}
	public void setCardList(List<CardCordResponse> cardList) {
		this.cardList = cardList;
	}
	public boolean isHasShareCard() {
		return hasShareCard;
	}
	public void setHasShareCard(boolean hasShareCard) {
		this.hasShareCard = hasShareCard;
	}
	
}
