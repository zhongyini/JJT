package com.jjt.wechat.admin.enums;

public enum KeyWordType {
	/**
	 * 关键字
	 */
	KEYWORD("1"),
	/**
	 * 常见问题
	 */
	FAQ("2"),
	/**
	 * 活动关键字
	 */
	ACTION_KEYWORD("3");
	
	private String type;

	private KeyWordType(String type) {
		this.setType(type);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
