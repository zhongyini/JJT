package com.jjt.wechat.core.constants;

/**
 * 信息查询：微信用户一览常量接口
 * @author bobliu
 *
 */
public interface Constants {
	
	/**
	 * 性别：未知--0
	 */
	public final static String  SEX_I = "未知";
	
	/**
	 * 性别：男--1
	 */
	public final static String  SEX_II = "男";
	
	/**
	 * 性别：女--2
	 */
	public final static String  SEX_III = "女";
	
	/**
	 * 是否在籍 在籍--0
	 */
	public final static String  USER_STATUS_FALSE = "在籍";
	
	/**
	 * 是否在籍：非在籍--1
	 */
	public final static String  USER_STATUS_TRUE = "非在籍";
	
	/**
	 * 是否关注：已关注--0
	 */
	public final static String  DELETE_FLAG_TRUE = "已关注";
	
	/**
	 * 是否关注：未关注--1
	 */
	public final static String  DELETE_FLAG_FALSE = "未关注";
	
	/**
	 * 零：0
	 */
	public final static String ZERO ="0";
	
	/**
	 * 初始状态：0
	 */
	public final static String STATUS_ZERO ="0";
	
	/**
	 * 一：1
	 */
	public final static String ONE ="1";
	
	/**
	 * 二：2
	 */
	public final static String TWO ="2";
	
	/**
	 * 三：3
	 */
	public final static String THREE ="3";
	
	/**
	 * 四：4
	 */
	public final static String FOUR ="4";
	
	/**
	 * 五：5
	 */
	public final static String FIVE ="5";
	
	
	/**
	 * 六：6
	 */
	public final static String SIX ="6";
	
	/**
	 * 七：2
	 */
	public final static String SEVEN ="7";
	
	
	/**
	 * 八：2
	 */
	public final static String EIGHT ="8";
	
	/**
	 * 九：9
	 */
	public final static String NINE ="9";
	
	/**
	 * 换行：\t
	 */
	public final static String LINE_BREAK ="\t";
	
	/**
	 * 空字符串：""
	 */
	public final static String BLANK_STR ="";
	
	/**
	 * 插入数据成功 :1
	 */
	public final static int INSERT_SUCCESS = 1;
	
	/**
	 * 插入数据是败：-1
	 */
	public final static int INSERT_FAIL = -1;
	
	/**
	 * 无群发消息结果:0
	 */
	public final static int NO_RESULT = 0;
	
	/**
	 * 整数 :1
	 */
	public final static int INT_ONE = 1;
	/**
	 * 整数 :2
	 */
	public final static int INT_TWO = 2;
	/**
	 * 整数 :3
	 */
	public final static int INT_THREE = 3;
	/**
	 * 整数 :4
	 */
	public final static int INT_FOUR = 4;
	/**
	 * 整数 :5
	 */
	public final static int INT_FIVE = 5;
	/**
	 * 整数 :6
	 */
	public final static int INT_SIX = 6;
	/**
	 * 整数 :7
	 */
	public final static int INT_SEVEN = 7;
	
	/**
	 * 第一次推送  整数 :1
	 */
	public final static int SENDTIME_ONE = 1;
	
	/**
	 * 整数：-1
	 */
	public final static int INT_MINUS_ONE = -1;
	
	/**
	 * 整数：0
	 */
	public final static int INT_ZERO = 0;
	/**
	 * 整数：8
	 */
	public final static int INT_EIGHT = 8;
	/**
	 * 整数：10
	 */
	public final static int INT_TEN = 10;
	
	/**
	 * redirect重定向
	 */
	public static String REDIRECT = "redirect:";
	
	/**
	 * 上传完成的文件后缀:   .done
	 */
	public static String FILE_SUFFIX =".done";
	
	/**
	 * 日期格式：yyyy-MM-dd
	 */
	public static String DATE_FORMAT_1 ="yyyy-MM-dd";
	
	/**
	 * 数据删除的最大期限：36
	 */
	public static int DELETE_DAYS_MAXLIMIT = 36;
	
