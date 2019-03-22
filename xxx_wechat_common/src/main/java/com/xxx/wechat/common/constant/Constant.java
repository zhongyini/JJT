package com.xxx.wechat.common.constant;

/**
 * 常量
 */
public interface Constant {
    public static String EMPTY = "";

    /**
     * 微信公众号接口url
     */
    public interface WechatUrl {

        public final static String BASE_API_URL = "https://api.weixin.qq.com/";

        // 获取access_token
        public final static String GET_ACCESS_TOKEN_GET = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s";

        // 获取jsapi_ticket
        public final static String GET_JSAPI_TICKET = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=%s&type=jsapi";

        // 获取api_ticket
        public final static String GET_API_TICKET = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=%s&type=wx_card";

        public final static String GET_QRCODE_TICKET = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=%s";

        // 设置所属行业
        public final static String SET_INDUSTRY_POST = "https://api.weixin.qq.com/cgi-bin/template/api_set_industry?access_token=%s";
        // 获取设置的行业信息
        public final static String GET_INDUSTRY_GET = "https://api.weixin.qq.com/cgi-bin/template/get_industry?access_token=%s";
        // 获得模板ID
        public final static String GET_TEMPLATE_ID_POST = "https://api.weixin.qq.com/cgi-bin/template/api_add_template?access_token=%s";
        // 获取模板列表
        public final static String GET_TEMPLATE_LIST_GET = "https://api.weixin.qq.com/cgi-bin/template/get_all_private_template?access_token=%s";
        // 删除模板
        public final static String DEL_PRIVATE_TEMPLATE_POST = "https://api.weixin.qq.com/cgi-bin/template/del_private_template?access_token=%s";
        // 发送模板消息
        public final static String SEND_TEMPLEATE_MESSAGE_POST = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=%s";

        // 获取用户列表
        public final static String GET_USER_OPENID_GET = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=%s&next_openid=%s";
        // 获取用户基本信息 lang:zh_CN 简体，zh_TW 繁体，en 英语 可以使用枚举类型:UserLang.java
        public final static String GET_USER_INFO_GET = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=%s&openid=%s&lang=%s";
        // 批量获取用户基本信息
        public final static String GET_USER_LIST_INFO_POST = "https://api.weixin.qq.com/cgi-bin/user/info/batchget?access_token=%s";
        // 设置用户备注名
        public final static String UPDATE_USER_REMARK_POST = "https://api.weixin.qq.com/cgi-bin/user/info/updateremark?access_token=%s";
        // 获取公众号的黑名单列表
        public final static String GET_BLACK_LIST_POST = "https://api.weixin.qq.com/cgi-bin/tags/members/getblacklist?access_token=%s";
        // 拉黑用户
        public final static String BATCH_BLACK_LIST_MEMBERS = "https://api.weixin.qq.com/cgi-bin/tags/members/batchblacklist?access_token=%s";
        // 取消拉黑用户
        public final static String BATCH_UNBLACK_LIST_MEMBERS_TAGS = "https://api.weixin.qq.com/cgi-bin/tags/members/batchunblacklist?access_token=%s";

        // 创建标签
        public final static String CREATE_TAGS_POST = "https://api.weixin.qq.com/cgi-bin/tags/create?access_token=%s";
        // 获取公众号已创建的标签
        public final static String GET_TAGS_GET = "https://api.weixin.qq.com/cgi-bin/tags/get?access_token=%s";
        // 编辑标签
        public final static String UPDATE_TAGS_POST = "https://api.weixin.qq.com/cgi-bin/tags/update?access_token=%s";
        // 删除标签
        public final static String DELETE_TAGS_POST = "https://api.weixin.qq.com/cgi-bin/tags/delete?access_token=%s";
        // 获取标签下粉丝列表
        public final static String GET_TAG_USER_GET = "https://api.weixin.qq.com/cgi-bin/user/tag/get?access_token=%s";

        // 批量为用户打标签
        public final static String BATCH_TAGGING_MEMBERS_TAG_POST = "https://api.weixin.qq.com/cgi-bin/tags/members/batchtagging?access_token=%s";
        // 批量为用户取消标签
        public final static String BATCH_UNTAGGING_MEMBERS_TAGS_POST = "https://api.weixin.qq.com/cgi-bin/tags/members/batchuntagging?access_token=%s";
        // 获取用户身上的标签列表
        public final static String GET_ID_LIST_TAGS_POST = "https://api.weixin.qq.com/cgi-bin/tags/getidlist?access_token=%s";

