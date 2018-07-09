package com.xxx.wechat.core.dao.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 核销履历表.
 * 
 */
@Table(name = "xxx_wechat_card_consume")
public class WechatCardConsume implements Serializable {

	/** serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** id. */
	@Id
	@Column(name = "consume_id")
	private Integer consumeId;

	/** code_id. */
	@Column(name = "code_id")
	private String codeId;

	/** admin_id. */
	@Column(name = "admin_id")
	private String adminId;

	/** money. */
	@Column(name = "money")
	private String money;
	
	/** updatetime. */
	@Column(name = "updatetime")
	private Date updatetime;

	/**
	 * Constructor.
	 */
	public WechatCardConsume() {
	}

	public Integer getConsumeId() {
		return consumeId;
	}

	public void setConsumeId(Integer consumeId) {
		this.consumeId = consumeId;
	}

	public String getCodeId() {
		return codeId;
	}

	public void setCodeId(String codeId) {
		this.codeId = codeId;
	}

	public String getAdminId() {
		return adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	public Date getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}

}