	/**
	 * 活动ID：1
	 */
	public static int ACTION_ID_ONE = 1;
	/**
	 * 活动ID：2
	 */
	public static int ACTION_ID_TWO = 2;
	/**
	 * 活动ID：3
	 */
	public static int ACTION_ID_THREE = 3;
	/**
	 * 活动ID：4
	 */
	public static int ACTION_ID_FOUR = 4;
	/**
	 * 活动ID：5
	 */
	public static int ACTION_ID_FIVE = 5;
	/**
	 * 活动名称1
	 */
	public static String ACTION_NAME_ONE = "action1";
	/**
	 * 活动名称2:action2
	 */
	public static String ACTION_NAME_TWO = "action2";
	/**
	 * 活动名称3:action3
	 */
	public static String ACTION_NAME_THREE = "action3";
	/**
	 * 活动名称4:action4
	 */
	public static String ACTION_NAME_FOUR = "action4";
	/**
	 * 活动名称5:action5
	 */
	public static String ACTION_NAME_FIVE = "action5";
	/**
	 * 活动名称6:action6
	 */
	public static String ACTION_NAME_SIX = "action6";
	/**
	 * 活动主题1:微信绑定活动
	 */
	public static String ACTION_MEMO_ONE = "微信绑定活动";
	/**
	 * 活动主题2:有奖问答活动
	 */
	public static String ACTION_MEMO_TWO = "有奖问答活动";
	/**
	 * 活动主题3:服务号一月H5活动
	 */
	public static String ACTION_MEMO_THREE = "服务号一月H5活动";
	/**
	 * 活动积分1:2000
	 */
	public static int ACTION_INTEGRAL_ONE = 2000;
	/**
	 * 活动积分2:4000
	 */
	public static int ACTION_INTEGRAL_TWO = 4000;
	/**
	 * 活动积分3:600
	 */
	public static int ACTION_INTEGRAL_THREE = 600;
	/**
	 * 活动积分3:200
	 */
	public static int TWO_HUNDRED = 200;
	
	/**
	 * webId
	 */
	public static String WEBID = "webId";
	/**
	 * token
	 */
	public static String TICKET = "ticket";
	/**
	 * reasonId
	 */
	public static String REASONID = "reasonId";
	/**
	 * ticket保存路径
	 */
	public static String QRCODE = "qrcode";
	/**
	 * 天津
	 */
	public static String TIANJIN = "天津";
	/**
	 * 唐山
	 */
	public static String TANGSHAN = "唐山";
	/**
	 * utf-8
	 */
	public static String UTF8 = "utf-8";
	
	/**
	 * recommendExt
	 */
	public static String RECOMMEND_EXT = "recommendExt";
	/**
	 * 服务号签到:17
	 */
	public static String FROMTYPE_SEVENTEEN = "17";
	/**
	 * 服务号活动:18
	 */
	public static String FROMTYPE_EIGHTEEN = "18";
	/**
	 * 注册登录送积分:19
	 */
	public static String FROMTYPE_NINETEEN = "19";
	/**
	 * 下载微信二维码路径
	 */
	public static String QR_URL = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=";
	/**
	 * 保存二维码路径
	 */
//	public static String QR_PATH = "/var/qiaohu/webapps/ROOT/WEB-INF/classes/static/img/qrcode";
	
	public static String QR_PATH = "/var/qiaohu/data/images/qrcode";
	
//	public static String QR_PATH = "D:\\aaa\\workspace\\qiaohu_wechat_front\\target\\wechat-front\\WEB-INF\\classes\\static\\img\\qrcode";
	
	/**
	 * 一月活动的场景id
	 */
	public static String QRSCENE_23 = "qrscene_23";
	
	public final static String TWENTY_THREE ="23";
	
	
	/**
	 * 图片格式：.jpg
	 */
	public static String _JPG = ".jpg";
	
	public static String _POST_JPG = "_post.jpg";
	
	/**
	 * 关注
	 */
	public static String SUBSCRIBE = "关注";
	/**
	 * 取消关注
	 */
	public static String UNSUBSCRIBE = "取消关注";
	/**
	 * 登录
	 */
	public static String LOGIN = "登录";
	/**
	 * 退出登录
	 */
	public static String LOGOUT = "退出登录";
	/**
	 * 活动参与通知类型：1：活动将要完成通知内容
	 */
	public static String ACTIVITY_CONTENT_TYPE_COMPLETED = "1";
	/**
	 * 活动参与通知类型：2：活动即将结束通知内容
	 */
	public static String ACTIVITY_CONTENT_TYPE_END = "2";

}
