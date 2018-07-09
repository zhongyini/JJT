package com.jjt.wechat.admin.enums;

public enum AdminStatus {
	/**
	 * 正常
	 */
	NORMAL("0"),
	/**
	 * 已删除
	 */
	DELETE("1"),
	/**
	 * 过期
	 */
	OVERDUE("2"),
	/**
	 * 已冻结
	 */
	FROZEN("3");

	private String status;

	private AdminStatus(String status) {
		this.setStatus(status);
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
