package com.jjt.wechat.common.wechat.api.response;

/**
 * 微信AccessToken实体类
 *
 */
public class AccessTokenResponse extends BaseResponse{
	
	private static final long serialVersionUID = -2694178048888523479L;

	private String access_token;
	
	private Integer expires_in;

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public Integer getExpires_in() {
		return expires_in;
	}

	public void setExpires_in(Integer expires_in) {
		this.expires_in = expires_in;
	}
	

}
