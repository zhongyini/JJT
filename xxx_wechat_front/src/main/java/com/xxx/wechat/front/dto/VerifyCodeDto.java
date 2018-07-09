package com.xxx.wechat.front.dto;

import java.io.Serializable;

public class VerifyCodeDto implements Serializable {

	private static final long serialVersionUID = 3810206258523302110L;

	// 用户openid
	private String openid;
	// 手机号
	private String phoneNumber;
	// 验证码随机数
	private String randomNum;
	// 用户输入的验证码
	private String verifyCode;
	// 用户输入的验证码
	private String ifVerify;

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getRandomNum() {
		return randomNum;
	}

	public void setRandomNum(String randomNum) {
		this.randomNum = randomNum;
	}

	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

	public String getIfVerify() {
		return ifVerify;
	}

	public void setIfVerify(String ifVerify) {
		this.ifVerify = ifVerify;
	}

}
