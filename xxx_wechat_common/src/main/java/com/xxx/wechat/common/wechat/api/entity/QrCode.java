package com.xxx.wechat.common.wechat.api.entity;

import com.xxx.wechat.common.wechat.api.enums.QrcodeType;

public class QrCode extends BaseModel {

	private static final long serialVersionUID = -6635377778332564076L;

	// 该二维码有效时间，以秒为单位。 最大不超过2592000（即30天），此字段如果不填，则默认有效期为30秒。
	private Integer expireSeconds;

	// 二维码类型，QR_SCENE为临时的整型参数值，QR_STR_SCENE为临时的字符串参数值，QR_LIMIT_SCENE为永久的整型参数值，QR_LIMIT_STR_SCENE为永久的字符串参数值
	private QrcodeType actionName;

	// 二维码详细信息
	private String actionInfo;

	// 场景值ID，临时二维码时为32位非0整型，永久二维码时最大值为100000（目前参数只支持1--100000）
	private Integer sceneId;

	// 场景值ID（字符串形式的ID），字符串类型，长度限制为1到64
	private String sceneStr;

	public Integer getExpireSeconds() {
		return expireSeconds;
	}

	public void setExpireSeconds(Integer expireSeconds) {
		this.expireSeconds = expireSeconds;
	}

	public QrcodeType getActionName() {
		return actionName;
	}

	public void setActionName(QrcodeType actionName) {
		this.actionName = actionName;
	}

	public String getActionInfo() {
		return actionInfo;
	}

	public void setActionInfo(String actionInfo) {
		this.actionInfo = actionInfo;
	}

	public Integer getSceneId() {
		return sceneId;
	}

	public void setSceneId(Integer sceneId) {
		this.sceneId = sceneId;
	}

	public String getSceneStr() {
		return sceneStr;
	}

	public void setSceneStr(String sceneStr) {
		this.sceneStr = sceneStr;
	}

}
