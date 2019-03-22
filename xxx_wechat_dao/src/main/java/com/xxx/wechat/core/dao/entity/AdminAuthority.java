package com.xxx.wechat.core.dao.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Model class of 权限代码信息表.
 * 
 */
@Table(name = "xxx_admin_authority")
public class AdminAuthority implements Serializable {

	/** serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** 代码. */
	@Id
	@Column(name="AUTHORITY_ID")
	private String authorityId;

	/** 级别. */
	@Column(name="LEVEL")
	private String level;
	
	/** 名称. */
	@Column(name="name")
	private String name;

	/**
	 * Constructor.
	 */
	public AdminAuthority() {
	}

	/**
	 * Set the 代码.
	 * 
	 * @param code
	 *            代码
	 */
	public void setAuthorityId(String authorityId) {
		this.authorityId = authorityId;
	}

	/**
	 * Get the 代码.
	 * 
	 * @return 代码
	 */
	public String getAuthorityId() {
		return this.authorityId;
	}

	/**
	 * Get the 级别.
	 * 
	 * @return 级别
	 */
	public String getLevel() {
		return level;
	}

	/**
	 * Set the 级别.
	 * 
	 * @param name
	 *            级别
	 */
	public void setLevel(String level) {
		this.level = level;
	}

	/**
	 * Set the 名称.
	 * 
	 * @param name
	 *            名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Get the 名称.
	 * 
	 * @return 名称
	 */
	public String getName() {
		return this.name;
	}

}
