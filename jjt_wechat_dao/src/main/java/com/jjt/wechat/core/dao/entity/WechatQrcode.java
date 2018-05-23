package com.jjt.wechat.core.dao.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 二维码表.
 * 
 */
@Table(name = "jjt_wechat_qrcode")
public class WechatQrcode implements Serializable {

	/** serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** qrcode_id. */
	@Id
	@Column(name = "qrcode_id")
	private Integer qrcodeId;

	/** open_id. */
	@Column(name = "open_id")
	private String openId;

	/** ticket. */
	@Column(name = "ticket")
	private String ticket;

	/** qrcode_type. */
	@Column(name = "qrcode_type")
	private Integer qrcodeType;

	/** create_date. */
	@Column(name = "create_date")
	private Date createDate;

	/**
	 * Constructor.
	 */
	public WechatQrcode() {
	}

	/**
	 * Set the qrcode_id.
	 * 
	 * @param qrcodeId
	 *            qrcode_id
	 */
	public void setQrcodeId(Integer qrcodeId) {
		this.qrcodeId = qrcodeId;
	}

	/**
	 * Get the qrcode_id.
	 * 
	 * @return qrcode_id
	 */
	public Integer getQrcodeId() {
		return this.qrcodeId;
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
	 * Set the ticket.
	 * 
	 * @param ticket
	 *            ticket
	 */
	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	/**
	 * Get the ticket.
	 * 
	 * @return ticket
	 */
	public String getTicket() {
		return this.ticket;
	}

	/**
	 * Set the qrcode_type.
	 * 
	 * @param qrcodeType
	 *            qrcode_type
	 */
	public void setQrcodeType(Integer qrcodeType) {
		this.qrcodeType = qrcodeType;
	}

	/**
	 * Get the qrcode_type.
	 * 
	 * @return qrcode_type
	 */
	public Integer getQrcodeType() {
		return this.qrcodeType;
	}

	/**
	 * Set the create_date.
	 * 
	 * @param createDate
	 *            create_date
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * Get the create_date.
	 * 
	 * @return create_date
	 */
	public Date getCreateDate() {
		return this.createDate;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((qrcodeId == null) ? 0 : qrcodeId.hashCode());
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
		WechatQrcode other = (WechatQrcode) obj;
		if (qrcodeId == null) {
			if (other.qrcodeId != null) {
				return false;
			}
		} else if (!qrcodeId.equals(other.qrcodeId)) {
			return false;
		}
		return true;
	}

}