        // 自定义菜单创建接口
        public final static String CREATE_MENU_POST = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=%s";

        // 自定义菜单创建接口：个性化菜单未测试
        public final static String CREATE_MATCHRULE_MENU_POST = "https://api.weixin.qq.com/cgi-bin/menu/addconditional?access_token=%s";
        // 自定义菜单查询接口
        public final static String GET_MENU_GET = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=%s";

        // 上传图文消息内的图片获取URL
        public final static String ADD_MATERIAL_POST = "https://api.weixin.qq.com/cgi-bin/material/add_material?access_token=%s&type=%s";
        // 新增永久图文素材
        public final static String ADD_NEWS_MATERIAL_POST = "https://api.weixin.qq.com/cgi-bin/material/add_news?access_token=%s";

        // 客服接口-发消息:文本消息和图片消息已测试
        public final static String SEND_CUSTOM_MESSAGE_POST = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=%s";

        // 获取客服基本信息
        public final static String GETKFLIST_CUSTOMSERVICE_GET = "https://api.weixin.qq.com/cgi-bin/customservice/getkflist?access_token=%s";
        // 获取在线客服基本信息
        public final static String GETONLINEKFLIST_CUSTOMSERVICE_GET = "https://api.weixin.qq.com/cgi-bin/customservice/getonlinekflist?access_token=%s";
        // 获取客户会话状态
        public final static String GET_CUSTOMSERVICE_KESESSION_GET = "https://api.weixin.qq.com/customservice/kfsession/getsession?access_token=%s&openid=%s";
        // 获取客服会话列表
        public final static String GETSESSIONLIST_CUSTOMSERVICE_GET = "https://api.weixin.qq.com/customservice/kfsession/getsessionlist?access_token=%s&kf_account=%s";

        //获取卡券信息
        public final static String GETCOUPON_POST = "https://api.weixin.qq.com/card/code/get?access_token=%s";
        //卡券核销
        public final static String CARD_CANCELLATION_POST = "https://api.weixin.qq.com/card/code/consume?access_token=%s";

        // 根据OpenID列表群发
        public final static String SEND_MASS_MESSAGE_POST = "https://api.weixin.qq.com/cgi-bin/message/mass/send?access_token=%s";
        // 获取素材列表
        public final static String BATCH_GET_MATERIAL_POST = "https://api.weixin.qq.com/cgi-bin/material/batchget_material?access_token=%s";
        // 获取素材总数
        public final static String GET_MATERIAL_COUNT_GET = "https://api.weixin.qq.com/cgi-bin/material/get_materialcount?access_token=%s";

        // 第一步：用户同意授权，获取code
        public final static String AUTHORIZE_OAUTH2_CONNECT = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=%s&redirect_uri=%s&response_type=code&scope=%s&state=%s#wechat_redirect";
        public final static String AUTHORIZE_CONNECT_QRCONNECT = "https://open.weixin.qq.com/connect/qrconnect?appid=%s&redirect_uri=%s&response_type=code&scope=%s&state=%s#wechat_redirect";
        // 第二步：通过code换取网页授权access_token
        public final static String ACCESS_TOKEN_OAUTH2_GET = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code";
        // 通过refresh_token刷新access_token
        public final static String GET_OAUTH2_REFRESH_TOKEN = "https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=%s&grant_type=refresh_token&refresh_token=%s";

        // 拉取用户信息
        public final static String GET_SNS_USERINFO =  "https://api.weixin.qq.com/sns/userinfo?access_token=%s&openid=%s&lang=zh_CN";
        // 获取用户已领取卡券接口
        public final static String CARD_USER_GETCARDLIST = "https://api.weixin.qq.com/card/user/getcardlist?access_token=%s";
        // Code解码接口
        public final static String CARD_CODE_DECRYPT = "https://api.weixin.qq.com/card/code/decrypt?access_token=%s";

        // 二维码显示
        public final static String SHOW_QR_CODE = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=%s";
    }

    /**
     * 程序要访问的controller地址
     *
     * @author Administrator
     */
    interface ControllerUrl {

        public final static String COMMON_ERROR = "/common/error";

        public final static String COMMON_JUMP = "/common/jump";

        public final static String MENU_VOUCHER = "/menu/voucher";

        public final static String USER_LOGIN = "/user/login";

