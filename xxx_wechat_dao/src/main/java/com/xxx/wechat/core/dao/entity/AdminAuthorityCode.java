package com.xxx.wechat.core.dao.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Model class of 权限代码信息表.
 * 
 */
@Table(name = "xxx_admin_authority_code")
public class AdminAuthorityCode implements Serializable {

	/** serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** 代码. */
	@Id
	@Column(name="AUTHORITY_CODE")
	private String authorityCode;

	/** 名称. */
	@Column(name="name")
	private String name;

	/**
	 * Constructor.
	 */
	public AdminAuthorityCode() {
	}

	/**
	 * Set the 代码.
	 * 
	 * @param code
	 *            代码
	 */
	public void setAuthorityCode(String authorityCode) {
		this.authorityCode = authorityCode;
	}

	/**
	 * Get the 代码.
	 * 
	 * @return 代码
	 */
	public String getAuthorityCode() {
		return this.authorityCode;
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

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((authorityCode == null) ? 0 : authorityCode.hashCode());
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		AdminAuthorityCode other = (AdminAuthorityCode) obj;
		if (authorityCode == null) {
			if (other.authorityCode != null) {
				return false;
			}
		} else if (!authorityCode.equals(other.authorityCode)) {
			return false;
		}
		return true;
	}

}
