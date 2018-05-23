package com.jjt.wechat.core.dao.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 二维码类型表.
 * 
 */
@Table(name = "jjt_wechat_qrcode_type")
public class WechatQrcodeType implements Serializable {

	/** serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** id. */
	@Id
	@Column(name = "id")
	private Integer id;

	/** scene. */
	@Column(name = "scene")
	private String scene;

	/** qrcode_name. */
	@Column(name = "qrcode_name")
	private String qrcodeName;

	/** modify_admin. */
	@Column(name = "modify_admin")
	private String modifyAdmin;

	/** modify_time. */
	@Column(name = "modify_time")
	private Date modifyTime;

	/**
	 * Constructor.
	 */
	public WechatQrcodeType() {
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
	 * Set the modify_admin.
	 * 
	 * @param modifyAdmin
	 *            modify_admin
	 */
	public void setModifyAdmin(String modifyAdmin) {
		this.modifyAdmin = modifyAdmin;
	}

	/**
	 * Get the modify_admin.
	 * 
	 * @return modify_admin
	 */
	public String getModifyAdmin() {
		return this.modifyAdmin;
	}

	/**
	 * Set the modify_time.
	 * 
	 * @param modifyTime
	 *            modify_time
	 */
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	/**
	 * Get the modify_time.
	 * 
	 * @return modify_time
	 */
	public Date getModifyTime() {
		return this.modifyTime;
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
		WechatQrcodeType other = (WechatQrcodeType) obj;
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
