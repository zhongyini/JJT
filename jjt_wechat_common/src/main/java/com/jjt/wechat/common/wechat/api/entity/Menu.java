package com.jjt.wechat.common.wechat.api.entity;

import java.util.List;

public class Menu extends BaseModel {

	private static final long serialVersionUID = 592454974475039393L;

	/**
	 * 一级菜单列表，最多3个
	 */
	private List<MenuButton> button;

	/**
	 * 菜单匹配规则
	 *
	 * @since 1.3.7
	 */
	private Matchrule matchrule;

	public List<MenuButton> getButton() {
		return button;
	}

	public void setButton(List<MenuButton> button) {
		this.button = button;
	}

	public Matchrule getMatchrule() {
		return matchrule;
	}

	public void setMatchrule(Matchrule matchrule) {
		this.matchrule = matchrule;
	}

}
