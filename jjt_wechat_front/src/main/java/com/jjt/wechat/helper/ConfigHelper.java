package com.jjt.wechat.helper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.jjt.common.utils.DateUtils;


@Configuration
@PropertySource("classpath:config.properties")
public class ConfigHelper {
	@Value(value = "${single.sign.on.user}")
	public String singleSignOnUser;

	@Value(value = "${single.sign.on.pwd}")
	public String singleSignOnPwd;

	@Value(value = "${single.sign.on.url}")
	public String singleSignOnUrl;

	@Value(value = "${user.bind.switch}")
	public int userBindSwitch;

	@Value(value = "${user.bind.startDate}")
	public int userBindSatrtDate;

	@Value(value = "${user.bind.endDate}")
	public int userBindEndDate;

	@Value(value = "${user.bind.points}")
	public int points;

	@Value(value = "${user.bind.reason}")
	public String reason;
	
	@Value(value = "${user.experience.startDate}")
	public int userExperienceStartDate;
	@Value(value = "${user.experience.endDate}")
	public int userExperienceEndDate;
	@Value(value = "${user.experience.points}")
	public int userExperiencePoints;
	@Value(value = "${user.experience.reason}")
	public String userExperienceReason;
	@Value(value = "${user.experience.url}")
	public String userExperienceUrl;
	@Value(value = "${user.experience.home}")
	public String userExperienceHome;
	
	@Value(value = "${user.january.startDate}")
	public int userJanuaryStartDate;
	@Value(value = "${user.january.endDate}")
	public int userJanuaryEndDate;
	@Value(value = "${user.january.points}")
	public int userJanuaryPoints;
	@Value(value = "${user.january.reason}")
	public String userJanuaryReason;
	@Value(value = "${user.january.qrcode}")
	public String userJanuaryQrcode;
	@Value(value = "${user.january.qrid}")
	public int userJanuaryQrid;
	
	@Value(value = "${voucher.jf}")
	public String voucherJf;
	@Value(value = "${voucher.show}")
	public String voucherShow;
	@Value(value = "${voucher.shop}")
	public String voucherShop;
	
	@Value(value = "${wechat.message.token}")
	public String wechatMessageToken;
	
	@Value(value= "${reply.integral.unsend}")
	public String replyIntegralUnsend;
	
	@Value(value="${integer.reply.head}")
	public String integerReplyHead;
	
	@Value(value="${integer.reply.foot}")
	public String integerReplyFoot;
	
	@Value(value="${qiaohu.cookie}")
	public String cookieKey;
	
	@Value(value="${kf.close.session}")
	public String kfCloseSession;
	
	@Value(value="${around.birthday.cou.url}")
	public String aroundBirthdayUrl;
	
	@Value(value="${show.ticket.cou.url}")
	public String showTicketUrl;
	
	@Value(value="${integral.cou.url}")
	public String integralUrl;
	
	@Value(value="${hld.cou.url}")
	public String hldCouUrl;
	
	@Value(value="${non.customer.online}")
	public String nonCustomerOnline;
	
	@Value(value="${qq.map.key}")
	public String qqMapkey;
	
	@Value(value="${auditing.template.id}")
	public String auditingTemplateId;
	
	/*
	 * auditing.remarkValue=123remark
auditing.firstValue=123first
auditing.failed.keyword1=failkey1
auditing.failed.keyword2=failedkey2
auditing.success.keyword1=successkey1
auditing.success.keyword2=successkey2
	 */
	@Value(value="${auditing.remarkValue}")
	public String auditingRemarkValue;
	
	@Value(value="${auditing.firstValue}")
	public String auditingFirstValue;
	
	@Value(value="${auditing.failed.keyword1}")
	public String auditingFailedKeyword1;
	
	@Value(value="${auditing.failed.keyword2}")
	public String auditingFailedKeyword2;
	
	@Value(value="${auditing.success.keyword1}")
	public String auditingSuccessKeyword1;
	
	@Value(value="${auditing.success.keyword2}")
	public String auditingSuccessKeyword2;
	

	public boolean isInBindActivity() {
		if (userBindSwitch != 0) {
			return false;
		}
		int today = Integer.parseInt(DateUtils.format(DateUtils.getNow(),
				DateUtils.yyyyMMdd));
		if ((today < userBindSatrtDate) || (today > userBindEndDate)) {
			return false;
		}
		return true;
	}
	
	// 判断是否在用户体验的日期内
	public boolean isInExperienceDate() {
		int today = Integer.parseInt(DateUtils.format(DateUtils.getNow(),
				DateUtils.yyyyMMdd));
		if ((today < userExperienceStartDate) || (today > userExperienceEndDate)) {
			return false;
		}
		return true;
	}
	
	/**
	 * 判断是否在一月活动时间范围内
	 * @return
	 */
	public boolean isInJanuaryDate() {
		int today = Integer.parseInt(DateUtils.format(DateUtils.getNow(),
				DateUtils.yyyyMMdd));
		if ((today < userJanuaryStartDate) || (today > userJanuaryEndDate)) {
			return false;
		}
		return true;
	}

}
