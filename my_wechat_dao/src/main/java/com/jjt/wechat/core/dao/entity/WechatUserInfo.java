package com.jjt.wechat.core.dao.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * 用户的实体类
 *
 */
@Table(name = "jjt_wechat_user_info")
public class WechatUserInfo implements Serializable {
	
	private static final long serialVersionUID = -6824624926439743435L;
	@Id
	@Column(name = "OPEN_ID")
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
	@Column(name = "SUBSCRIBE")
	private Integer subscribe;
	@Column(name = "LANGUAGE")
	private String language;
	@Column(name = "SUBSCRIBE_TIME")
	private Long subscribeTime;
	@Column(name = "REMARK")
	private String remark;
	@Column(name = "GROUPID")
	private Integer groupid;
	@Column(name = "SUBSCRIBE_SCENE")
	private String subscribeScene;
	@Column(name = "QR_SCENE")
	private int qrScene;
	@Column(name = "QR_SCENE_STR")
	private String qrSceneStr;
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
	public Integer getSubscribe() {
		return subscribe;
	}
	public void setSubscribe(Integer subscribe) {
		this.subscribe = subscribe;
	}
	public Timestamp getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(Timestamp updatetime) {
		this.updatetime = updatetime;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public Long getSubscribeTime() {
		return subscribeTime;
	}
	public void setSubscribeTime(Long subscribeTime) {
		this.subscribeTime = subscribeTime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getGroupid() {
		return groupid;
	}
	public void setGroupid(Integer groupid) {
		this.groupid = groupid;
	}
	public String getSubscribeScene() {
		return subscribeScene;
	}
	public void setSubscribeScene(String subscribeScene) {
		this.subscribeScene = subscribeScene;
	}
	public int getQrScene() {
		return qrScene;
	}
	public void setQrScene(int qrScene) {
		this.qrScene = qrScene;
	}
	public String getQrSceneStr() {
		return qrSceneStr;
	}
	public void setQrSceneStr(String qrSceneStr) {
		this.qrSceneStr = qrSceneStr;
	}
	
}
