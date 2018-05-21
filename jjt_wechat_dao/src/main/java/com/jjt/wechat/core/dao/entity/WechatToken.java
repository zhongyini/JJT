package com.jjt.wechat.core.dao.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 微信Access Token的实体类
 * 
 */
@Entity
@Table(name = "t_wechat_token")
public class WechatToken implements Serializable {

	private static final long serialVersionUID = 9065748003703618943L;
	/**
	 * 获取到的凭证
	 */
	@Id
	@Column(name = "accessToken")
	private String accessToken;
	/**
	 * 凭证有效时间，单位：秒
	 */
	@Column(name = "expiresIn")
	private Integer expiresIn;

	/**
	 * 微信返回的错误信息
	 */
	@Column(name = "errcode", nullable = false)
	private String errcode;
	/**
	 * 微信返回的错误码
	 */
	@Column(name = "errmsg", nullable = false)
	private String errmsg;
	/**
	 * token插入表的时间戳
	 */
	@Column(name = "createDate")
	private Timestamp createDate;

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public Integer getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(Integer expiresIn) {
		this.expiresIn = expiresIn;
	}

	public String getErrcode() {
		return errcode;
	}

	public void setErrcode(String errcode) {
		this.errcode = errcode;
	}

	public String getErrmsg() {
		return errmsg;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}

	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

}
