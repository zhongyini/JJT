package com.jjt.wechat.core.dao.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 核销履历表.
 * 
 */
@Table(name = "jjt_wechat_card_consume")
public class WechatCardConsume implements Serializable {

	/** serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** id. */
	@Id
	@Column(name = "id")
	private Integer id;

	/** code. */
	@Column(name = "code")
	private String code;

	/** card_id. */
	@Column(name = "card_id")
	private String cardId;

	/** open_id. */
	@Column(name = "open_id")
	private String openId;

	/** admin_id. */
	@Column(name = "admin_id")
	private String adminId;

	/** updatetime. */
	@Column(name = "updatetime")
	private Date updatetime;

	/**
	 * Constructor.
	 */
	public WechatCardConsume() {
	}

	/**
	 * Set the id.
	 * 
	 * @param id
	 *            id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Get the id.
	 * 
	 * @return id
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 * Set the code.
	 * 
	 * @param code
	 *            code
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * Get the code.
	 * 
	 * @return code
	 */
	public String getCode() {
		return this.code;
	}

	/**
	 * Set the card_id.
	 * 
	 * @param cardId
	 *            card_id
	 */
	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	/**
	 * Get the card_id.
	 * 
	 * @return card_id
	 */
	public String getCardId() {
		return this.cardId;
	}

	/**
	 * Set the open_id.
	 * 
	 * @param openId
	 *            open_id
	 */
	public void setOpenId(String openId) {
		this.openId = openId;
	}

	/**
	 * Get the open_id.
	 * 
	 * @return open_id
	 */
	public String getOpenId() {
		return this.openId;
	}

	/**
	 * Set the admin_id.
	 * 
	 * @param adminId
	 *            admin_id
	 */
	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

	/**
	 * Get the admin_id.
	 * 
	 * @return admin_id
	 */
	public String getAdminId() {
		return this.adminId;
	}

	/**
	 * Set the updatetime.
	 * 
	 * @param updatetime
	 *            updatetime
	 */
	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}

	/**
	 * Get the updatetime.
	 * 
	 * @return updatetime
	 */
	public Date getUpdatetime() {
		return this.updatetime;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		WechatCardConsume other = (WechatCardConsume) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}

}
