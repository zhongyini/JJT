package com.xxx.wechat.core.dao.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 二维码类型表.
 * 
 */
@Table(name = "xxx_wechat_qrcode_type")
public class WechatQrcodeType implements Serializable {

	/** serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** qrcodeTypeId. */
	@Id
	@Column(name = "qrcode_type_id")
	private Integer qrcodeTypeId;

	/** scene. */
	@Column(name = "scene")
	private String scene;

	/** qrcode_name. */
	@Column(name = "qrcode_name")
	private String qrcodeName;

	/** create_user. */
	@Column(name = "create_user")
	private String createUser;

	/** create_date. */
	@Column(name = "create_date")
	private Date createDate;

	/**
	 * Constructor.
	 */
	public WechatQrcodeType() {
	}

	/**
	 * Set the qrcodeTypeId.
	 * 
	 * @param qrcodeTypeId
	 *            qrcodeTypeId
	 */
	public void setId(Integer qrcodeTypeId) {
		this.qrcodeTypeId = qrcodeTypeId;
	}

	/**
	 * Get the qrcodeTypeId.
	 * 
	 * @return qrcodeTypeId
	 */
	public Integer getId() {
		return this.qrcodeTypeId;
	}

	/**
	 * Set the scene.
	 * 
	 * @param scene
	 *            scene
	 */
	public void setScene(String scene) {
		this.scene = scene;
	}

	/**
	 * Get the scene.
	 * 
	 * @return scene
	 */
	public String getScene() {
		return this.scene;
	}

	/**
	 * Set the qrcode_name.
	 * 
	 * @param qrcodeName
	 *            qrcode_name
	 */
	public void setQrcodeName(String qrcodeName) {
		this.qrcodeName = qrcodeName;
	}

	/**
	 * Get the qrcode_name.
	 * 
	 * @return qrcode_name
	 */
	public String getQrcodeName() {
		return this.qrcodeName;
	}

	/**
	 * Set the create_user.
	 * 
	 * @param createUser
	 *            create_user
	 */
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	/**
	 * Get the create_user.
	 * 
	 * @return create_user
	 */
	public String getCreateUser() {
		return this.createUser;
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
		result = prime * result + ((qrcodeTypeId == null) ? 0 : qrcodeTypeId.hashCode());
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
		WechatQrcodeType other = (WechatQrcodeType) obj;
		if (qrcodeTypeId == null) {
			if (other.qrcodeTypeId != null) {
				return false;
			}
		} else if (!qrcodeTypeId.equals(other.qrcodeTypeId)) {
			return false;
		}
		return true;
	}

}
