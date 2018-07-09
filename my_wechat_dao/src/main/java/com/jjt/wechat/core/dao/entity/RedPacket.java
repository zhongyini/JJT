package com.jjt.wechat.core.dao.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 红包履历表.
 * 
 */
@Table(name = "jjt_red_packet")
public class RedPacket implements Serializable {

	/** serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** packet_id. */
	@Id
	@Column(name = "packet_id")
	private Integer packetId;

	/** code. */
	@Column(name = "code")
	private String code;

	/** card_id. */
	@Column(name = "card_id")
	private String cardId;

	/** open_id. */
	@Column(name = "open_id")
	private String openId;

	/** create_date. */
	@Column(name = "create_date")
	private Date createDate;

	/**
	 * Constructor.
	 */
	public RedPacket() {
	}

	/**
	 * Set the packet_id.
	 * 
	 * @param packetId
	 *            packet_id
	 */
	public void setPacketId(Integer packetId) {
		this.packetId = packetId;
	}

	/**
	 * Get the packet_id.
	 * 
	 * @return packet_id
	 */
	public Integer getPacketId() {
		return this.packetId;
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
		result = prime * result + ((packetId == null) ? 0 : packetId.hashCode());
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
		RedPacket other = (RedPacket) obj;
		if (packetId == null) {
			if (other.packetId != null) {
				return false;
			}
		} else if (!packetId.equals(other.packetId)) {
			return false;
		}
		return true;
	}

}
