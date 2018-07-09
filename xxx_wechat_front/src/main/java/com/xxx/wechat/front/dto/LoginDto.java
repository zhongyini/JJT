package com.xxx.wechat.front.dto;

import java.io.Serializable;

public class LoginDto implements Serializable {

	private static final long serialVersionUID = 3811900193891981955L;
	
	// 用户openid
	private String openid;
	// 推荐者openid
	private String recOpenid;
	// 卡号
	private String code;
	// 卡券id
	private  String cardId;
	// 手机号
	private  String phone;
	// 领券人数
	private  String codeNum;
	// 验证码
	private  String verifyCode;
	
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getRecOpenid() {
		return recOpenid;
	}
	public void setRecOpenid(String recOpenid) {
		this.recOpenid = recOpenid;
	}
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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getCodeNum() {
		return codeNum;
	}
	public void setCodeNum(String codeNum) {
		this.codeNum = codeNum;
	}
	public String getVerifyCode() {
		return verifyCode;
	}
	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

}