        public final static String CARD_VIEW = "/card/view";

        public final static String SHARE_VIEW = "/share/view";
        
        public final static String MENU_ONE_ONE = "/menu/one/one";
        public final static String MENU_ONE_TWO = "/menu/one/two";
        public final static String MENU_ONE_THREE = "/menu/one/three";
        public final static String MENU_ONE_FOUR = "/menu/one/four";
        public final static String MENU_ONE_FIVE = "/menu/one/five";
        public final static String MENU_TWO_ONE = "/menu/two/one";
        public final static String MENU_TWO_TWO = "/menu/two/two";
        public final static String MENU_TWO_THREE = "/menu/two/three";
        public final static String MENU_TWO_FOUR = "/menu/two/four";
        public final static String MENU_TWO_FIVE = "/menu/two/five";
        public final static String MENU_THREE_ONE = "/menu/three/one";
        public final static String MENU_THREE_TWO = "/menu/three/two";
        public final static String MENU_THREE_THREE = "/menu/three/three";
        public final static String MENU_THREE_FOUR = "/menu/three/four";
        public final static String MENU_THREE_FIVE = "/menu/three/five";
        
    }

    /**
     * 微信公众号参数
     *
     * @author TungS
     */
    interface WechatParams {
        // 弹出授权页面，可通过openid拿到昵称、性别、所在地。并且，即使在未关注的情况下，只要用户授权，也能获取其信息
        public final static String AUTHORIZE_SCOPE_USERINFO = "snsapi_userinfo";
        // 不弹出授权页面，直接跳转，只能获取用户openid
        public final static String AUTHORIZE_SCOPE_BASE = "snsapi_base";
        // 请求成功的errcode
        public final static String ERRCODE_SUCCESS = "0";
        //微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
        public final static String MSG_PARAM_SIGNATURE = "signature";
        //时间戳
        public final static String MSG_PARAM_TIMESTAMP = "timestamp";
        //随机数
        public final static String MSG_PARAM_NONCE = "nonce";
        //随机字符串
        public final static String MSG_PARAM_ECHOSTR = "echostr";
        //消息类型：text文本消息、event事件消息
        public static final String MSGTYPE = "MsgType";
        //事件类型，subscribe(订阅)、unsubscribe(取消订阅)
        public final static String EVENT = "Event";
        // zh_CN 简体
        public final static String ZH_CN = "zh_CN";
        // zh_TW 繁体
        public final static String ZH_TW = "zh_TW";
        // en 英语
        public final static String EN = "en";
        
        /**
         * 开发者微信号
         */
        public static final String TOUSERNAME = "ToUserName";
        /**
         * 发送方帐号（一个OpenID）
         */
        public static final String FROMUSERNAME = "FromUserName";
        /**
         * 二维码的ticket，可用来换取二维码图片
         */
        public static final String TICKET = "Ticket";
        /**
		 * 二维码场景值
		 */
		public final static String QRSCENE = "qrscene_";
        /**
         * 事件KEY值
         */
        public static final String EVENTKEY = "EventKey";
    }

    /**
     * 腾讯地图接口
     *
     * @author TungS
     */
    interface TencentMap {
        // 腾讯地图，由坐标到坐标所在位置的文字描述的转换
        public final static String MAP_GEOCODER = "http://apis.map.qq.com/ws/geocoder/v1/?location=%s&poi_options=address_format=short&key=%s";
    }

    /**
     * 系统配置表中字段item
     *
     * @author TungS
     */
    interface Configuration {
        public final static String APPID = "appid";
        public final static String APPSECRET = "appsecret";
        /**
         * 域名
         */
        public final static String HOST_URL = "hostUrl";
        /**
         * 一页显示数
         */
        public final static String LIST_PAGE_SIZE = "list_page_size";
        /**
         * state
         */
        public final static String STATE = "state";

        /**
         * 推荐数上限
         */
        public final static String REC_NUM_LIMIT = "rec_num_limit";
        
        /**
         * 领取数上限
         */
        public final static String GET_NUM_LIMIT = "get_num_limit";
        
        /**
         * 红包返点数
         */
        public final static String MONEY = "money";
        
        /**
         * 微信配置Token
         */
        public final static String SERVER_CONFIG_TOKEN = "server_config_token";
        
        /**
    	 * 微信个性化动态菜单标签id
    	 */
    	public static String WECHAT_TAG_ID = "wechat_tag_id";
    }

