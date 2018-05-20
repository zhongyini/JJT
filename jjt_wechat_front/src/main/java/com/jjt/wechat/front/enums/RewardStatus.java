package com.jjt.wechat.front.enums;

public enum RewardStatus {

	/**
	 * 领取成功0
	 */
	ReceivedSuccess("0"),

	/**
	 * 已领取过1
	 */
	HasReceived("1"),

	/**
	 * 非在籍客户2
	 */
	CannotReceived("2"),
	/**
	 * 领取失败3
	 */
	ReceivedFail("3");
	private String type;

	private RewardStatus(String type) {
		this.setType(type);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
