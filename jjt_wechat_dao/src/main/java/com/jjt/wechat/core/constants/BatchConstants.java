package com.jjt.wechat.core.constants;

public interface BatchConstants {

	/**
	 * CSV文件分隔符
	 */
	public static String CSV_SPLIT_SYMBOL = ",";

	/**
	 * CSV文件换行符
	 */
	public static String CSV_LINE_FEED = "\r\n";

	/**
	 * CSV文件类型：发货提醒
	 */
	public static String DATA_FILE_TYPE_DELIVER = "2";

	/**
	 * CSV文件内容字段数：发货提醒
	 */
	public static int FIELD_NUMBER_DELIVER = 7;
	/**
	 * CSV文件类型：会员到期提醒
	 */
	public static String DATA_FILE_TYPE_MEMBER_EXPIRED = "3";

	/**
	 * CSV文件内容字段数：会员到期提醒
	 */
	public static int FIELD_NUMBER_MEMBER_EXPIRED = 4;

	/**
	 * CSV文件类型：生日提醒
	 */
	public static String DATA_FILE_TYPE_BIRTHDAY = "4";

	/**
	 * CSV文件内容字段数：生日提醒
	 */
	public static int FIELD_NUMBER_BIRTHDAY = 5;

	/**
	 * CSV文件类型：积分到期提醒
	 */
	public static String DATA_FILE_TYPE_INTEGRAL_EXPIRED = "5";

	/**
	 * CSV文件内容字段数：积分到期提醒
	 */
	public static int FIELD_NUMBER_INTEGRAL_EXPIRED = 5;
	
	
	/**
	 * CSV文件类型：order用户csv
	 */
	public static String DATA_FILE_TYPE_ORDER = "6";
	
	/**
	 * CSV文件类型：特殊用户csv
	 */
	public static String DATA_FILE_TYPE_SUORDER = "7";
	
	/**
	 * CSV文件类型：ucode导入群发
	 */
	public static String DATA_FILE_TYPE_MASSUCODE = "8";
	
	/**
	 * csv文件类型：csv文件导入发送服务变更消息
	 */
	public static String DATA_FILE_TYPE_SERCHG = "9";
	
	
	/**
	 * 共同的模板消息：消息长度至少4位
	 */
	public static int FIELD_TEMPLATE = 5;
	/**
	 * 共同模板消息的文件类型
	 */
	public static String DATA_FILE_TYPE_Template = "99";

	/**
	 * CSV文件导入状态：初期化
	 */
	public static int DATA_FILE_IMPORT_STATUS_INIT = 0;

	/**
	 * CSV文件导入状态：处理中
	 */
	public static int DATA_FILE_IMPORT_STATUS_PROGRESS = 1;
	/**
	 * CSV文件导入状态：解析文件失败
	 */
	public static int DATA_FILE_IMPORT_STATUS_FAILED = 2;

	/**
	 * CSV文件导入状态：文件重新解析机制
	 */
	public static int DATA_FILE_IMPORT_STATUS_RETRY = 3;

	/**
	 * CSV文件导入状态：成功
	 */
	public static int DATA_FILE_IMPORT_STATUS_SUCCESS = 9;

	/**
	 * 提交事物条数
	 */
	public static int COMMIT_ROW_NUM = 1000;

	/**
	 * CSV文件解析成功
	 */
	public static int CSV_PARSE_SUCCESS = 0;

	/**
	 * csv文件格式错误，跳过继续解析
	 */
	public static int CSV_PARSE_FAIL = 1;

	/**
	 * csv文件插入数据库失败，重新解析文件
	 */
//	public static int CSV_PARSE_FAIL_RETRY = 2;

	/**
	 * Batch运行开始：0
	 */
	public static String BATCH_RUNSTATUS_START = "0";

	/**
	 * Batch正常完成：1
	 */
	public static String BATCH_RUNSTATUS_OVER = "1";

	/**
	 * Batch异常：2
	 */
	public static String BATCH_RUNSTATUS_EXCEPTION = "2";

	/**
	 * 通过openId群发数量：9800
	 */
	public static Integer MASS_MESSAGE_BY_OPENID_MAX = 9800;
	
	/**
	 * 积分变动提醒：未发送 0
	 */
	public static String INTEGRAL_CHANGE_UNSEND = "0";
	/**
	 * 积分变动提醒：发送中 1
	 */
	public static String INTEGRAL_CHANGE_SENDING = "1";
	/**
	 * 消息：没有openId，或者openId未关注 2
	 */
	public static String WECHAT_UNOPENID = "2";
	/**
	 * 消息：发送失败 3
	 */
	public static String WECHAT_SEND_FAILED = "3";
	/**
	 * 消息：特定不发送人群
	 */
	public static String WECHAT_UNSEND_PERSON = "4";
	/**
	 * 消息：没有template ID
	 */
	public static String WECHAT_UNTEMPLATE_ID= "5";
	/**
	 * 消息：没有头部消息
	 */
	public static String WECHAT_UNFIRST= "6";
	/**
	 * 消息：没有尾部消息
	 */
	public static String WECHAT_UNREMARK= "8";
	/**
	 * 消息：发送完毕 9
	 */
	public static String WECHAT_SEND_OVER = "9";
	
	public static String WECHAT_ERROR_CODE_0 ="0";
	//积分是K31和K35不推送
	public static String UNSEND_STARTWITH_K31 = "K31";
	//积分是K31和K35不推送
	public static String UNSEND_STARTWITH_K35 = "K35";
	//开头为K的生日月都不推送
	public static String UNSEND_STARTWITH_K = "K";
	
