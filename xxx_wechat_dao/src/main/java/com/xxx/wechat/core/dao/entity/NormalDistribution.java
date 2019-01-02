package com.xxx.wechat.core.dao.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 正态分布对应结果值
 *
 */
@Table(name = "xxx_normal_nistribution")
public class NormalDistribution implements Serializable {

	private static final long serialVersionUID = -8912039186884911517L;

	/**
	 * 主键id
	 */
	@Id
	@Column(name = "id")
	private Integer id;
	
	/**
	 * 要求正态分布的数
	 */
	@Column(name = "number")
	private BigDecimal number;
	
	/**
	 * 求得的值
	 */
	@Column(name = "result")
	private BigDecimal result;
	
	/**
	 * 删除标志
	 */
	@Column(name = "delete_flag")
	private Integer deleteFlag;

	/**
	 * 创建者
	 */
	@Column(name = "update_user")
	private String updateUser;

	/**
	 * 创建时间
	 */
	@Column(name = "update_time")
	private Timestamp updateTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BigDecimal getNumber() {
		return number;
	}

	public void setNumber(BigDecimal number) {
		this.number = number;
	}

	public BigDecimal getResult() {
		return result;
	}

	public void setResult(BigDecimal result) {
		this.result = result;
	}

	public Integer getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}
	
}
