package com.xxx.wechat.common.wechat.api.response;

import com.xxx.wechat.common.wechat.api.entity.BaseModel;

/**
 * 
 */
public class BaseResponse extends BaseModel{

	private static final long serialVersionUID = -1407317213753097990L;
	private String errcode;
	private String errmsg;
	
	public String getErrcode() {
		return errcode;
	}
	public void setErrcode(String errcode) {
		this.errcode = errcode;
	}
	public String getErrmsg() {
		return errmsg;
	}
	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}

}
