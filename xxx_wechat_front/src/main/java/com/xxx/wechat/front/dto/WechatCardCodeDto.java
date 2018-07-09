package com.xxx.wechat.front.dto;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;

public class WechatCardCodeDto implements Serializable {

	private static final long serialVersionUID = 6826058127091176197L;
	
	// 经过加密的Code码
	private String encryptCode;
	
	private String code;
	
	private String cardId;
	
	private CardExt cardExt;
	
	public String getCardId() {
		return cardId;
	}
	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
	public CardExt getCardExt() {
		return cardExt;
	}
	public void setCardExt(CardExt cardExt) {
		this.cardExt = cardExt;
	}
	public String getEncryptCode() {
		return encryptCode;
	}
	public void setEncryptCode(String encryptCode) {
		this.encryptCode = encryptCode;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}

	public class CardExt {
		
		private String openid;
		private String timestamp;
		@JSONField(name = "nonce_str")
		private String nonceStr;
		@JSONField(name = "fixed_begintimestamp")
		private String fixedBegintimestamp;
		@JSONField(name = "outer_str")
		private String outerStr;
		private String signature;
		
		public String getOpenid() {
			return openid;
		}
		public void setOpenid(String openid) {
			this.openid = openid;
		}
		public String getTimestamp() {
			return timestamp;
		}
		public void setTimestamp(String timestamp) {
			this.timestamp = timestamp;
		}
		public String getNonceStr() {
			return nonceStr;
		}
		public void setNonceStr(String nonceStr) {
			this.nonceStr = nonceStr;
		}
		public String getFixedBegintimestamp() {
			return fixedBegintimestamp;
		}
		public void setFixedBegintimestamp(String fixedBegintimestamp) {
			this.fixedBegintimestamp = fixedBegintimestamp;
		}
		public String getOuterStr() {
			return outerStr;
		}
		public void setOuterStr(String outerStr) {
			this.outerStr = outerStr;
		}
		public String getSignature() {
			return signature;
		}
		public void setSignature(String signature) {
			this.signature = signature;
		}
	}
}
