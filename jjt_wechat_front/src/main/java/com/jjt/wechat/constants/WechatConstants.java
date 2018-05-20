package com.jjt.wechat.constants;

public interface WechatConstants {

	interface View {
		
		/**
		 * URL_REDIRECT
		 * 重定向
		 */
		public final static String URL_REDIRECT = "redirect:";
		
		/**
		 * 优惠券视图
		 */
		public final static String COUPON = "coupon/coupon";
		/**
		 * 优惠券视图-无优惠券
		 */
		public final static String COUPON_NON = "coupon/couponNon";
		/**
		 * 会员卡视图
		 */
		public final static String MEMBERSHIPCARD = "membership/membershipcard";
		/**
		 * 会员卡视图-非在籍
		 */
		public final static String MEMBERSHIPCARD_NON = "membership/membershipcardNon";
		/**
		 * 用户登录视图
		 */
		public final static String USER_LOGIN = "user/login";
		/**
		 * 用户注册视图-验证
		 */
		public final static String USER_REGISTER_VERIFY = "user/registerVerify";
		/**
		 * 用户注册视图
		 */
		public final static String USER_REGISTER = "user/register";
		/**
		 * 注册活动action0
		 */
		public final static String USER_BIND_ACTION0 = "/user/bind0";
		/**
		 * 登陆成功活动action1
		 */
		public final static String USER_BIND_ACTION1 = "/user/bind1";

		/**
		 * 注册活动0
		 */
		public final static String USER_BIND_ACTIVITY0 = "user/userBind0";
		/**
		 * 注册活动1user/userBind1
		 */
		public final static String USER_BIND_ACTIVITY1 = "user/userBind1";
		/**
		 * 注册活动2user/userBind2
		 */
		public final static String USER_BIND_ACTIVITY2 = "user/userBind2";
		/**
		 * 注册活动3user/userBind3
		 */
		public final static String USER_BIND_ACTIVITY3 = "user/userBind3";
		/**
		 * 巧虎福利：有奖问答
		 */
		public final static String ANSWER_BEGIN = "/answer/begin";
		
		public final static String ANSWER_RESULT = "/answer/result";
		
		/**
		 * 巧虎福利：四月庆生活动    add by lm 20180313
		 */
		public final static String APRILBIRTH_BEGIN = "/aprilbirth/begin";
		
		public final static String APRILBIRTH_NOINDEX = "/aprilbirth/noindex";
		
		public final static String APRILBIRTH_RESULT = "/aprilbirth/result";
		
		/**
		 * 巧虎福利：有奖问答非在籍和没有ucode
		 */
		public final static String ANSWER_ANSWER02 = "/answer/answer02";
		
		public final static String USER_QHFLBIND = "/user/qhflbind";
		
		
		//巧虎福利：一月活动
		/**
		 * 会员主页
		 */
		public final static String JANUARY_INDEX = "/january/index";
		/**
		 * 非在籍会员主页
		 */
		public final static String JANUARY_NOINDEX = "/january/noindex";
		/**
		 * 选择理由画面跳转到 600积分画面 或者 区域限制画面
		 */
		public final static String JANUARY_GENERATE = "/january/generate";
		/**
		 * 天津、唐山会员主页
		 */
		public final static String JANUARY_AREAINDEX = "/january/areaindex";
		/**
		 * 领取600积分画面
		 */
		public final static String JANUARY_GETINTEGRAL = "/january/getintegral";
		
		/**
		 * 有被推荐人的推荐人积分详细画面
		 */
		public final static String JANUARY_HAVEINTEGRAL = "/january/haveintegral";
		
		/**
		 * 查询积分
		 */
		public final static String JANUARY_QUERYINTEGRAL = "/january/queryIntegrals";
		
		/**
		 * 没有被推荐人的推荐人积分详细画面
		 */
		public final static String JANUARY_NOINTEGRAL = "/january/nointegral";
		
		/**
		 * 推荐理由画面
		 */
		public final static String JANUARY_REASON = "/january/reason";
		/**
		 * 海报画面
		 */
		public final static String JANUARY_POSTER = "/january/poster";
		
		
		
		/**
		 * 巧虎福利：活动名称1
		 */
		public final static String ACTION_NAME1 = "action1";
		
		/**
		 * 巧虎福利：活动名称2
		 */
		public final static String ACTION_NAME2 = "action2";
		
