package com.jjt.wechat.helper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:api.properties")
public class ApiHelper {
	/**
	 * 基干系baseAPI
	 */
	@Value("${base_api_url}")
	private String baseApiUrl;
	/**
	 * 认证系baseAPI
	 */
	@Value("${base_cas_url}")
	private String baseCasUrl;
	/**
	 * 积分系baseAPI
	 */
	@Value("${base_jf_url}")
	private String baseJfUrl;
	/**
	 * 基干系appID
	 */
	@Value("${api_appid}")
	public String apiAppId;
	/**
	 * 基干系appKEY
	 */
	@Value("${api_appkey}")
	public String apiAppKey;
	/**
	 * cas系appID
	 */
	@Value("${cas_appid}")
	public String casAppId;
	/**
	 * cas系appKey
	 */
	@Value("${cas_appkey}")
	public String casAppKey;
	/**
	 * 积分系appID
	 */
	@Value("${jf_appid}")
	public String jfAppId;
	/**
	 * 积分系appKey
	 */
	@Value("${jf_appkey}")
	public String jfAppKey;
	/**
	 * 生日对应版本appID
	 */
	@Value("${yzzf_appid}")
	public String yzzfAppid;
	/**
	 * 生日对应版本appKEY
	 */
	@Value("${yzzf_appkey}")
	public String yzzfAppkey;
	
	/**
	 * 生日对应版本appKEY
	 */
	@Value("${record_appid}")
	public String recordAppid;
	/**
	 * 生日对应版本appKEY
	 */
	@Value("${record_appkey}")
	public String recordAppkey;
	
	/**
	 * 注册来源
	 */
	@Value("${referrer}")
	public String referrer;
	/**
	 * 登录URL
	 */
	@Value("${login_url}")
	private String loginUrl;
	/**
	 * 注册URL
	 */
	@Value("${reg_url}")
	private String regUrl;
	/**
	 * 注册来源
	 */
	@Value("${resend_url}")
	private String resendDefaultPassUrl;
	/**
	 * 用户积分查询
	 */
	@Value("${integral_get}")
	private String integralGet;
	/**
	 * 所有优惠券取得
	 */
	@Value("${all_vouchers}")
	private String allVouchers;
	/**
	 * 单点快捷激活登录
	 */
	@Value("${reg_login_url}")
	private String regLoginUrl;
	/**
	 * 签到
	 */
	@Value("${sign_url}")
	private String signUrl;

	/**
	 * 绑定
	 */
	@Value("${add_intergral}")
	private String addIntergral;

	/**
	 * 绑定
	 */
	@Value("${history_version_url}")
	private String historyVersionUrl;
	
	/**
	 * 获取历史记录
	 * @return
	 */
	@Value("${history_version_url_sec}")
	private String historyVersionUrlSec;
	
	/**
	 * 通过webid获取用户的注册信息
	 */
	@Value("${find_user_author}")
	private String findUserAuthor;
	
	/**
	 * 通过webid记录用户进入体验画面状态
	 */
	@Value("${record_status_url}")
	private String recordStatusUrl;
	
	/**
	 * 记录体验卡用户所选版本及性别
	 */
	@Value("${version_sex_url}")
	private String versionAndSexUrl;
	
	/**
	 * 通过webid增加用户积分
	 */
	@Value("${add_intergral_by_webid}")
	private String addIntergralByWebid;
	
	/**
	 * 通过年、月获取对应版本
	 */
	@Value("${get_date_version}")
	private String getDateVersion;
	
	/**
	 * 积分兑换券路径
	 */
	@Value("${integral_exchange}")
	private String integralExchange;
	
	/**
	 * 通过年、月获取对应版本URL
	 */
	public String getGetDateVersion() {
		return getDateVersion;
	}
	
	/**
	 * 通过webid增加用户积分URL
	 */
	public String getAddIntergralByWebid() {
		return this.baseJfUrl + addIntergralByWebid;
	}

	
	/**
	 * 获取webid记录用户进入体验画面状态URL
	 */
	public String getRecordStatusUrl() {
		return recordStatusUrl;
	}
	
	/**
	 * 记录体验卡用户所选版本及性别
	 */
	public String getVersionAndSex() {
		return versionAndSexUrl;
	}

	public String getFindUserAuthor(){
		return this.baseApiUrl+findUserAuthor;
	}

	public String getAddIntergral() {
		return this.baseJfUrl + addIntergral;
	}

	public String getHistoryVersionUrl() {
		return this.baseApiUrl + historyVersionUrl;
	}
	
	public String getHistoryVersionUrlSec(){
		return this.baseApiUrl + historyVersionUrlSec;
	}

	/**
	 * 获取单点快捷激活登录URL
	 */
	public String getRegLoginUrl() {
		return baseCasUrl + regLoginUrl;
	}

	/**
	 * 获取单点登录URL
	 * 
	 * @return
	 */
	public String getLoginUrl() {
		return this.baseCasUrl + loginUrl;
	}

	public void setLoginUrl(String loginUrl) {
		this.loginUrl = loginUrl;
	}

	/**
	 * 获取登录URL
	 * 
	 * @return
	 */
	public String getRegUrl() {
		return this.baseApiUrl + regUrl;
	}

	/**
	 * 获取用户积分查询URL
	 * 
	 * @return
	 */
	public String getIntegralGetUrl() {
		return this.baseJfUrl + integralGet;
	}
	
	/**
	 * 获取积分兑换券兑换路径
	 * @return
	 */
	public String getIntegralExchange() {
		return this.baseJfUrl + integralExchange;
	}

	/**
	 * 获取用户积分查询URL
	 * 
	 * @return
	 */
	public String getAllVouchersUrl() {
		return this.baseJfUrl + allVouchers;
	}

	/**
	 * 获取签到URL
	 * 
	 * @return
	 */
	public String getSignUrl() {
		return this.baseJfUrl + signUrl;
	}

	public void setRegUrl(String regUrl) {
		this.regUrl = regUrl;
	}

	/**
	 * 获取重新发送注册短信密码URL
	 * 
	 * @return
	 */
	public String getResendDefaultPassUrl() {
		return this.baseApiUrl + resendDefaultPassUrl;
	}

	public void setResendDefaultPassUrl(String resendDefaultPassUrl) {
		this.resendDefaultPassUrl = resendDefaultPassUrl;
	}
}
