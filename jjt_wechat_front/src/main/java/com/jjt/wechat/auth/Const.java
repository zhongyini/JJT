package com.jjt.wechat.auth;


public interface Const {

	interface Status {
		int STATUS_OK = 0;
		int STATUS_FAIL = 1;
		String MESSAGE_OK = "处理成功";
	}

	interface Sms {
		/**
		 * 验证码有效时间
		 */
		int TIME_VERIFY_CODE = 180;
		/**
		 * 再次发送验证码时间
		 */
		int TIME_RESEND = 120;

	}

	interface Session {

		String SESSION_COMMENT_CATEGORY = "key_comment_category";

		String SESSION_MOBILE = "key_session_mobile";

		String SESSION_INFO = "key.session.info";
		/**
		 * 验证码key
		 */
		String SESSION_VERIFI_CODE = "verifiCode";
		/**
		 * 验证码key
		 */
		String SESSION_REGISTER_ID = "key_session_registerid";

		/** 用户手机号码 */
		String SESSION_APPLYPACK_MOBILE = "applyPackMobile";

		/** 用户手机验证码 */
		String SESSION_APPLYPACK_MOBILE_VERIFICODE = "applyPackMobileVerifiCode";

		/** 用户手机号码(礼物) */
		String SESSION_APPLYGIFT_MOBILE = "applyGiftMobile";
		String SESSION_USER_ID = "applyGiftUserId";

		/** 用户手机验证码(礼物) */
		String SESSION_APPLYGIFT_MOBILE_VERIFICODE = "applyGiftMobileVerifiCode";
	}

	interface Cookie {
		String COOKIE_MOBILE_TEMP = "mobile_temp";

		int TIMEOUT = 300; // 登陆超时设置(秒)

		int TIME_SESSION_OUT = -1; // tomcat默认session存活时间
		/**
		 * 一年
		 */
		int USER_INFO_TIMEOUT = 365 * 24 * 60 * 60;
		String COOKIE_DOMAIN = "qiaohu.com";
		String COOKIE_TGC = "_qiaohu_c";
		String COOKIE_TIMEOUT = "_qiaohu_t";
		String COOKIE_LOGIN = "_qiaohu_k";

		String COOKIE_NICKNAME = "nickname";
		String COOKIE_ISMEMBER = "isMember";
		String COOKIE_USER_ID = "userId";
		String COOKIE_VERSION = "version";
		String COOKIE_AUTH_CD = "authCd";
		String COOKIE_UCODE = "ucode";
		String COOKIE_USER_STATUS = "userStatus";
		String COOKIE_CELLPHONE = "qhgw_cp";

		String VERIFI_CODE = "verificodeMatch";

		String USER_STATUS = "qhgw_us";
	}

	interface Mobile {
		String SMOBILE_VERIFICODE = "123456";
	}

	interface Contribution {
		String ACTION1 = "1";
		String ACTION2 = "2";
	}

	interface MD5Constant {
		String QIAO_HU_XTH = "qiaohuxth";

		String QIAOHU_10TH_GIFT = "qiaohu10thgift";
	}

	interface EventInput {

		String SUBJECT_CONTRIBUTION = "subjectContribution";

		String SEARCH_CONTRIBUTION = "searchContribution";

		String REVIEW_STATUS = "0";// 待审核

		Integer SOURCE0 = 0;
		Integer SOURCE1 = 1;

		String DEFAULT_TARGET = "0";

		String SHARE_FLG = "0";

		String ACTION_KBN = "1";

		String ACTION_KBN1 = "2";

		String CATEGORY_ID = "6";
	}



	interface ApplyGift {
		String GIFT_NAME1 = "品牌H5分享有礼";

		String GIFT_NAME2 = "寻找巧虎H5分享有礼";

		String MOBILE_VALID = "0";
	}

	interface Comment {
		int PIC_LIMIT = 5;
	}

	interface Plan {
		/**
		 * 非公开
		 */
		int STATUS_UN_OPEN = 0;
		/**
		 * 待售
		 */
		int STATUS_SALING = 1;
		/**
		 * 当月
		 */
		int STATUS_CURRENT_MONTH = 2;
		/**
		 * 售罄
		 */
		int STATUS_SELLOUT = 3;
	}
}
