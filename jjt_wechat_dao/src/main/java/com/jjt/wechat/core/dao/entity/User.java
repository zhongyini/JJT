package com.jjt.wechat.core.dao.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * 
 * 用户的实体类
 *
 */
@Entity
@Table(name = "t_wechat_user")
public class User implements Serializable {
	
	private static final long serialVersionUID = -6824624926439743435L;
	@Id
	@Column(name = "openId")
	private String openId;
	@Column(name = "NICKNAME")
	private String nickName;
	@Column(name = "SEX")
	private String sex;
	@Column(name = "CITY")
	private String city;
	@Column(name = "PROVINCE")
	private String province;
	@Column(name = "COUNTRY")
	private String country;
	@Column(name = "LANGUAGE")
	private String language;
	@Column(name = "subscribeTime")
	private Timestamp subscribeTime;
	@Column(name = "createDate")
	private Timestamp createDate;
	@Column(name = "deleteFlag")
	private String deleteFlag;
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public Timestamp getSubscribeTime() {
		return subscribeTime;
	}
	public void setSubscribeTime(Timestamp subscribeTime) {
		this.subscribeTime = subscribeTime;
	}
	public Timestamp getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}
	public String getDeleteFlag() {
		return deleteFlag;
	}
	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	
	

}
