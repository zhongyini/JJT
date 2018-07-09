package com.xxx.wechat.core.dao.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 推荐关系表.
 * 
 */
@Table(name = "xxx_wechat_recommend")
public class WechatRecommend implements Serializable {

	/** serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** recommend_id. */
	@Id
	@Column(name = "recommend_id")
	private Integer recommendId;

	/** rec_ed_openid. */
	@Column(name = "rec_ed_openid")
	private String recEdOpenid;
	
	/** rec_openid. */
	@Column(name = "rec_openid")
	private String recOpenid;

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

	@Override
	public String toString() {
		return "WechatRecommend [recommendId=" + recommendId + ", recOpenid=" + recOpenid + ", recEdOpenid="
				+ recEdOpenid + ", updatetime=" + updatetime
				+ "]";
	}

}