    interface Str {

        public final static String jump = "/oauth/url?redirectUrl=";

        public final static String http = "http://";

        /**
         * 字符串"0"
         */
        public final static String STR_ZERO = "0";
        
        /**
         * 字符串"1"
         */
        public final static String STR_ONE = "1";
        
        /**
         * 字符串"2"
         */
        public final static String STR_TWO = "2";
        
        /**
         * 字符串"3"
         */
        public final static String STR_THREE = "3";

        /**
         * 扫码关注的区分字段
         */
        public final static String SHARE_ONE = "share_one_";

        /**
         * 小写utf-8
         */
        public final static String UTF8 = "utf-8";
        
        /**
         * 大写UTF-8
         */
        public final static String BIG_UTF8 = "UTF-8";
        
        /**
         * 二维码文件名前缀
         */
        public final static String QR_ = "qr_";
        
        /**
         * 图片格式：png
         */
        public final static String PNG = "png";
        
        /**
         * 点.
         */
        public final static String POINT = ".";
        
        /**
         * 卡券标题
         */
        public final static String TITLE = "卡券名";
        
        /**
         * 卡券id
         */
        public final static String CARD_ID = "pKv1dwhhTop_f0vFBBjCDX8JhH2Y";
        
        /**
         * 九间堂新中医
         */
        public final static String SIGN_NAME = "九间堂新中医";
        
        /** token  */
		public static String HIS_TOKEN = "his-token";
    }

    interface Num {

    	public final static int INT_ZERO = 0;
		public final static int INT_ONE = 1;
		public final static int INT_TWO = 2;
		public final static int INT_THREE = 3;
		public final static int INT_FOUR = 4;
		public final static int INT_FIVE = 5;
		public final static int INT_SIX = 6;
		public final static int INT_SEVEN = 7;
		public final static int INT_EIGHT = 8;
		public final static int INT_NINE = 9;
		public final static int INT_TEN = 10;
		public final static int INT_ELEVEN = 11;
		public final static int INT_EIGHTEEN = 18;
		public final static int INT_TWENTY = 20;
		
		/** =============自定义客户端操作返回状态码 start================ **/
		/**
		 * 服务器成功返回网页200
		 */
		public static int SUCCESS = 0;
		/**
		 * 服务器处理失败返回码110
		 */
		public static int ERROR = 110;
		/**
		 * 没有登录返回码111
		 */
		public static int STATUS_NO_LOGIN = 111;
		/**
		 * 登录失败返回码112
		 */
		public static int STATUS_ERROR_LOGIN = 112;

		/**
		 * 没有权限返回码113
		 */
		public static int STATUS_NO_PERMISSION = 113;

		/**
		 * 非法访问返回码114
		 */
		public static int STATUS_NO_TOKEN = 114;
		
		/**
		 * 您已在其它地方登录，请重新登录返回码115
		 */
		public static int STATUS_RE_LOGIN = 115;
		
		/**
		 * 登录超时，请重新登录返回码116
		 */
		public static int STATUS_TOO_LONG_LOGIN = 116;

		/**
		 * 内部服务器错误500
		 */
		public static int STATUS_CODE_500 = 500;
		/**
		 * 内部服务器错误404
		 */
		public static int STATUS_CODE_403 = 403;
		/**
		 * 内部服务器错误404
		 */
		public static int STATUS_CODE_404 = 404;
		/** =============自定义客户端操作返回状态码 end================ **/

    }

    interface PaymentStatus {
        /**
         * 系统操作异常
         */
        String STATUS0 = "0";
        /**
         * 发放中
         */
        String ISSUING = "1";
        /**
         * 支付API发放成功
         */
        String SUCCESS = "2";
        /**
         * 支付API发放失败
         */
        String FAIL = "3";
    }

    interface PaymentApiStatus {
        /**
         * 支付API发放成功
         */
        Integer SUCCESS = 0;
        /**
         * 支付API发放失败
         */
        Integer FAIL = 1;
        /**
         * 系统异常
         */
        Integer ERROR = 2;
    }

    interface PaymentResultCode {
        /**
         * 系统异常
         */
        Integer CODE0 = 9999999;
        /**
         * 正常
         */
        Integer CODE1 = 0;
        /**
         * 账户不存在
         */
        Integer CODE2 = 100001;
        /**
         * 账户余额为0
         */
        Integer CODE3 = 100002;
    }

}
