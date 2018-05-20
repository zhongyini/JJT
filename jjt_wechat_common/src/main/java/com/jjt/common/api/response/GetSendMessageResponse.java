package com.jjt.common.api.response;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 获取群发消息结果
 */
public class GetSendMessageResponse extends BaseResponse {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2880960015823751347L;
	@JSONField(name = "msg_id")
	private String msgId;

	@JSONField(name = "msg_status")
	private String msgStatus;

	public String getMsgId() {
		return msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

	public String getMsgStatus() {
		return msgStatus;
	}

	public void setMsgStatus(String msgStatus) {
		this.msgStatus = msgStatus;
	}

}
