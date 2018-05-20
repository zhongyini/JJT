package com.jjt.wechat.core.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * Model class of 权限信息表.
 */
@Table(name="jjt_authority")
public class Authority implements Serializable {

	/** serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** 角色ID. */
	@Column(name="role_id")
	private String roleId;

	/** 权限代码. */
	@Column(name="authority_code")
	private String authorityCode;

	/**
	 * Constructor.
	 */
	public Authority() {
	}

	/**
	 * Set the 角色ID.
	 * 
	 * @param roleId
	 *            角色ID
	 */
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	/**
	 * Get the 角色ID.
	 * 
	 * @return 角色ID
	 */
	public String getRoleId() {
		return this.roleId;
	}

	/**
	 * Set the 权限代码.
	 * 
	 * @param authorityCode
	 *            权限代码
	 */
	public void setAuthorityCode(String authorityCode) {
		this.authorityCode = authorityCode;
	}

	/**
	 * Get the 权限代码.
	 * 
	 * @return 权限代码
	 */
	public String getAuthorityCode() {
		return this.authorityCode;
	}


}
