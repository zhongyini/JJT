package com.jjt.wechat.front.enums;

public enum VoucherType {
	/**
	 * 积分
	 */
	JF("积分优惠券"),
	/**
	 * 周边优惠券
	 */
	ZB("周边优惠券"),
	/**
	 * 生日优惠券
	 */
	SR("生日优惠券"),
	/**
	 * 舞台剧优惠券
	 */
	WTJ("舞台剧优惠券");

	private String type;

	private VoucherType(String type) {
		this.setType(type);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
