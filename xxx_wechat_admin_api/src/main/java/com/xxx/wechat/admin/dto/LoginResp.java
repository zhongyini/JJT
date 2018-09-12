package com.xxx.wechat.admin.dto;

import java.util.List;

import com.xxx.wechat.core.dao.entity.AdminUser;


public class LoginResp{

	private boolean passwordOverdue= false;
	
	private AdminUser admin;

	private List<String> permissions;

	public AdminUser getAdmin() {
		return admin;
	}

	public void setAdmin(AdminUser admin) {
		this.admin = admin;
	}

	public List<String> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<String> permissions) {
		this.permissions = permissions;
	}

	public boolean isPasswordOverdue() {
		return passwordOverdue;
	}

	public void setPasswordOverdue(boolean passwordOverdue) {
		this.passwordOverdue = passwordOverdue;
	}

}
