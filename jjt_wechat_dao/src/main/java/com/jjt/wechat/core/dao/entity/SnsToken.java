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
@Table(name = "jjt_sns_token")
public class SnsToken implements Serializable {

	private static final long serialVersionUID = 8215284855874708251L;

	@Id
	@Column(name = "openid")
	private String openid;
	
	/**
	 * 获取到的凭证
	 */
	@Column(name = "access_token")
	private String accessToken;
	
	@Column(name = "jsapi_ticket")
	private String jsapiTicket;
	
	/**
	 * 凭证有效时间，单位：秒
	 */
	@Column(name = "expires_in")
	private Integer expiresIn;

	@Column(name = "refresh_token")
	private String refreshToken;
	
	@Column(name = "scope")
	private String scope;
	
	@Column(name = "unionid")
	private String unionid;
	
	/**
	 * 微信返回的错误信息
	 */
	@Column(name = "errcode")
	private String errcode;
	/**
	 * 微信返回的错误码
	 */
	@Column(name = "errmsg", nullable = false)
	private String errmsg;
	/**
	 * token插入表的时间戳
	 */
	@Column(name = "create_date")
	private Timestamp createDate;
	
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public String getJsapiTicket() {
		return jsapiTicket;
	}
	public void setJsapiTicket(String jsapiTicket) {
		this.jsapiTicket = jsapiTicket;
	}
	public Integer getExpiresIn() {
		return expiresIn;
	}
	public void setExpiresIn(Integer expiresIn) {
		this.expiresIn = expiresIn;
	}
	public String getRefreshToken() {
		return refreshToken;
	}
	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
	public String getUnionid() {
		return unionid;
	}
	public void setUnionid(String unionid) {
		this.unionid = unionid;
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
