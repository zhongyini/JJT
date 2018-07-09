package com.xxx.wechat.core.dao.entity.extend;

import com.xxx.wechat.core.dao.entity.WechatUser;

public class ShareTheViewExt extends WechatUser{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7090560220258748775L;
	
	private int page = 1;

	private int count = 10;
	
	private Integer getshareNumber;
	
	private Integer cancelcount;
	
	private String column;
	
	private String sort;
	
	

	public String getColumn() {
		return column;
	}

	public void setColumn(String column) {
		this.column = column;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

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
