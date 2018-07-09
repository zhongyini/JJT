package com.xxx.wechat.core.dao.entity.extend;

import com.xxx.wechat.core.dao.entity.AdminUser;

public class AdminUserExt extends AdminUser{

	/**
	 * 
	 */
	private static final long serialVersionUID = 947427224240733074L;

	
	private int page = 1;

	private int count = 10;
	
	private String oldPassword ;

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	
	
}
