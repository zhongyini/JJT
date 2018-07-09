package com.xxx.wechat.admin.enums;

public enum KeyWordExtType {
	/**
	 * 关键字-默认回复
	 */
	REPLY_KEYWORD_DEF("1"),
	/**
	 * 回复-关注-欢迎语
	 */
	REPLY_SUBSCRIBE_WELCOME("2"),
	/**
	 * 回复-关注-常见问题
	 */
	REPLY_FAQ("3"),
	/**
	 * 活动关键字-默认回复
	 */
	REPLY_ACTION_KEYWORD_DEF("3");

	private String type;

	private KeyWordExtType(String type) {
		this.setType(type);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