		/**
		 * 巧虎福利：活动名称3
		 */
		public final static String ACTION_NAME3 = "action3";
		
		/**
		 * 巧虎福利：活动名称4
		 */
		public final static String ACTION_NAME4 = "action4";
		
		/**
		 * 巧虎福利：活动名称5
		 */
		public final static String ACTION_NAME5 = "action5";
		
		/**
		 * 巧虎福利：活动名称6
		 */
		public final static String ACTION_NAME6 = "action6";
		
		/**
		 * 巧虎福利：活动名称7
		 */
		public final static String ACTION_NAME7 = "action7";
		
		/**
		 * 巧虎福利：活动名称8
		 */
		public final static String ACTION_NAME8 = "action8";

		/**
		 * 全局错误地址
		 */
		public final static String ERROR_ACTION = "/menu/error";
		/**
		 * 全局错误视图
		 */
		public final static String ERROR = "/common/error";
		public final static String ERROR_NO_WX = "/common/errorNoWX";
		/**
		 * 重定向视图
		 */
		public final static String REDIRECT = "common/redirect";
		/**
		 * 内部链接登录
		 */
		public final static String USER_OAUTH_LOGIN = "/user/oauthLogin";

		/**
		 * action内部消息
		 */
		public final static String MESG = "mesg";
		
		/**
		 * ExperienceController
		 * 非会员用户体验视图
		 */
		
		public final static String REDIRECT_EXPERIENCE_INDEX = "redirect:/experience/index?flag=";
		public final static String EXPERIENCE_LOGIN = "/experience/login";
	}

	interface QiaoHuAPI {
		/**
		 * session USER
		 */
		public final static String USER = "USER";

		public final static String USERBINDSTATUS = "USERBINDSTATUS";
		
		public final static String OPENID = "OPENID";

		public final static String SERVLET_PATH = "SERVLET_PATH";

		/**
		 * 巧虎基干系登录API
		 */
		public final static String QH_API_LOGIN = "";

		public final static String QH_API_REGISTER = "";

		public final static String QH_API_SEND_SMS = "";

		/**
		 * API巧虎用户信息
		 */
		public static final String KEY_QIAOHU_USER_ID = "qiaohu_userId";

		/**
		 * API返回正常结果
		 */
		public static final int REQUEST_OK = 1;
		/**
		 * API返回异常结果
		 */
		public static final String REQUEST_FAIL = "0";
		/**
		 * API返回信息
		 */
		public static final String KEY_MESSAGE = "message";
		/**
		 * API头部cookies
		 */
		public static final String KEY_HEADER = "Set-Cookie";
		/**
		 * API返回状态
		 */
		public static final String KEY_STATUS = "status";
		public static final String KEY_APPLY_PACK_COUNT = "count";
		public static final String ERROR_FIELD = "errorField";

		/**
		 * cookie
		 */
		String COOKIE_MOBILE_TEMP = "mobile_temp";

		int TIMEOUT = 300; // 登陆超时设置(秒)

		int TIME_SESSION_OUT = -1; // tomcat默认session存活时间
		/**
		 * 一年
		 */
		int USER_INFO_TIMEOUT = 365 * 24 * 60 * 60;

		// String COOKIE_DOMAIN = "imwork.net";
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

	interface KeFu {
		// 客服接入状态区分
		public static final String NOACCESS = "0";
		public static final String ACCESS = "1";
		public static final String FINISH = "2";
	}

	interface ButtonKey {
		// 我的
		public static final String WD = "WD";
		// 巧虎小帮手
		public static final String QHXBS = "QHXBS";
		// 活动福利
		public static final String HDFL = "HDFL";

		// 会员卡
		public static final String HYK = "HYK";
		// 巧虎福利
		public static final String QHFL = " QHFL";
		// 商品使用说明
		public static final String SPSYZD = "SPSYZD";
		// 线上活动
		public static final String XSHD = "XSHD";
		// 线下活动
		public static final String XXHD = "XXHD";
		// 查物流
		public static final String WDWL = "WDWL";
		// 我的积分
		public static final String WDJF = "WDJF";
		// 改地址
		public static final String GDZ = "GDZ";
		// 我的优惠券
		public static final String WDYHQ = "WDYHQ";
		// 查附近巧虎专柜
		public static final String CFJQHZG = "CFJQHZG";
		// 常见问题
		public static final String CJWT = "CJWT";
		// 人工客服
		public static final String RGKF = "RGKF";
		// 订购&续订
		public static final String DGXD = "DGXD";
		// 巧虎世界地图
		public static final String QHSJDT = "QHSJDT";
	}

