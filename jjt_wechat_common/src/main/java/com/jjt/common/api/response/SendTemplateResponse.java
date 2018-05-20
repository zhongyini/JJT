package com.jjt.common.api.response;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 发送模版消息响应
 */
public class SendTemplateResponse extends BaseResponse {

    /**
	 * 
	 */
	private static final long serialVersionUID = -734017541620886056L;
	/**
     * 消息id
     */
    @JSONField(name = "msgid")
    private String msgid;

    public String getMsgid() {
        return msgid;
    }

    public void setMsgid(String msgid) {
        this.msgid = msgid;
    }
}
