package com.jjt.wechat.common.constant;

/**
 * 常量
 * 
 */
public interface Constant {
	String EMPTY = "";

	/**
	 * 微信公众号接口url
	 * 
	 */
	interface WechatUrl {
		
		String BASE_API_URL = "https://api.weixin.qq.com/";
		
		// 获取access_token
		String GET_ACCESS_TOKEN_GET = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s";

		// 设置所属行业
		String SET_INDUSTRY_POST = "https://api.weixin.qq.com/cgi-bin/template/api_set_industry?access_token=%s";
		// 获取设置的行业信息
		String GET_INDUSTRY_GET = "https://api.weixin.qq.com/cgi-bin/template/get_industry?access_token=%s";
		// 获得模板ID
		String GET_TEMPLATE_ID_POST = "https://api.weixin.qq.com/cgi-bin/template/api_add_template?access_token=%s";
		// 获取模板列表
		String GET_TEMPLATE_LIST_GET = "https://api.weixin.qq.com/cgi-bin/template/get_all_private_template?access_token=%s";
		// 删除模板
		String DEL_PRIVATE_TEMPLATE_POST = "https://api.weixin.qq.com/cgi-bin/template/del_private_template?access_token=%s";
		// 发送模板消息
		String SEND_TEMPLEATE_MESSAGE_POST = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=%s";

		// 获取用户列表
		String GET_USER_OPENID_GET = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=%s&next_openid=%s";
		// 获取用户基本信息 lang:zh_CN 简体，zh_TW 繁体，en 英语 可以使用枚举类型:UserLang.java
		String GET_USER_INFO_GET = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=%s&openid=%s&lang=%s";
		// 批量获取用户基本信息
		String GET_USER_LIST_INFO_POST = "https://api.weixin.qq.com/cgi-bin/user/info/batchget?access_token=%s";
		// 设置用户备注名
		String UPDATE_USER_REMARK_POST = "https://api.weixin.qq.com/cgi-bin/user/info/updateremark?access_token=%s";
		// 获取公众号的黑名单列表
		String GET_BLACK_LIST_POST = "https://api.weixin.qq.com/cgi-bin/tags/members/getblacklist?access_token=%s";
		// 拉黑用户
		String BATCH_BLACK_LIST_MEMBERS = "https://api.weixin.qq.com/cgi-bin/tags/members/batchblacklist?access_token=%s";
		// 取消拉黑用户
		String BATCH_UNBLACK_LIST_MEMBERS_TAGS = "https://api.weixin.qq.com/cgi-bin/tags/members/batchunblacklist?access_token=%s";

		// 创建标签
		String CREATE_TAGS_POST = "https://api.weixin.qq.com/cgi-bin/tags/create?access_token=%s";
		// 获取公众号已创建的标签
		String GET_TAGS_GET = "https://api.weixin.qq.com/cgi-bin/tags/get?access_token=%s";
		// 编辑标签
		String UPDATE_TAGS_POST = "https://api.weixin.qq.com/cgi-bin/tags/update?access_token=%s";
		// 删除标签
		String DELETE_TAGS_POST = "https://api.weixin.qq.com/cgi-bin/tags/delete?access_token=%s";
		// 获取标签下粉丝列表
		String GET_TAG_USER_GET = "https://api.weixin.qq.com/cgi-bin/user/tag/get?access_token=%s";

		// 批量为用户打标签
		String BATCH_TAGGING_MEMBERS_TAG_POST = "https://api.weixin.qq.com/cgi-bin/tags/members/batchtagging?access_token=%s";
		// 批量为用户取消标签
		String BATCH_UNTAGGING_MEMBERS_TAGS_POST = "https://api.weixin.qq.com/cgi-bin/tags/members/batchuntagging?access_token=%s";
		// 获取用户身上的标签列表
		String GET_ID_LIST_TAGS_POST = "https://api.weixin.qq.com/cgi-bin/tags/getidlist?access_token=%s";

		// 自定义菜单创建接口
		String CREATE_MENU_POST = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=%s";

		// 自定义菜单创建接口：个性化菜单未测试
		String CREATE_MATCHRULE_MENU_POST = "https://api.weixin.qq.com/cgi-bin/menu/addconditional?access_token=%s";
		// 自定义菜单查询接口
		String GET_MENU_GET = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=%s";

		// 上传图文消息内的图片获取URL
		String ADD_MATERIAL_POST = "https://api.weixin.qq.com/cgi-bin/material/add_material?access_token=%s&type=%s";
		// 新增永久图文素材
		String ADD_NEWS_MATERIAL_POST = "https://api.weixin.qq.com/cgi-bin/material/add_news?access_token=%s";