	public static String DELETE_FLAG_1 = "1";
	
	public static String DELETE_FLAG_0 = "0";
	
	/**
	 * 商品区分，主题商品 0
	 */
	public static int PROD_THEMED_FALG = 0;
	/**
	 * 商品区分，周边商品 1
	 */
	public static int PROD_PERIPHERAL_FALG = 1;
	/**
	 * 商品区分，单品 2
	 */
	public static int PROD_SINGLE_FALG = 2;
	
	/**
	 * 在籍状态
	 */
	public static String IS_MEMBERSHIP = "0";
	/**
	 * 非在籍状态
	 */
	public static String NON_MEMBERSHIP = "1";
	/**
	 * 在籍天数
	 */
	public static int MEMBERSHIP_DAYS = 45;
	
	/**
	 * 发送失败：-1
	 */
	public static int SEND_FAIL = -1;
	
	/**
	 * batch Name
	 */
	public static String BATCH_CSV_DELIVER = "发货提醒CSV导入";
	public static String BATCH_CSV_MEMBER = "会员到期提醒CSV导入";
	public static String BATCH_CSV_INTEGRAL = "积分到期提醒CSV导入";
	public static String BATCH_CSV_BIRTHDAY = "生日月CSV导入";
	public static String BATCH_CSV_MASSUCODE = "群发PIN_CSV导入";
	public static String BATCH_CSV_ORDER = "用户发货CSV导入";
	public static String BATCH_CSV_SUORDER = "特殊用户发货CSV导入";
	public static String BATCH_CSV_SERCHG = "服务变更CSV导入";
	public static String BATCH_CSV_TEMPLATE = "共同CSV导入";
	
	public static String BATCH_API_INTEGRAL_CHANGE = "积分变动提醒模版消息";
	public static String BATCH_MSG_DELIVER = "发货提醒模版消息";
	public static String BATCH_MSG_BIRTHDAY = "生日月图文群发";
	public static String BATCH_MSG_INTEGRA = "积分到期模版消息";
	public static String BATCH_MSG_INTEGRACHANGE = "积分变动模版消息";
	public static String BATCH_MSG_NEWS = "图文群发";
	public static String BATCH_MSG_DELIVERTIMES="累积发货次数图文群发";
	public static String BATCH_MSG_THEMETRADEMASSSEND="主题商品自动化群发";
	public static String BATCH_MSG_MASSUCODE = "根据PIN图文群发";
	public static String BATCH_MSG_MASSAB = "AB推送图文群发";
	public static String BATCH_MSG_COMMON = "特定用户图文群发";
	public static String BATCH_MSG_SPECIAL = "区间月龄图文群发";
	public static String BATCH_MSG_MEMBER = "会员到期模版消息";
	public static String BATCH_MSG_SERCHG = "服务变更模版消息";
	public static String BATCH_MSG_TEMPLATE = "共通模版消息";
	public static String BATCH_MSG_ACTIVITIES = "活动成功参与消息";
	public static String BATCH_MEMBER_STATUS = "在籍状态维护";
	public static String BATCH_TOKEN_REFRUSH = "微信Token更新";
	public static String BATCH_MASTER_REFRUSH = "Master数据出生年、月及版本号更新";
	public static String BATCH_DATA_DELETE = "过期数据删除";
	public static String BATCH_NONMEM_MASTER_DATE = "同步版本号的master数据";
	public static String BATCH_NONMEM_YL = "非会员月龄版推送";
	public static String BATCH_NONMEM_NON_YL = "非会员非月龄版推送";
	public static String BATCH_NONMEM_CLEAR = "非会员推送清零batch";
	public static String BATCH_STATISTICALDATA_UPDAT = "群推统计数据更新batch";
	public static String BATCH_USERMASSTRADE_UPDAT = "主题商品数据更新batch";

	
	/**
	 * batch 执行结束内容
	 */
	public static String BATCH_OVER = "batch执行结束";
	public static String BATCH_EXCEPTION = "batch执行异常";
	
	//备份文件路径
	public static String FILE_BAK = "bak";
	//错误文件路径
	public static String FILE_ERR = "err";
	//数据库配置内容分隔符
	public static String CONFIG_SPLIT_SYMBOL = ",";
	
	public static String MASS_UNSEND = "0";
	public static String MASS_SENDED= "1";
	/**
	 * 图文
	 */
	public static String PICTURE_TEXT= "1";
	
	/**
	 * 发布状态：已发布
	 */
	public static String SEND_STATUS_TWO= "2";
	
	/**
	 * 发送失败：2
	 */
	public static String MASS_SENDFAIL= "2";
	public static String MASS_EXCEPTION= "3";
	
	public static String MASS_USERINFO_SENDFLAG = "9";
	
	//发货提醒重复数量
	public static int DELIVER_SAME_COUNT = 1;
	
	//特殊用户发货时间csv的长度
	public static int CSV_FILE_COLUMON_LENGTH = 5;
	
	public static int SEARCH_LIMIT_COUNT = 1000;
	
	public static String MASS_SEND_A = "A";
	
	public static String MASS_SEND_B = "B";
	
	public static String FLAG_ZERO = "0";
	public static String FLAG_ONE = "1";
	
	public static String MASS_COMMON_UNREGIST = "1";
	
	public static final int EXECUTE_SQL_ERROR = -1;
	
	public static final int EXECUTE_SQL_ZERO = 0;
	
	public static final String EMPTY = "";

	

}