	interface Config {
		public static final String MSG_PARAM_SIGNATURE = "signature";
		public static final String MSG_PARAM_TIMESTAMP = "timestamp";
		public static final String MSG_PARAM_NONCE = "nonce";
		public static final String MSG_PARAM_ECHOSTR = "echostr";

		public static final String WECHAT_CONTENT_TYPE = "text/xml;charset=UTF-8";

		// 接收消息
		/**
		 * 开发者微信号
		 */
		public static final String TOUSERNAME = "ToUserName";
		/**
		 * 发送方帐号（一个OpenID）
		 */
		public static final String FROMUSERNAME = "FromUserName";
		/**
		 * 消息创建时间 （整型）
		 */
		public static final String CREATETIME = "CreateTime";
		/**
		 * 文本消息内容
		 */
		public static final String CONTENT = "Content";
		/**
		 * 消息id，64位整型
		 */
		public static final String MSGID = "MsgId";
		/**
		 * 事件类型，subscribe(订阅)、unsubscribe(取消订阅)
		 */
		public static final String EVENT = "Event";
		/**
		 * 消息类型：text文本消息、event事件消息
		 */
		public static final String MSGTYPE = "MsgType";
		/**
		 * 二维码的ticket，可用来换取二维码图片
		 */
		public static final String TICKET = "Ticket";
		/**
		 * 事件KEY值
		 */
		public static final String EVENTKEY = "EventKey";
		/**
		 * 群发的消息ID
		 */
		public static final String MASS_MSGID = "MsgID";
		/**
		 * 群发的结构
		 */
		public static final String MASS_STATUS = "Status";
		/**
		 * 粉丝数
		 */
		public static final String MASS_TOTALCOUNT = "TotalCount";
		/**
		 * 过滤
		 */
		public static final String MASS_FILTERCOUNT = "FilterCount";
		/**
		 * 发送成功的粉丝数
		 */
		public static final String MASS_SENTCOUNT = "SentCount";
		/**
		 * 发送失败的粉丝数
		 */
		public static final String MASS_ERRORCOUNT = "ErrorCount";
		/**
		 * 指定会话接入的客服账号
		 */
		public static final String KFACCOUNT = "KfAccount";
		public static final String CLOSETYPE = "closeType";
		/**
		 * 扫码关注
		 */
		public static final String QRSCENE = "qrscene_";
		/**
		 * 可用积分
		 */
		public static final String AVAILABLE_POINTS = "available_points";
		/**
		 * 已用积分
		 */
		public static final String USED_POINTS = "used_points";
		/**
		 * 冻结积分
		 */
		public static final String FREEZE_POINTS = "freeze_points";

		/**
		 * 积分是K31和K35不推送
		 */
		public static String UNSEND_FROMCODE_K31 = "K31";
		/**
		 * 积分是K31和K35不推送
		 */
		public static String UNSEND_FROMCODE_K35 = "K35";
		/**
		 * 换行符
		 */
		public static String LINE_SIGN = "\n";

		public static String PERCENT_SIGN = "%";
	}
	
	interface Coupon{
		String AROUND_COU = "周边";
		String BIRTH_COU = "生日";
		String SHOW_COU = "舞台剧";
		String TICKET_COU = "售票演出";
		String INTEGRAL_COU = "积分";
		String INTEGRAL_EX = "积分兑换券";
		String HLD_COU = "巧虎欢乐岛优惠券";
		String HLD_EXPERIENCE = "巧虎欢乐岛体验券";
		String COUPON = "优惠券";
		String EX = "兑换券";
		String EXPERIENCE = "巧虎欢乐岛体验券";
		String EXCHANGED = "兑换券已使用";
	}
	
	interface LocalAction{
		String FROM_USER_NAME = "FromUserName";
		String LOCATION_X = "Location_X";
		String LOCATION_Y = "Location_Y";
		String LOCAL_END[] = {"维吾尔自治区","回族自治区","壮族自治区","特别行政区","自治区","省","市","维吾尔","回族","壮族"};
	}
	
	interface Str{
		public static String SIGN_SUCCESS = "签到成功";
		public static String MESSAGE = "message";
		public static String WEBID = "webId";
		public static String STATUS = "status";
		public static String ONE = "1";
		
	}
}
