package com.xxx.wechat.core.dao.entity.extend;

import com.xxx.wechat.core.dao.entity.AdminAuthority;

public class AdminAuthorityExt extends AdminAuthority {

	private static final long serialVersionUID = 1L;

	private String roleId;

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	
}
