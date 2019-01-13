package com.xxx.wechat.core.dao.entity.extend;

import com.xxx.wechat.core.dao.entity.LotteryDltHistory;

/**
 * 大乐透历史记录表
 * 
 * @author yk
 *
 */
public class LotteryDltHistoryExt extends LotteryDltHistory {

	private static final long serialVersionUID = 3745485696817884537L;
	
	private String fieldName;

	private Integer fieldValue;
	
	private Integer fieldCount;

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public Integer getFieldValue() {
		return fieldValue;
	}

	public void setFieldValue(Integer fieldValue) {
		this.fieldValue = fieldValue;
	}

	public Integer getFieldCount() {
		return fieldCount;
	}

	public void setFieldCount(Integer fieldCount) {
		this.fieldCount = fieldCount;
	}

}
