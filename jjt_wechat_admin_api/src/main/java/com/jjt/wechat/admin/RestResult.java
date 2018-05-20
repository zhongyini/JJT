package com.jjt.wechat.admin;

import com.jjt.wechat.constants.Constants;

public class RestResult {

	public RestResult() {
		this.status=Constants.SUCCESS;
	}

	public RestResult(String message) {
		this.status=Constants.ERROR;
		this.message = message;
	}
	public RestResult(Integer status, String message) {
		this.status=Constants.SUCCESS;
		this.message = message;
	}
	public RestResult(String message, Object result) {
		this.status=Constants.SUCCESS;
		this.message = message;
		this.result = result;
	}
	public RestResult(Object result) {
		this.status=Constants.SUCCESS;
		this.result = result;
	}
	/**
	 * 返回状态
	 */
	private int status;

	/**
	 * 返回错误code
	 */
	private String errCode;
	/**
	 * 返回错误信息
	 */
	private String message;

	/**
	 * 返回结果
	 */
	private Object result;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getErrCode() {
		return errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

	public void setSuccessResult(String message, Object result) {
		this.status = Constants.SUCCESS;
		this.message = message;
		this.result = result;
	}

	public void setErrorResult(String message) {
		this.status = Constants.ERROR;
		this.message = message;
	}

	public void setErrorResult(String message, Object result) {
		this.status = Constants.ERROR;

	}
}
