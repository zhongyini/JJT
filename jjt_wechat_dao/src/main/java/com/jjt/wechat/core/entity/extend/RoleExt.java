package com.jjt.wechat.core.entity.extend;

import java.util.List;

import com.jjt.wechat.core.entity.Role;

public class RoleExt extends Role {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4747646442019188745L;

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
