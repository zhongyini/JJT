package com.jjt.wechat.admin.enums;

public enum ReplyType {
	/**
	 * 回复文本
	 */
	TEXT("1"),
	/**
	 * 回复图文
	 */
	MATERIAL("2"),
	
	/**
	 * 回复图片
	 */
	PICTURE("4");

	private String type;

	private ReplyType(String type) {
		this.setType(type);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
