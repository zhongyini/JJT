package com.jjt.wechat.core.dao.entity.extend;

import java.util.List;

import com.jjt.wechat.core.dao.entity.AdminRole;

public class RoleExt extends AdminRole {

	private static final long serialVersionUID = -1653575444768307783L;

	private int page = 1;

	private int count = 10;

	private List<String> permissions;

	public List<String> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<String> permissions) {
		this.permissions = permissions;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}
}
