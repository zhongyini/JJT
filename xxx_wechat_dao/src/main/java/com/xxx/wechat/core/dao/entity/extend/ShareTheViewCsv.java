package com.xxx.wechat.core.dao.entity.extend;

import java.io.Serializable;

public class ShareTheViewCsv implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3049259455553625459L;

	public ShareTheViewCsv() {
		
	}
	
	public ShareTheViewCsv(ShareTheViewExt share) {
		this.phone = share.getPhone();
		this.shareNumber = share.getShareNumber();
		this.getshareNumber = share.getGetshareNumber();
		this.cancelcount = share.getCancelcount();
	}
	
	private String phone;
	
	private Integer shareNumber;
	
	private Integer getshareNumber;
	
	private Integer cancelcount;

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getShareNumber() {
		return shareNumber;
	}

	public void setShareNumber(Integer shareNumber) {
		this.shareNumber = shareNumber;
	}

	public Integer getGetshareNumber() {
		return getshareNumber;
	}

	public void setGetshareNumber(Integer getshareNumber) {
		this.getshareNumber = getshareNumber;
	}

	public Integer getCancelcount() {
		return cancelcount;
	}

	public void setCancelcount(Integer cancelcount) {
		this.cancelcount = cancelcount;
	}
	
	
	
}
