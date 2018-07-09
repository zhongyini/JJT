package com.xxx.wechat.common.wechat.api.response;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 获取二维码
 * 
 */
public class QrcodeResponse extends BaseResponse {

	private static final long serialVersionUID = 4845275012385095116L;
	
	private String  ticket;
    @JSONField(name = "expire_seconds")
    private Integer expireSeconds;
    private String  url;

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public Integer getExpireSeconds() {
        return expireSeconds;
    }

    public void setExpireSeconds(Integer expireSeconds) {
        this.expireSeconds = expireSeconds;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
