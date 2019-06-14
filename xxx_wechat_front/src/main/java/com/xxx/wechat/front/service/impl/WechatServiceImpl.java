package com.qiaohu.wechat.front.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qiaohu.wechat.common.api.CustomAPI;
import com.qiaohu.wechat.common.api.UserAPI;
import com.qiaohu.wechat.common.api.enums.ResultType;
import com.qiaohu.wechat.common.api.response.ApiResponse;
import com.qiaohu.wechat.common.api.response.GetUserInfoResponse;
import com.qiaohu.wechat.common.entity.message.req.BaseEvent;
import com.qiaohu.wechat.common.entity.message.req.ImageReqMsg;
import com.qiaohu.wechat.common.entity.message.req.KfEvent;
import com.qiaohu.wechat.common.entity.message.req.LinkReqMsg;
import com.qiaohu.wechat.common.entity.message.req.LocationReqMsg;
import com.qiaohu.wechat.common.entity.message.req.MenuEvent;
import com.qiaohu.wechat.common.entity.message.req.QrCodeEvent;
import com.qiaohu.wechat.common.entity.message.req.SendMessageEvent;
import com.qiaohu.wechat.common.entity.message.req.TemplateMsgEvent;
import com.qiaohu.wechat.common.entity.message.req.TextReqMsg;
import com.qiaohu.wechat.common.entity.message.req.VideoReqMsg;
import com.qiaohu.wechat.common.entity.message.req.VoiceReqMsg;
import com.qiaohu.wechat.common.entity.message.resp.Article;
import com.qiaohu.wechat.common.entity.message.resp.BaseMsg;
import com.qiaohu.wechat.common.entity.message.resp.ImageMsg;
import com.qiaohu.wechat.common.entity.message.resp.MpNewsMsg;
import com.qiaohu.wechat.common.entity.message.resp.NewsMsg;
import com.qiaohu.wechat.common.entity.message.resp.TextMsg;
import com.qiaohu.wechat.common.entity.wechatenum.DeleteFlag;
import com.qiaohu.wechat.common.utils.BeanUtils;
import com.qiaohu.wechat.common.utils.CheckUtils;
import com.qiaohu.wechat.common.utils.DateUtils;
import com.qiaohu.wechat.common.utils.StrUtil;
import com.qiaohu.wechat.constants.WechatConstants;
import com.qiaohu.wechat.core.config.ApiConfig;
import com.qiaohu.wechat.core.constants.BatchConstants;
import com.qiaohu.wechat.core.constants.ConfigConstants;
import com.qiaohu.wechat.core.constants.Constants;
import com.qiaohu.wechat.core.dao.CustomerLogDao;
import com.qiaohu.wechat.core.dao.MassSendDtlDao;
import com.qiaohu.wechat.core.dao.UserWeiXinDao;
import com.qiaohu.wechat.core.entity.CustomerLog;
import com.qiaohu.wechat.core.entity.Keyword;
import com.qiaohu.wechat.core.entity.KeywordInputResumen;
import com.qiaohu.wechat.core.entity.MassSendDtl;
import com.qiaohu.wechat.core.entity.QrLog;
import com.qiaohu.wechat.core.entity.StatisticsFaqChangeResume;
import com.qiaohu.wechat.core.entity.UserActionRecord;
import com.qiaohu.wechat.core.entity.UserWeiXin;
import com.qiaohu.wechat.core.entity.UserWeiXinlabel;
import com.qiaohu.wechat.core.entity.extend.KeywordExt;
import com.qiaohu.wechat.core.exception.AppException;
import com.qiaohu.wechat.core.service.IKeyWordService;
import com.qiaohu.wechat.core.service.IQRService;
import com.qiaohu.wechat.core.service.IUserActionRecordService;
import com.qiaohu.wechat.core.service.IUserWeixinLabelService;
import com.qiaohu.wechat.core.util.SysPropUtil;
import com.qiaohu.wechat.front.service.IActivityService;
import com.qiaohu.wechat.front.service.IApiService;
import com.qiaohu.wechat.front.service.ICustomService;
import com.qiaohu.wechat.front.service.IKeywordInputResumenService;
import com.qiaohu.wechat.front.service.IStatisticsFaqChangeResumeService;
import com.qiaohu.wechat.front.service.IWechatService;
import com.qiaohu.wechat.front.service.IWechatUserService;
import com.qiaohu.wechat.helper.ConfigHelper;

