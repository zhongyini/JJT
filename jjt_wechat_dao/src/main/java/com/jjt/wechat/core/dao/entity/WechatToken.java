package com.jjt.wechat.core.dao.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 微信Access Token的实体类
 * 
 */
@Table(name = "jjt_wechat_token")
public class WechatToken implements Serializable {

	private static final long serialVersionUID = 9065748003703618943L;
	
	/**
	 * 获取到的凭证
	 */
	@Id
	@Column(name="ACCESS_TOKEN")
	private String accessToken;
	
	@Column(name="JSAPI_TICKET")
	private String jsapiTicket;
	/**
	 * 凭证有效时间，单位：秒
	 */
	@Column(name="EXPIRES_IN")
	private int expiresIn;

	/**
	 * 错误时微信会返回错误码等信息，JSON数据包示例如下（该示例为AppID无效错误）:
	 */
	@Column(name="ERRCODE")
	private String errcode;
	
	@Column(name="ERRMSG")
	private String errmsg;
	
	@Column(name="CREATE_DATE")
	private Timestamp createDate;

	public String getJsapiTicket() {
		return jsapiTicket;
	}

	public void setJsapiTicket(String jsapiTicket) {
		this.jsapiTicket = jsapiTicket;
	}

	public int getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(int expiresIn) {
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
	
	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	
}
