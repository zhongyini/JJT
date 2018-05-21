package com.jjt.wechat.common.wechat.api.entity;

import java.util.List;

public class MenuButton extends BaseModel {

	private static final long serialVersionUID = -924202118177121427L;

	/**
	 * 菜单类别
	 */
	private String type;

	/**
	 * 菜单上显示的文字
	 */
	private String name;

	/**
	 * 菜单key，当MenuType值为CLICK时用于区别菜单
	 */
	private String key;

	/**
	 * 菜单跳转的URL，当MenuType值为VIEW时用
	 */
	private String url;

	/**
	 * 菜单显示的永久素材的MaterialID,当MenuType值为media_id和view_limited时必需
	 */
	private String media_id;

	/**
	 * 二级菜单列表，每个一级菜单下最多5个
	 */
	private List<MenuButton> sub_button;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMedia_id() {
		return media_id;
	}

	public void setMedia_id(String media_id) {
		this.media_id = media_id;
	}

	public List<MenuButton> getSub_button() {
		return sub_button;
	}

	public void setSub_button(List<MenuButton> sub_button) {
		this.sub_button = sub_button;
	}

}
