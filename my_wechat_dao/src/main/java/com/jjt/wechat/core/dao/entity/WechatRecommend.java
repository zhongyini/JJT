package com.jjt.wechat.core.dao.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 推荐关系表.
 * 
 */
@Table(name = "jjt_wechat_recommend")
public class WechatRecommend implements Serializable {

	/** serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** recommend_id. */
	@Id
	@Column(name = "recommend_id")
	private Integer recommendId;

	/** rec_open_id. */
	@Column(name = "rec_open_id")
	private String recOpenId;

	/** rec_ed_open_id. */
	@Column(name = "rec_ed_open_id")
	private String recEdOpenId;

	/** qrcode_type_id. */
	@Column(name = "qrcode_type_id")
	private Integer qrcodeTypeId;

	/** status. */
	@Column(name = "status")
	private Integer status;

	/** updatetime. */
	@Column(name = "updatetime")
	private Date updatetime;

	/**
	 * Constructor.
	 */
	public WechatRecommend() {
	}

	/**
	 * Set the recommend_id.
	 * 
	 * @param recommendId
	 *            recommend_id
	 */
	public void setRecommendId(Integer recommendId) {
		this.recommendId = recommendId;
	}

	/**
	 * Get the recommend_id.
	 * 
	 * @return recommend_id
	 */
	public Integer getRecommendId() {
		return this.recommendId;
	}

	/**
	 * Set the rec_open_id.
	 * 
	 * @param recOpenId
	 *            rec_open_id
	 */
	public void setRecOpenId(String recOpenId) {
		this.recOpenId = recOpenId;
	}

	/**
	 * Get the rec_open_id.
	 * 
	 * @return rec_open_id
	 */
	public String getRecOpenId() {
		return this.recOpenId;
	}

	/**
	 * Set the rec_ed_open_id.
	 * 
	 * @param recEdOpenId
	 *            rec_ed_open_id
	 */
	public void setRecEdOpenId(String recEdOpenId) {
		this.recEdOpenId = recEdOpenId;
	}

	/**
	 * Get the rec_ed_open_id.
	 * 
	 * @return rec_ed_open_id
	 */
	public String getRecEdOpenId() {
		return this.recEdOpenId;
	}

	/**
	 * Set the qrcode_type_id.
	 * 
	 * @param qrcodeTypeId
	 *            qrcode_type_id
	 */
	public void setQrcodeTypeId(Integer qrcodeTypeId) {
		this.qrcodeTypeId = qrcodeTypeId;
	}

	/**
	 * Get the qrcode_type_id.
	 * 
	 * @return qrcode_type_id
	 */
	public Integer getQrcodeTypeId() {
		return this.qrcodeTypeId;
	}

	/**
	 * Set the status.
	 * 
	 * @param status
	 *            status
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * Get the status.
	 * 
	 * @return status
	 */
	public Integer getStatus() {
		return this.status;
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
		result = prime * result + ((recommendId == null) ? 0 : recommendId.hashCode());
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
		WechatRecommend other = (WechatRecommend) obj;
		if (recommendId == null) {
			if (other.recommendId != null) {
				return false;
			}
		} else if (!recommendId.equals(other.recommendId)) {
			return false;
		}
		return true;
	}

}
