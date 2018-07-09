package com.xxx.wechat.core.dao.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import com.xxx.wechat.common.utils.CheckUtils;
/**
 * 
 * 用户表
 *
 */
@Table(name = "xxx_wechat_user")
public class WechatUser implements Serializable {

	private static final long serialVersionUID = 523462239335999362L;

	@Id
	@Column(name = "OPENID")
	private String openid;
	
	@Column(name = "UNIONID")
	private String unionid;
	
	@Column(name = "PHONE")
	private String phone;
	
	@Column(name = "share_number")
	private Integer shareNumber;
	
	@Column(name = "verify_phone")
	private String verifyPhone;
	
	@Column(name = "verify_code")
	private String verifyCode;
	
	@Column(name = "verify_code_time")
	private Timestamp verifyCodeTime;
	
	@Column(name = "UPDATETIME")
	private Timestamp updatetime;

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getUnionid() {
		return unionid;
	}

	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getShareNumber() {
		return CheckUtils.isNull(shareNumber)?0:shareNumber;
	}

	public void setShareNumber(Integer shareNumber) {
		this.shareNumber = shareNumber;
	}

	public String getVerifyPhone() {
		return verifyPhone;
	}

	public void setVerifyPhone(String verifyPhone) {
		this.verifyPhone = verifyPhone;
	}

	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

	public Timestamp getVerifyCodeTime() {
		return verifyCodeTime;
	}

	public void setVerifyCodeTime(Timestamp verifyCodeTime) {
		this.verifyCodeTime = verifyCodeTime;
	}

	public Timestamp getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Timestamp updatetime) {
		this.updatetime = updatetime;
	}
	
}
