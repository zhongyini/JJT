package com.xxx.wechat.core.dao.entity.extend;

import java.net.URLDecoder;

import com.xxx.wechat.common.constant.Constant;
import com.xxx.wechat.common.utils.CheckUtils;
import com.xxx.wechat.core.dao.entity.WechatRecommend;

public class WechatRecommendExt extends WechatRecommend {

	private static final long serialVersionUID = 3550626401504669183L;

	/** 被推荐者头像 */
	private String headimgurl;
	
	/** 被推荐者昵称 */
	private String nickname;
	
	/** 核销时间 */
	private String createDate;
	
	/** 核销金额 */
	private String money;
	
	public String getHeadimgurl() {
		return headimgurl;
	}

	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}

	public String getNickname() {
		try {
			return URLDecoder.decode(nickname, "utf-8");
		} catch (Exception e) {

		}
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getMoney() {
		return (CheckUtils.isNullOrEmpty(money) || money.equals(Constant.Str.STR_ZERO)) ?"待体验":money+"元";
	}

	public void setMoney(String money) {
		this.money = money;
	}
	
}
