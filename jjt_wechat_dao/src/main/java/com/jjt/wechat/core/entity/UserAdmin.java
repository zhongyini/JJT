package com.jjt.wechat.core.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Table(name = "jjt_user_admin")
public class UserAdmin implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1313374562753030839L;

	public UserAdmin(){
		
	}
	public UserAdmin(String adminId,String password){
		this.adminId = adminId;
		this.password = password;
	}
	@Id
	@Column(name = "ADMIN_ID")
	private String adminId;

	@Column(name = "NAME")
	private String name;

	@Column(name = "PASSWORD")
	private String password;

	@Column(name = "ROLE_ID")
	private String roleId;

	@Column(name = "PASSWORD_OVERDUE_DATE")
	private String passwordOverdueDate;

	@Column(name = "MAIL")
	private String mail;
	
	@Column(name = "PORTRAIT")
	private String portrait;
	
	@Column(name = "DELETE_FLAG")
	private String deleteFlag;

	@Column(name = "CREATE_USER")
	private String createUser;

	@Column(name = "CREATE_DATE")
	private Timestamp createDate;

	@Column(name = "MODIFY_USER")
	private String modifyUser;

	@Column(name = "MODIFY_DATE")
	private Timestamp modifyDate;

	@Transient
	private String roleName;
	public String getAdminId() {
		return adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

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

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getPasswordOverdueDate() {
		return passwordOverdueDate;
	}

	public void setPasswordOverdueDate(String passwordOverdueDate) {
		this.passwordOverdueDate = passwordOverdueDate;
	}

	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getPortrait() {
		return portrait;
	}
	public void setPortrait(String portrait) {
		this.portrait = portrait;
	}
	public String getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
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

	public String getModifyUser() {
		return modifyUser;
	}

	public void setModifyUser(String modifyUser) {
		this.modifyUser = modifyUser;
	}

	public Timestamp getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Timestamp modifyDate) {
		this.modifyDate = modifyDate;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

}
