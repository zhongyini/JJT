package com.jjt.wechat.admin.dto;

import java.io.Serializable;

import com.jjt.wechat.core.constants.Constants;

public class UserAppendDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5855298868533959988L;

	private String year;
	private String month;
	private String yearAddMonth;
	
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getYearAddMonth() {
		if (this.month.length() == 1) {
			month = Constants.ZERO + month;
		}
		yearAddMonth = year + month;
		return yearAddMonth;
	}
	public void setYearAddMonth(String yearAddMonth) {
		this.yearAddMonth = yearAddMonth;
	}
	
}
