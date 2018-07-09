package com.jjt.wechat.core.dao.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * 
 * 用户表
 *
 */
@Table(name = "jjt_wechat_user")
public class WechatUser implements Serializable {

	private static final long serialVersionUID = 523462239335999362L;

	@Id
	@Column(name = "OPEN_ID")
	private String openId;
	
	@Column(name = "UNIONID")
	private String unionid;
	
	@Column(name = "UPDATETIME")
	private Timestamp updatetime;

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getUnionid() {
		return unionid;
	}

	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}

	public Timestamp getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Timestamp updatetime) {
		this.updatetime = updatetime;
	}
	
	
}
