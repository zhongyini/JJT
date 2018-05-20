package com.jjt.map.common.api.entity;

import java.io.Serializable;

public class GeoCoder implements Serializable{
	
	private static final long serialVersionUID = 1919010869470592419L;
	private String status;
	private String message;
	private String request_id;
	private GeoCoderResult result;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getRequest_id() {
		return request_id;
	}
	public void setRequest_id(String request_id) {
		this.request_id = request_id;
	}
	public GeoCoderResult getResult() {
		return result;
	}
	public void setResult(GeoCoderResult result) {
		this.result = result;
	}
	
}
