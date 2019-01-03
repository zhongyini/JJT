package com.xxx.wechat.core.dao.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 百度接口Access Token的实体类
 * 
 */
@Table(name = "xxx_baidu_api_access_token")
public class BaiduApiAccessToken implements Serializable {

	private static final long serialVersionUID = 9065748003703618943L;
	
	/**
	 * 获取到的accessToken
	 */
	@Id
	@Column(name="ACCESS_TOKEN")
	private String accessToken;
	
	/**
	 * 用于刷新的token
	 */
	@Column(name="REFRESH_TOKEN")
	private String refreshToken;
	
	/**
	 * 凭证有效时间，单位：秒
	 */
	@Column(name="EXPIRES_IN")
	private int expiresIn;

	@Column(name="SESSION_KEY")
	private String session_key;
	
	@Column(name="SCOPE")
	private String scope;
	
	@Column(name="SESSION_SECRET")
	private String sessionSecret;

	@Column(name="CREATE_DATE")
	private Timestamp create_date;
	
	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public int getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(int expiresIn) {
		this.expiresIn = expiresIn;
	}

	public String getSession_key() {
		return session_key;
	}

	public void setSession_key(String session_key) {
		this.session_key = session_key;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getSessionSecret() {
		return sessionSecret;
	}

	public void setSessionSecret(String sessionSecret) {
		this.sessionSecret = sessionSecret;
	}

	public Timestamp getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Timestamp create_date) {
		this.create_date = create_date;
	}
	
}
