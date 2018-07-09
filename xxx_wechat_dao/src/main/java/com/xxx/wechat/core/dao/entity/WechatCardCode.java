package com.xxx.wechat.core.dao.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 生成卡券code表.
 * 
 */
@Table(name = "xxx_wechat_card_code")
public class WechatCardCode implements Serializable {

	/** serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** codeId. */
	@Id
	@Column(name = "code_id")
	private Integer codeId;

	/** code. */
	@Column(name = "code")
	private String code;

	/** card_id. */
	@Column(name = "card_id")
	private String cardId;

	/** rec_ed_openid. */
	@Column(name = "rec_ed_openid")
	private String recEdOpenid;
	
	/** rec_openid. */
	@Column(name = "rec_openid")
	private String recOpenid;

	/** money. */
	@Column(name = "money")
	private String money;
	
	/** code_status. */
	@Column(name = "code_status")
	private Integer codeStatus;

	/** red_packet_status. */
	@Column(name = "red_packet_status")
	private Integer redPacketStatus;

	/** updatetime. */
	@Column(name = "updatetime")
	private Date updatetime;

	/**
	 * Constructor.
	 */
	public WechatCardCode() {
	}

	/**
	 * Set the codeId.
	 * 
	 * @param codeId
	 *            codeId
	 */
	public void setCodeId(Integer codeId) {
		this.codeId = codeId;
	}

	/**
	 * Get the codeId.
	 * 
	 * @return codeId
	 */
	public Integer getCodeId() {
		return this.codeId;
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
	 * Set the rec_ed_openid.
	 * 
	 * @param recEdOpenid
	 *            rec_ed_openid
	 */
	public void setRecEdOpenid(String recEdOpenid) {
		this.recEdOpenid = recEdOpenid;
	}

	/**
	 * Get the rec_ed_openid.
	 * 
	 * @return rec_ed_openid
	 */
	public String getRecEdOpenid() {
		return this.recEdOpenid;
	}

	/**
	 * Set the rec_openid.
	 * 
	 * @param recOpenid
	 *            rec_openid
	 */
	public void setRecOpenid(String recOpenid) {
		this.recOpenid = recOpenid;
	}

	/**
	 * Get the rec_openid.
	 * 
	 * @return rec_openid
	 */
	public String getRecOpenid() {
		return this.recOpenid;
	}
	
	/**
	 * Get the money.
	 * 
	 * @return money
	 */
	public String getMoney() {
		return this.money;
	}
	
	/**
	 * Set the money.
	 * 
	 * @param money
	 *            money
	 */
	public void setMoney(String money) {
		this.money = money;
	}

	/**
	 * Set the code_status.
	 * 
	 * @param codeStatus
	 *            code_status
	 */
	public void setCodeStatus(Integer codeStatus) {
		this.codeStatus = codeStatus;
	}

	/**
	 * Get the code_status.
	 * 
	 * @return code_status
	 */
	public Integer getCodeStatus() {
		return this.codeStatus;
	}

	/**
	 * Set the red_packet_status.
	 * 
	 * @param redPacketStatus
	 *            red_packet_status
	 */
	public void setRedPacketStatus(Integer redPacketStatus) {
		this.redPacketStatus = redPacketStatus;
	}

	/**
	 * Get the red_packet_status.
	 * 
	 * @return red_packet_status
	 */
	public Integer getRedPacketStatus() {
		return this.redPacketStatus;
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
		result = prime * result + ((codeId == null) ? 0 : codeId.hashCode());
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
		WechatCardCode other = (WechatCardCode) obj;
		if (codeId == null) {
			if (other.codeId != null) {
				return false;
			}
		} else if (!codeId.equals(other.codeId)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "WechatCardCode [codeId=" + codeId + ", code=" + code + ", cardId=" + cardId + ", recEdOpenid="
				+ recEdOpenid + ", recOpenid=" + recOpenid + ", money=" + money + ", codeStatus=" + codeStatus
				+ ", redPacketStatus=" + redPacketStatus + ", updatetime=" + updatetime + "]";
	}

}
