package com.jjt.wechat.core.dao.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "jjt_admin_role")
public class AdminRole implements Serializable {

	private static final long serialVersionUID = 6687894475472223044L;

	public AdminRole() {

	}

	public AdminRole(String roleId) {
		this.roleId = roleId;
	}
	public AdminRole(String roleId,String roleName) {
		this.roleId = roleId;
		this.roleName =roleName;
	}
	@Id
	@Column(name = "ROLE_ID")
	private String roleId;

	@Column(name = "ROLE_NAME")
	private String roleName;

	@Column(name = "CREATE_USER")
	private String createUser;

	@Column(name = "CREATE_DATE")
	private Timestamp createDate;

	@Column(name = "UPDATETIME")
	private Timestamp updatetime;

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	public Timestamp getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Timestamp updatetime) {
		this.updatetime = updatetime;
	}

}
