package com.xxx.wechat.core.dao.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 姓名配置表
 * 
 * @author yk
 *
 */
@Table(name = "xxx_name_config")
public class NameConfig implements Serializable {

	private static final long serialVersionUID = 9052422626158410877L;

	/**
	 * 主键id
	 */
	@Id
	@Column(name = "id")
	private Integer id;

	/**
	 * 组成名字的字
	 */
	@Column(name = "word")
	private String word;

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

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
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
