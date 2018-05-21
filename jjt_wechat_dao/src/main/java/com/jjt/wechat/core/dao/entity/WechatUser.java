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
@Table(name = "jjt_wechat_user")
public class WechatUser implements Serializable {
	
	private static final long serialVersionUID = -6824624926439743435L;
	@Id
	@Column(name = "openId")
	private String openId;
	@Column(name = "NICKNAME")
	private String nickName;
	@Column(name = "SEX")
	private String sex;
	@Column(name = "PROVINCE")
	private String province;
	@Column(name = "CITY")
	private String city;
	@Column(name = "COUNTRY")
	private String country;
	@Column(name = "HEADIMGURL")
	private String headimgurl;
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
	public Timestamp getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(Timestamp updatetime) {
		this.updatetime = updatetime;
	}
	public String getHeadimgurl() {
		return headimgurl;
	}
	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}
	public String getUnionid() {
		return unionid;
	}
	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}

}