		// 客服接口-发消息:文本消息和图片消息已测试
		String SEND_CUSTOM_MESSAGE_POST = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=%s";

		// 获取客服基本信息
		String GETKFLIST_CUSTOMSERVICE_GET = "https://api.weixin.qq.com/cgi-bin/customservice/getkflist?access_token=%s";
		// 获取在线客服基本信息
		String GETONLINEKFLIST_CUSTOMSERVICE_GET = "https://api.weixin.qq.com/cgi-bin/customservice/getonlinekflist?access_token=%s";
		// 获取客户会话状态
		String GET_CUSTOMSERVICE_KESESSION_GET = "https://api.weixin.qq.com/customservice/kfsession/getsession?access_token=%s&openid=%s";
		// 获取客服会话列表
		String GETSESSIONLIST_CUSTOMSERVICE_GET = "https://api.weixin.qq.com/customservice/kfsession/getsessionlist?access_token=%s&kf_account=%s";

		// 根据OpenID列表群发
		String SEND_MASS_MESSAGE_POST = "https://api.weixin.qq.com/cgi-bin/message/mass/send?access_token=%s";
		// 获取素材列表
		String BATCH_GET_MATERIAL_POST = "https://api.weixin.qq.com/cgi-bin/material/batchget_material?access_token=%s";
		// 获取素材总数
		String GET_MATERIAL_COUNT_GET = "https://api.weixin.qq.com/cgi-bin/material/get_materialcount?access_token=%s";

		// 第一步：用户同意授权，获取code
		String AUTHORIZE_OAUTH2_CONNECT = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=%s&redirect_uri=%s&response_type=code&scope=%s&state=%s#wechat_redirect";
		String AUTHORIZE_CONNECT_QRCONNECT = "https://open.weixin.qq.com/connect/qrconnect?appid=%s&redirect_uri=%s&response_type=code&scope=%s&state=%s#wechat_redirect";
		// 第二步：通过code换取网页授权access_token
		String ACCESS_TOKEN_OAUTH2_GET = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code";
		// 通过refresh_token刷新access_token
		String GET_OAUTH2_REFRESH_TOKEN = "https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=%s&grant_type=refresh_token&refresh_token=%s";
		
	}
	
	/**
	 * 程序要访问的controller地址
	 * @author Administrator
	 *
	 */
	interface ControllerUrl {
		// 券
		String MENU_VOUCHER = "/menu/voucher";
		
	}

	/**
	 * 微信公众号参数
	 * 
	 * @author TungS
	 *
	 */
	interface WechatParams {
		// 弹出授权页面，可通过openid拿到昵称、性别、所在地。并且，即使在未关注的情况下，只要用户授权，也能获取其信息
		String AUTHORIZE_SCOPE_USERINFO = "snsapi_userinfo";
		// 不弹出授权页面，直接跳转，只能获取用户openid
		String AUTHORIZE_SCOPE_BASE = "snsapi_base";
		// 请求成功的errcode
		String ERRCODE_SUCCESS = "0";
		//微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
		String MSG_PARAM_SIGNATURE = "signature";
		//时间戳
		String MSG_PARAM_TIMESTAMP = "timestamp";
		//随机数
		String MSG_PARAM_NONCE = "nonce";
		//随机字符串
		String MSG_PARAM_ECHOSTR = "echostr";
		//微信配置Token
		String SERVER_CONFIG_TOKEN="server_config_token";
		//消息类型：text文本消息、event事件消息
		public static final String MSGTYPE = "MsgType";
		//事件类型，subscribe(订阅)、unsubscribe(取消订阅)
		public static final String EVENT = "Event";
		//事件KEY值
		public static final String EVENTKEY = "EventKey";
		// zh_CN 简体
		public static final String ZH_CN = "zh_CN";
		// zh_TW 繁体
		public static final String ZH_TW = "zh_TW";
		// en 英语
		public static final String EN = "en";
	}

	/**
	 * 腾讯地图接口
	 * 
	 * @author TungS
	 *
	 */
	interface TencentMap {
		// 腾讯地图，由坐标到坐标所在位置的文字描述的转换
		String MAP_GEOCODER = "http://apis.map.qq.com/ws/geocoder/v1/?location=%s&poi_options=address_format=short&key=%s";
	}

	/**
	 * 系统配置表中字段item
	 * 
	 * @author TungS
	 *
	 */
	interface Configuration {
		String APPID = "appid";
		String APPSECRET = "appsecret";
		/**
		 * 域名
		 */
		String HOST_URL = "hostUrl";
		/**
		 * state
		 */
		String STATE = "state";
	}

}
