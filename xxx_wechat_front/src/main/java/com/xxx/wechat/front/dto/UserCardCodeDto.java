package com.xxx.wechat.front.dto;

import java.io.Serializable;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;
import com.xxx.wechat.common.constant.Constant;
import com.xxx.wechat.common.utils.CheckUtils;
import com.xxx.wechat.core.dao.entity.extend.WechatRecommendExt;

//import com.alibaba.fastjson.JSONArray;
//import com.alibaba.fastjson.annotation.JSONField;

public class UserCardCodeDto implements Serializable {

	private static final long serialVersionUID = 3909194298164375307L;

	// 用户openid
	private String openid;
	private String code;
	@JSONField(name = "card_id")
	private  String cardId;
	// 卡券列表
	private String cardList;
	// 手机号
	private String phone;
	// 总领券人数
	private String codeNum;
	// 长度
	private long total;
	
	private List<WechatRecommendExt> brotherList;
	
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getCardId() {
		return cardId;
	}
	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
	public String getCardList() {
		return cardList;
	}
	public void setCardList(String cardList) {
		this.cardList = cardList;
	}
	public List<WechatRecommendExt> getBrotherList() {
		return brotherList;
	}
	public void setBrotherList(List<WechatRecommendExt> brotherList) {
		this.brotherList = brotherList;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	public String getCodeNum() {
		return CheckUtils.isNullOrEmpty(codeNum)?Constant.Str.STR_ZERO:codeNum;
	}
	public void setCodeNum(String codeNum) {
		this.codeNum = codeNum;
	}

}
