package com.jjt.wechat.admin.dto;

import java.io.Serializable;

public class LoginReq implements Serializable {

	private static final long serialVersionUID = 5125455102475804055L;

	private String name;

	private String password;

	private String roleId;
	
	private Long loginTime;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Long loginTime) {
		this.loginTime = loginTime;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

}