@Service
public class WechatServiceImpl implements IWechatService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private IWechatUserService wechatUserService;
	@Autowired
	private CustomerLogDao customerLogDao;
	@Autowired
	private ICustomService customService;
	@Autowired
	private IQRService qrService;
	@Autowired
	private IKeyWordService keyWordService;
	@Autowired
	private IActivityService activityService;
	@Autowired
	private IApiService apiService;
	@Autowired
	private MassSendDtlDao massSendDtlDao;
	@Autowired
	private UserWeiXinDao userWeiXinDao;
	@Autowired
	private ConfigHelper configHelper;
	@Autowired
	private IUserWeixinLabelService userWeixinLabelService;
	//用户操作记录
	@Autowired
	private IUserActionRecordService userActionRecordService;
	@Autowired 
	private IStatisticsFaqChangeResumeService statisticsFaqChangeResumeService;
	@Autowired
	private IKeywordInputResumenService keywordInputResumenService;

	/**
	 * 回复纯文字消息
	 */
	@Override
	public BaseMsg textMessage(TextReqMsg reqMsg) {
		try {
			logger.info("process start text message");
			String inputWord = reqMsg.getContent();
			String openId = reqMsg.getFromUserName();

			//TYPE 1：关键字 2：常见问题 3:活动关键字
			// 2018-02-28 匹配活动关键字， 活动关键字是精确查询
			logger.info("输入关键词" + inputWord);
			KeywordExt actionKeywordExt = keyWordService.detail(inputWord.toString());
			CustomAPI customApi = null;
			
			if (!CheckUtils.isNull(actionKeywordExt)
					&& !CheckUtils.isNullOrEmpty(actionKeywordExt.getReplyContent())
					&& "3".equals(actionKeywordExt.getType())) {
				customApi = new CustomAPI(ApiConfig.getInstance()
						.getAccessToken());
				TextMsg msg = new TextMsg();
				msg.setContent(actionKeywordExt.getReplyContent());
				customApi.sendCustomMessage(openId, msg);
				logger.info("回复文字消息");
				return null;
			}
			
			// 匹配常见问题，常见问题是精确查询
			KeywordExt keywordExt = keyWordService.detail(inputWord.toString());
			if (!CheckUtils.isNull(keywordExt)
					&& !CheckUtils.isNullOrEmpty(keywordExt.getReplyContent())
					&& "2".equals(keywordExt.getType())) {
				
				//插入常见问题key履历 yucj 票:65883
				insertKeywordResumen(inputWord, openId);
				
				customApi = new CustomAPI(ApiConfig.getInstance()
						.getAccessToken());
				TextMsg msg = new TextMsg();
				msg.setContent(keywordExt.getReplyContent());
				customApi.sendCustomMessage(openId, msg);
				logger.info("回复文字消息");
				return null;
			}
			
			//匹配关键词，关键词是模糊匹配
			inputWord = WechatConstants.Config.PERCENT_SIGN + inputWord
						 + WechatConstants.Config.PERCENT_SIGN;
			List<Keyword> retKeywordList = keyWordService
					.matchKeyWord(inputWord);

			customApi = new CustomAPI(ApiConfig.getInstance().getAccessToken());
			
			//REPLY_TYPE 1：文字  2：图文  3：客服转接
			// 将匹配的关键字全部回复
			if (!CheckUtils.isNullOrEmpty(retKeywordList)) {
				for (Keyword retKeyword : retKeywordList) {
					// 通过客服消息回复文字
					if (Keyword.REPLY_TYPE_TEXT.equals(retKeyword.getReplyType().trim())) {
						if ("1".equals(retKeyword.getType())) {
							TextMsg msg = new TextMsg();
							msg.setContent(retKeyword.getReplyContent());
							customApi.sendCustomMessage(openId, msg);
							logger.info("回复文字消息");
						}
					}
					// 通过客服消息回复图文
					else if (Keyword.REPLY_TYPE_NEWS.equals(retKeyword.getReplyType().trim())) {
						if ("1".equals(retKeyword.getType())) {
							MpNewsMsg msg = new MpNewsMsg();
							msg.setMediaId(retKeyword.getReplyContent());
							customApi.sendCustomMessage(openId, msg);
							logger.info("回复图文消息");
						}
					}
					//通过客服消息回复图片
					else if(Keyword.REPLY_TYPE_IMAGE.equals(retKeyword.getReplyType().trim())){
						if ("1".equals(retKeyword.getType())) {
							ImageMsg img = new ImageMsg();
							img.setMediaId(retKeyword.getReplyContent());
							customApi.sendCustomMessage(openId, img);
							logger.info("回复图片消息");
						}
					}
				}
			}
			// 未匹配到回复默认话术
			else {
				TextMsg msg = new TextMsg();
				msg.setContent(SysPropUtil
						.getString(ConfigConstants.REPLY_KEYWORD_DEF));
				customApi.sendCustomMessage(openId, msg);
				logger.info("回复默认消息");
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		return null;
	}

	/**
	 * 插入关键字履历
	 * @param inputWord
	 * @param openId
	 */
	private void insertKeywordResumen(String inputWord, String openId) {
		inputWord = inputWord.toUpperCase();
		KeywordInputResumen resumen = new KeywordInputResumen();
		resumen.setType(KeywordInputResumen.TYPE_KEYWORD);
		resumen.setOpenId(openId);
		resumen.setKeyword(inputWord);
		//keywordleve允许为空
		resumen.setKeywordLevel(KeywordInputResumen.getKeyWordLevelByKeyword(inputWord));
		resumen.setCreateDate(DateUtils.getNowTimestamp());
		try {
			keywordInputResumenService.insert(resumen);
		} catch (Exception e) {
			logger.error("插入常见问题的关键字插入失败关键字为"+inputWord+e.getMessage());
		}
	}

	@Override
	public BaseMsg imageMessage(ImageReqMsg reqMsg) {
		return null;// 暂无处理
	}

	@Override
	public BaseMsg videoMessage(VoiceReqMsg reqMsg) {
		return null;// 暂无处理
	}

	@Override
	public BaseMsg videoMessage(VideoReqMsg reqMsg) {
		return null;// 暂无处理
	}

	@Override
	public BaseMsg shortvideoMessage(VideoReqMsg reqMsg) {
		return null;// 暂无处理
	}

	@Override
	public BaseMsg locationMessage(LocationReqMsg reqMsg) {

		return null;
	}

	@Override
	public BaseMsg linkMessage(LinkReqMsg reqMsg) {
		return null;// 暂无处理
	}

	// 已关注扫码
	@Override
	public BaseMsg scanEvent(QrCodeEvent event) {
		logger.info("处理已关注消息");

		String qr = null;
		if (!StrUtil.isBlank(event.getTicket())) { // 扫码关注
			qr = event.getEventKey()
					.replace(WechatConstants.Config.QRSCENE, "");

			// 增加扫码履历
			qrService.insertQrLog(qr, event.getFromUserName(),
					QrLog.TYPE_ALREADY_CONCERN);
		}
		// 同步微信用户信息
		UserWeiXin userWX = retryUpdateWechatUserInfo(event.getFromUserName(), false);
		if (!CheckUtils.isNull(userWX)) {
			userWX.setApplyQR(qr);
			wechatUserService.updateUser(userWX, false);
			return null;
		} 
		try {
			userWX = new UserWeiXin();
			if (!StrUtil.isBlank(event.getTicket())) { // 扫码关注
				qr = event.getEventKey().replace(
						WechatConstants.Config.QRSCENE, "");
				userWX.setApplyQR(qr);
				// 增加扫码履历
				qrService.insertQrLog(qr, event.getFromUserName(),
						QrLog.TYPE_NEW_CONCERN);
			}
			UserAPI userAPI = new UserAPI(ApiConfig.getInstance().getAccessToken());

			GetUserInfoResponse wechatUserInfo = userAPI.getUserInfo(event.getFromUserName());
			if (CheckUtils.isNull(wechatUserInfo)
					|| CheckUtils.isNull(wechatUserInfo.getOpenid())) {
				logger.error("OpenId:" + wechatUserInfo.getOpenid() + " get user info error.");
			}
			if (!CheckUtils.isNullOrEmpty(wechatUserInfo.getErrcode())
					&& !ResultType.SUCCESS.getCode().toString()
							.equals(wechatUserInfo.getErrcode())) {
				logger.error("errorcode is :" + wechatUserInfo.getErrcode()
						+ " errormsg is " + wechatUserInfo.getErrmsg());
			}

			// nickname转为16进制存储至数据库
//			user.setByteNickName(wechatUserInfo.getNickname());
			try {
				userWX.setNickname(URLEncoder.encode(wechatUserInfo.getNickname(),"utf-8"));
			} catch (UnsupportedEncodingException e) {
				
			}
			if (!CheckUtils.isNull(wechatUserInfo.getSex())) {
				userWX.setSex(wechatUserInfo.getSex().toString());
			}
			userWX.setCity(wechatUserInfo.getCity());
			userWX.setCountry(wechatUserInfo.getCountry());
			userWX.setProvince(wechatUserInfo.getProvince());
			userWX.setLanguage(wechatUserInfo.getLanguage());
			userWX.setSubscribeTime(new Timestamp(wechatUserInfo.getSubscribeTime()));
			userWX.setDeleteFlag(DeleteFlag.NO_DELETE);
			userWX.setOpenId(event.getFromUserName());
			userWX.setErrorTime(0);
			userWX.setDeleteFlag("0");
			userWX.setLockFlag("0");
			wechatUserService.saveUser(userWX);
			logger.info("用户:" + event.getFromUserName() + "添加成功");
		} catch (Exception e) {
			logger.error(e.getMessage());
			if (!CheckUtils.isNull(userWX)) {
				userWX.setCreateUser(null);
				userWX.setCreateDate(null);
				//userWX.setWebId("");
				userWX.setUcode("");
				userWX.setCookie("");
				userWX.setOrderTimes("");
				userWX.setOrderDate("");
				userWX.setProdid("");
				userWX.setProdGrName("");
				userWX.setBirthdayMouth("");
				userWX.setFromcode("");
				wechatUserService.updateUser(userWX, true);
			}

		}

		return null;
	}

	// 新关注 test ok
	@Override
	public BaseMsg subscribeEvent(QrCodeEvent event) {
		logger.info("处理" + event.getFromUserName() + "关注消息");
		// 新关注发送默认消息
		try {
			CustomAPI customApi = new CustomAPI(ApiConfig.getInstance()
					.getAccessToken());
			TextMsg msg = new TextMsg();
			msg.setContent(SysPropUtil
					.getString(ConfigConstants.REPLY_SUBSCRIBE_WELCOME));
			customApi.sendCustomMessage(event.getFromUserName(), msg);
			logger.debug("新关注是发送默认消息成功");
		} catch (Exception e) {
			logger.error("新关注时发送默认消息失败");
			logger.error(e.getMessage());
		}

		// 同步微信用户信息
//		UserWeiXin userWX = retryUpdateWechatUserInfo(event.getFromUserName(), true);
//		if (!CheckUtils.isNull(userWX)) {
//			logger.info("用户:" + event.getFromUserName() + "更新成功");
//			return null;
//		}
		//新用户关注微信公众号时处理
		UserWeiXin userWX = null;
		try {
			userWX = new UserWeiXin();
			if (!StrUtil.isBlank(event.getTicket())) { // 扫码关注
				String qr = event.getEventKey().replace(
						WechatConstants.Config.QRSCENE, "");
				userWX.setApplyQR(qr);
				// 增加扫码履历
				qrService.insertQrLog(qr, event.getFromUserName(),
						QrLog.TYPE_NEW_CONCERN);
			}
			UserAPI userAPI = new UserAPI(ApiConfig.getInstance().getAccessToken());

			GetUserInfoResponse wechatUserInfo = userAPI.getUserInfo(event.getFromUserName());
			if (CheckUtils.isNull(wechatUserInfo)
					|| CheckUtils.isNull(wechatUserInfo.getOpenid())) {
				logger.error("OpenId:" + wechatUserInfo.getOpenid() + " get user info error.");
			}
			if (!CheckUtils.isNullOrEmpty(wechatUserInfo.getErrcode())
					&& !ResultType.SUCCESS.getCode().toString()
							.equals(wechatUserInfo.getErrcode())) {
				logger.error("errorcode is :" + wechatUserInfo.getErrcode()
						+ " errormsg is " + wechatUserInfo.getErrmsg());
			}

			// nickname转为16进制存储至数据库
//			user.setByteNickName(wechatUserInfo.getNickname());
			try {
				userWX.setNickname(URLEncoder.encode(wechatUserInfo.getNickname(),"utf-8"));
			} catch (UnsupportedEncodingException e) {
				
			}
			
			/**
			 * 增加用户关注记录到UserActionRecord
			 * @time 2018-02-23
			 */
			createUserActionRecord(event.getFromUserName(), userWX.getNickname(), Constants.BLANK_STR, Constants.BLANK_STR, Constants.SUBSCRIBE);
			
			if (!CheckUtils.isNull(wechatUserInfo.getSex())) {
				userWX.setSex(wechatUserInfo.getSex().toString());
			}
			if(!CheckUtils.isNullOrEmpty(wechatUserInfo.getCity())){
				userWX.setCity(wechatUserInfo.getCity().trim());
			}
			if(!CheckUtils.isNullOrEmpty(wechatUserInfo.getCountry())){
				userWX.setCountry(wechatUserInfo.getCountry().trim());
			}
			if(!CheckUtils.isNullOrEmpty(wechatUserInfo.getProvince())){
				userWX.setProvince(wechatUserInfo.getProvince().trim());
			}
			userWX.setLanguage(wechatUserInfo.getLanguage());
			userWX.setSubscribeTime(new Timestamp(wechatUserInfo.getSubscribeTime()));
			userWX.setDeleteFlag(DeleteFlag.NO_DELETE);
			userWX.setOpenId(event.getFromUserName());
			userWX.setErrorTime(0);
			userWX.setDeleteFlag("0");
			userWX.setLockFlag("0");
			wechatUserService.saveUser(userWX);
			logger.info("用户:" + event.getFromUserName() + "添加成功");
		} catch (Exception e) {
			logger.error(e.getMessage());
			if (!CheckUtils.isNull(userWX)) {
				userWX.setCreateUser(null);
				userWX.setCreateDate(null);
				//userWX.setWebId("");
				userWX.setUcode("");
				userWX.setCookie("");
				userWX.setOrderTimes("");
				userWX.setOrderDate("");
				userWX.setProdid("");
				userWX.setProdGrName("");
				userWX.setBirthdayMouth("");
				userWX.setFromcode("");
				wechatUserService.updateUser(userWX, true);
			}

		}
		try {
			distinguishRegist(userWX);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}

	// 取消关注 Test OK
	@Override
	public BaseMsg unsubscribeEvent(BaseEvent event) {
		UserWeiXin userWX = wechatUserService.selectByOpenId(event
				.getFromUserName());
		if (userWX != null) {
			/**
			 * 增加用户取消关注记录到UserActionRecord
			 * @time 2018-02-23
			 */
			createUserActionRecord(userWX.getOpenId(), userWX.getNickname(), userWX.getUcode(), userWX.getWebId(), Constants.UNSUBSCRIBE);
			
			wechatUserService.deleteUser(userWX);
		}
		return null;
	}

	@Override
	public BaseMsg clickEvent(MenuEvent event) {

		// 我的积分
		if (WechatConstants.ButtonKey.WDJF.equals(event.getEventKey())) {
			logger.info("处理我的积分按钮");
			UserWeiXin userWX = retryUpdateWechatUserInfo(event
					.getFromUserName(), false);
			CustomAPI customApi = new CustomAPI(ApiConfig.getInstance()
					.getAccessToken());

			TextMsg retMsg = new TextMsg();
			// 未绑定
			if (CheckUtils.isNull(userWX) || StrUtil.isBlank(userWX.getWebId())) {
				if (CheckUtils.isNull(userWX)) {
					logger.error("Can't find openId " + event.getFromUserName());
				}
				String content = SysPropUtil
						.getString(ConfigConstants.REPLY_LOGIN_PROMPT);
				String url = SysPropUtil.getString(ConfigConstants.HOST_URL)
						+ WechatConstants.View.USER_OAUTH_LOGIN;
				content = String.format(content, url);
				retMsg.setContent(content);
				customApi.sendCustomMessage(event.getFromUserName(), retMsg);
				return null;
			}

			// fromcode过滤 k31 k35不发送
			if (WechatConstants.Config.UNSEND_FROMCODE_K31
					.equalsIgnoreCase(userWX.getFromcode())
					|| WechatConstants.Config.UNSEND_FROMCODE_K35
							.equalsIgnoreCase(userWX.getFromcode())) {
				retMsg.setContent(configHelper.replyIntegralUnsend);
				customApi.sendCustomMessage(event.getFromUserName(), retMsg);
				return null;
			}
			// 返回结果状态为0时， 直接返回没有积分【您当前没有巧虎积分】
			ApiResponse resp = apiService.integralGet(userWX.getWebId());
			if (!resp.isSuccess(WechatConstants.QiaoHuAPI.KEY_STATUS)) {
				retMsg.setContent(SysPropUtil
						.getString(ConfigConstants.REPLY_INTEGRAL_ATI_ERR));
				customApi.sendCustomMessage(event.getFromUserName(), retMsg);
				return null;

			}
			logger.info("调用基干器积分接口成功");
			String available_points = resp.resultObj
					.getString(WechatConstants.Config.AVAILABLE_POINTS);
			String used_points = resp.resultObj
					.getString(WechatConstants.Config.USED_POINTS);
			String freeze_points = resp.resultObj
					.getString(WechatConstants.Config.FREEZE_POINTS);

			String content = SysPropUtil
					.getString(ConfigConstants.INTEGRAL_REPLY_CONTENT);
			content = String.format(content, available_points, used_points,
					freeze_points);

			retMsg.setContent(content);
			customApi.sendTextCustomMessage(event.getFromUserName(), retMsg);
			logger.info("发送积分客服消息结束");
			return null;
		}
		// 线下活动
//		else if (WechatConstants.ButtonKey.XXHD.equals(event.getEventKey())) {
//			return activityService.getActivity(re);
//		}
		// // 人工客服
		// else if (WechatConstants.ButtonKey.RGKF.equals(event.getEventKey()))
		// {
		// return customService.connCustomer(event.getFromUserName());
		// }
		// 常见问题
		else if (WechatConstants.ButtonKey.CJWT.equals(event.getEventKey())) {
			//插入点击常见问题履历 addby yucj
			insertFaqResumen(event);

			String content = SysPropUtil
					.getString(ConfigConstants.FAQ_REPLY_CONTENT);
			CustomAPI customApi = new CustomAPI(ApiConfig.getInstance()
					.getAccessToken());
			TextMsg msg = new TextMsg();
			msg.setContent(content);
			ResultType ret = customApi.sendCustomMessage(
					event.getFromUserName(), msg);
			logger.info(ret.toString());
		}

		return null;
	}
	
	/**
	 * 常见问题点击记录履历表 yucj start 对应票：67385
	 */
	private void insertFaqResumen(MenuEvent event){
		Long faq_init_id = 0L;
		Long faqId = faq_init_id;
		try {
			//获取今天的最大id的常见问题履历
			StatisticsFaqChangeResume resume= statisticsFaqChangeResumeService.selectMaxId();
			faqId = resume.getId();
		} catch (Exception e) {
			logger.error(e.getMessage() + "获取常见问题失败重新插入");
			//若获取失败则，重新插入一条后，再次获取
			faqId = insertFaqResumenAgain();
		}
		//获取履历失败，则直接返回
		if(faqId == faq_init_id){
			logger.error("获取常见问题履历失败");
			return;
		}
		//插入一次点击常见问题履历
		try {
			KeywordInputResumen resumen = new KeywordInputResumen();
			resumen.setOpenId(event.getFromUserName());
			resumen.setType(KeywordInputResumen.TYPE_FAQ);
			resumen.setFaqId(faqId);
			resumen.setCreateDate(DateUtils.getNowTimestamp());
			keywordInputResumenService.insert(resumen);
		} catch (Exception e) {
			logger.error(e.getMessage() + "");
		}
	}
	
	/**
	 * 插入常见问题履历并且获取最新的id
	 * @return
	 */
	private Long insertFaqResumenAgain(){
		try {
			StatisticsFaqChangeResume statisticsFaqChangeResume = new StatisticsFaqChangeResume();
			statisticsFaqChangeResume.setFaq(SysPropUtil.getString(ConfigConstants.FAQ_REPLY_CONTENT));
			statisticsFaqChangeResume.setCreateDate(DateUtils.getNowTimestamp());
			statisticsFaqChangeResumeService.insert(statisticsFaqChangeResume);
			StatisticsFaqChangeResume nowResume= statisticsFaqChangeResumeService.selectMaxId();
			logger.info("没有获取到履历id，当时重新插入常见问题履历并且获取最新的id成功");
			return nowResume.getId();
		} catch (Exception e) {
			logger.error(e.getMessage() + "常见问题重新插入履历和获取最大id失败");
			return 0L;
		}
	}

	@Override
	public BaseMsg viewEvent(MenuEvent event) {
		return null;
	}

	@Override
	public BaseMsg templatesEndJobFinishEvent(TemplateMsgEvent event) {
		return null;
	}

	@Override
	/**
	 * 群发处理后接收消息
	 */
	public BaseMsg massSendEndJobFinishEvent(SendMessageEvent event) {
		MassSendDtl massSendDtl = new MassSendDtl();
		massSendDtl.setMsgId(event.getMsgId());
		massSendDtl.setTotalCount(event.getTotalCount());
		massSendDtl.setFilterCount(event.getFilterCount());
		massSendDtl.setSentCount(event.getSentCount());
		massSendDtl.setErrorCount(event.getErrorCount());
		massSendDtl.setModifiedTime(DateUtils.getYMDHMS(new Date()));
		logger.info("要更新的数据：MsgId:"+event.getMsgId()+"totalCount:"+event.getTotalCount()+"sendCount:"+event.getSentCount()+"filterCount:"+event.getFilterCount());
		massSendDtlDao.updateByMsgId(massSendDtl);
		logger.info("更新群推消息详细记录表信息：totalCount sendCount filterCount");
		return null;
	}

	@Override
	public BaseMsg customAccessEvent(KfEvent event) {
		CustomerLog lastReq = customerLogDao.selectLastReqByOpenId(event
				.getFromUserName());
		if (!BeanUtils.isNull(lastReq)) {
			customService.accessServer(lastReq, event.getKfAccount());
		}
		return null;
	}

	@Override
	public BaseMsg customCloseEvent(KfEvent event) {
		CustomerLog lastReq = customerLogDao.selectLastReqByOpenId(event
				.getFromUserName());
		if (!BeanUtils.isNull(lastReq)) {
			customService.closeServer(lastReq, event.getKfAccount());
		}
		//发送客服消息结束语
		CustomAPI customApi = new CustomAPI(ApiConfig.getInstance()
				.getAccessToken());
		TextMsg msg = new TextMsg();
		msg.setContent(configHelper.kfCloseSession);
		ResultType ret = customApi.sendCustomMessage(
				event.getFromUserName(), msg);
		System.out.println(ret.getCode()+":"+ret.getDescription());
		return null;
	}

	/**
	 * 同步微信信息
	 * 
	 * @param openId
	 * @return
	 */
	private UserWeiXin updateWechatUserInfo(String openId, boolean update) {
		UserWeiXin user = userWeiXinDao.selectByPrimaryKey(openId);
		
		//用户不存在时
		if (CheckUtils.isNull(user)) {
			// 用户不存在
			logger.info("用户:" + openId + "不存在");
			return null;
		}

		//是否同步判断，新关注的用户不存在，没有关注时间，没有昵称
		if (!CheckUtils.isNull(user.getSubscribeTime())
				&& !CheckUtils.isNullOrEmpty(user.getByteNickname()) && !update) {
			//
			if (user.getModifyDate().toString().contains(DateUtils.getNowFormat(DateUtils.YYYY_MM_DD))
					&& DeleteFlag.NO_DELETE.equals(user.getDeleteFlag())) {
				return user;
			}
		}

		UserAPI userAPI = new UserAPI(ApiConfig.getInstance().getAccessToken());

		GetUserInfoResponse wechatUserInfo = userAPI.getUserInfo(user.getOpenId());

		if (CheckUtils.isNull(wechatUserInfo) || CheckUtils.isNull(wechatUserInfo.getOpenid())) {
			logger.error("OpenId:" + user.getOpenId() + " get user info error.");
			return user;
		}
		if (!CheckUtils.isNullOrEmpty(wechatUserInfo.getErrcode()) 
				&& !ResultType.SUCCESS.getCode().toString().equals(wechatUserInfo.getErrcode())) {
			logger.error("errorcode is :" + wechatUserInfo.getErrcode() + " errormsg is " + wechatUserInfo.getErrmsg());
			return user;
		}

		try {
			user.setNickname(URLEncoder.encode(wechatUserInfo.getNickname(),"utf-8"));
		} catch (UnsupportedEncodingException e) {
			
		}
		if(update){
			user.setUcode("");
			//user.setWebId("");
			user.setUserStatus("");
			user.setCookie("");
			user.setOrderTimes("");
			user.setOrderDate("");
			user.setProdid("");
			user.setProdGrName("");
			user.setBirthdayMouth("");
			user.setSignDate(null);
			user.setFromcode("");
		}
		if (!CheckUtils.isNull(wechatUserInfo.getSex())) {
			user.setSex(wechatUserInfo.getSex().toString());
		}
		user.setCity(wechatUserInfo.getCity());
		user.setCountry(wechatUserInfo.getCountry());
		user.setProvince(wechatUserInfo.getProvince());
		user.setLanguage(wechatUserInfo.getLanguage());
		user.setSubscribeTime(new Timestamp(wechatUserInfo.getSubscribeTime()));
		user.setDeleteFlag(DeleteFlag.NO_DELETE);
		// 更新信息
		try {
			wechatUserService.updateUser(user, update);
		} catch (Exception e) {
			logger.error("update user failed.");
			logger.error(e.getMessage());
		}
		
		if(user.getUserStatus().equals("0")) {
			// TODO yk 添加在籍标签
			batchTagsToUser(user.getOpenId(), Integer.valueOf(SysPropUtil.getString(ConfigConstants.WECHAT_TAG_ID)));
		}else if(user.getUserStatus().equals("1")){
			// TODO yk 添加非在籍标签
			batchTagsToUser(user.getOpenId(), Integer.valueOf(SysPropUtil.getString(ConfigConstants.WECHAT_OTHER_TAG_ID)));
		}
		
		return user;
	}

	/**
	 * 同步微信信息 ，并且错误重试
	 * 
	 * @param openId
	 * @return
	 */
	private UserWeiXin retryUpdateWechatUserInfo(String openId, boolean update) {
		//同步微信用户信息
		UserWeiXin userWX = null;
		try {
			userWX = updateWechatUserInfo(openId, update);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		if (CheckUtils.isNull(userWX)) {
			return null;
		}
		userWX.setDeleteFlag(DeleteFlag.NO_DELETE);
		userWX.setErrorTime(0);
		userWX.setLockFlag("0");
		return userWX;
	}

	/**
	 * 点击小助手按钮
	 */
	@Override
	public BaseMsg clickKFEvent(MenuEvent event) {
		return customService.connCustomer(event.getFromUserName());
	}

	@Override
	public BaseMsg locationActionevent(Map<String, Object> reqMap) {
		return activityService.getActivity(reqMap);
	}
	
	/**
	 * 记录该用户是否登陆过
	 * @param userWeiXin
	 */
	private void distinguishRegist(UserWeiXin userWeiXin){
		//初始化数据
		UserWeiXinlabel label = new UserWeiXinlabel();
		label.setOpenId(userWeiXin.getOpenId());
		label.setUpdatetime(new Timestamp(new Date().getTime()));
		
		//若webId存在，将flag置为1，若不存在则置为0
		if(CheckUtils.isNullOrEmpty(userWeiXin.getWebId())){
			label.setRegistFlag(BatchConstants.FLAG_ZERO);
		}else{
			label.setRegistFlag(BatchConstants.FLAG_ONE);
		}
		
		//查看表中是否有这样的记录
		UserWeiXinlabel userLabel = userWeixinLabelService.selectByPrimaryKey(label);
		if(CheckUtils.isNull(userLabel)){
			userWeixinLabelService.insertSelective(label);
		}else{
			//若存在用户，并且registflag字段为0时更新数据
			if(!CheckUtils.isNullOrEmpty(userLabel.getRegistFlag()) 
					&& BatchConstants.FLAG_ZERO.equals(userLabel.getRegistFlag())){
				userWeixinLabelService.updateByPrimaryKeySelective(label);
			}
		}
	}

	/**
	 * 增加用户登录记录
	 * @param openId
	 * @param ucode
	 * @param webId
	 * @param operation
	 */
	private void createUserActionRecord(String openId, String nickname, String ucode, String webId, String operation) {
		UserActionRecord userActionRecord = new UserActionRecord();
		//操作者的open_id
		userActionRecord.setOpenId(openId);
		//操作者的nickname
		//userActionRecord.setNickname(URLEncoder.encode(nickname,"utf-8"));
		userActionRecord.setNickname(nickname);
		//操作者的ucode
		userActionRecord.setUcode(ucode);
		//操作者的webId
		userActionRecord.setWebId(webId);
		//操作时间
		userActionRecord.setTime(DateUtils.getNowFormat(DateUtils.YMDHHMMSS));
		//添加操作属性
		userActionRecord.setOperation(operation);
		try {
		userActionRecordService.create(userActionRecord);
		} catch(Exception e) {
			logger.error("新增用户" + operation + "操作记录失败：" + e.getMessage());
		}
	}
	
	// 添加用户标签
	private void batchTagsToUser(String openid, Integer tagId) {
		List<String> openidList = new ArrayList<String>();
		openidList.add(openid);
		batchTagsToUser(openidList, tagId);
	}
	// 批量添加用户标签
	private void batchTagsToUser(List<String> openidList, Integer tagId) {
		try {
			UserAPI userApi = new UserAPI(ApiConfig.getInstance().getAccessToken());
			userApi.batchTagsToUser(openidList, tagId);
		} catch (AppException e) {
			logger.error(e.getMessage());
		}
	}

}
