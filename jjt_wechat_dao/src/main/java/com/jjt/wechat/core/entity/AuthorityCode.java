package com.jjt.wechat.core.entity;

import java.io.Serializable;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Model class of 权限代码信息表.
 */
@Table(name = "jjt_authority_code")
public class AuthorityCode implements Serializable {

	/** serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** 代码. */
	@Id
	private String code;

	/** 名称. */
	private String name;

	/**
	 * Constructor.
	 */
	public AuthorityCode() {
	}

	/**
	 * Set the 代码.
	 * 
	 * @param code
	 *            代码
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * Get the 代码.
	 * 
	 * @return 代码
	 */
	public String getCode() {
		return this.code;
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
		result = prime * result + ((code == null) ? 0 : code.hashCode());
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
		AuthorityCode other = (AuthorityCode) obj;
		if (code == null) {
			if (other.code != null) {
				return false;
			}
		} else if (!code.equals(other.code)) {
			return false;
		}
		return true;
	}

}
