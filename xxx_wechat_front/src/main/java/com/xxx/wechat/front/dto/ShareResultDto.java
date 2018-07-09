package com.xxx.wechat.front.dto;

import java.io.Serializable;
import java.util.List;

import com.xxx.wechat.common.utils.CheckUtils;
import com.xxx.wechat.core.dao.entity.extend.WechatRecommendExt;

public class ShareResultDto implements Serializable {

	private static final long serialVersionUID = 8480624027556711444L;

	/** 二维码路径 */
//	private String qrcodeUrl;
	
	/** 用户openid */
	private String openid;

	/** 推荐人数 */
	private String recommendNum;
	
	/** 可领取金额 */
	private String amountCash;
	
	/** 长度 */
	private long total;
	
	/** 被推荐者集合 */
	private List<WechatRecommendExt> recommendList;

	public String getRecommendNum() {
		return CheckUtils.isNullOrEmpty(recommendNum)?"0":recommendNum;
	}
	public void setRecommendNum(String recommendNum) {
		this.recommendNum = recommendNum;
	}
//	public String getQrcodeUrl() {
//		return qrcodeUrl;
//	}
//	public void setQrcodeUrl(String qrcodeUrl) {
//		this.qrcodeUrl = qrcodeUrl;
//	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getAmountCash() {
		return CheckUtils.isNullOrEmpty(amountCash)?"0":amountCash;
	}
	public void setAmountCash(String amountCash) {
		this.amountCash = amountCash;
	}
	public List<WechatRecommendExt> getRecommendList() {
		return recommendList;
	}
	public void setRecommendList(List<WechatRecommendExt> recommendList) {
		this.recommendList = recommendList;
	}
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}

}
