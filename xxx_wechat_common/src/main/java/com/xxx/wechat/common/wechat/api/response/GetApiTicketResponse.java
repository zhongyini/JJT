package com.xxx.wechat.common.wechat.api.response;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 获取微信JsApiTicket
 * 
 */
public class GetApiTicketResponse extends BaseResponse {

	private static final long serialVersionUID = 8691160586253383707L;

	private String ticket;

    @JSONField(name = "expires_in")
    private Integer expiresIn;

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public Integer getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Integer expiresIn) {
        this.expiresIn = expiresIn;
    }
}
