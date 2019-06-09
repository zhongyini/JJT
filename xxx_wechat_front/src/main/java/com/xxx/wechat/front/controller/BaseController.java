package com.xxx.wechat.front.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.xxx.wechat.common.constant.ConfigurationEnum;
import com.xxx.wechat.common.constant.Constant;
import com.xxx.wechat.common.utils.CheckUtils;
import com.xxx.wechat.common.utils.JsonUtils;
import com.xxx.wechat.common.wechat.api.CardApi;
import com.xxx.wechat.common.wechat.api.QrcodeAPI;
import com.xxx.wechat.common.wechat.api.UserAPI;
import com.xxx.wechat.common.wechat.api.entity.QrCode;
import com.xxx.wechat.common.wechat.api.response.CardResponse;
import com.xxx.wechat.common.wechat.api.response.GetUserInfoResponse;
import com.xxx.wechat.common.wechat.api.response.QrcodeResponse;
import com.xxx.wechat.core.config.ConfigurationConfig;
import com.xxx.wechat.core.config.WechatTokenConfig;
import com.xxx.wechat.core.dao.entity.WechatCard;
import com.xxx.wechat.core.dao.entity.WechatCardCode;
import com.xxx.wechat.core.dao.entity.WechatQrcode;
import com.xxx.wechat.core.dao.entity.WechatRecommend;
import com.xxx.wechat.core.dao.entity.WechatUserInfo;
import com.xxx.wechat.front.dto.UserCardCodeDto;
import com.xxx.wechat.front.service.IWechatCardCodeService;
import com.xxx.wechat.front.service.IWechatCardService;
import com.xxx.wechat.front.service.IWechatRecommendService;
import com.xxx.wechat.helper.ConfigHelper;
import com.xxx.wechat.helper.TokenHelper;

/**
 * 控制层的抽象父类
 * @author yangk
 *
 */
public abstract class BaseController {
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	protected HttpServletRequest request;
	
	@Autowired
	protected HttpServletResponse response;
	
	@Autowired
	protected ConfigHelper configHelper;
	
	@Autowired
	protected TokenHelper tokenHelper;
	
	@Autowired
	IWechatRecommendService wechatRecommendService;
	
	@Autowired
	IWechatCardService wechatCardService;
	
	@Autowired
	IWechatCardCodeService wechatCardCodeService;
	
	/**
	 * cookie保存期限一年
	 *
	 */
	private static int USER_INFO_TIMEOUT = 365 * 24 * 60 * 60;

	
	/**
	 * 从cookie中获取用户openid
	 */
	protected String getOpenid() {
		return tokenHelper
				.parseJWT(getCookieValue(ConfigurationConfig.getInstance().getProperty(ConfigurationEnum.OPENID_COOKIE)));
	}
	
	/**
	 * 往cookie中存放信息，保存时间为一年
	 * @param key
	 * @param value
	 */
	protected void setResponseCookie(String key, String value) {
		Cookie cookie = new Cookie(key, value);
		cookie.setMaxAge(USER_INFO_TIMEOUT);
		cookie.setPath("/");
		response.addCookie(cookie);
	}
	
	/**
	 * 根据key从cookie中获取openid
	 * @param key
	 * @return
	 */
	protected String getCookieValue(String key) {
		if (CheckUtils.isNullOrEmpty(key)) {
			return null;
		}
		String openId = null;
		if (request.getCookies() != null) {
			for (Cookie cookie : request.getCookies()) {
				String cookieName = cookie.getName();
				if (key.equals(cookieName)) {
					openId = cookie.getValue();
					break;
				}
			}
		}
		return openId;
	}
	
	/**
	 * 从微信端获取用户信息
	 * @param openid
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	protected WechatUserInfo getWechatUserInfo(String token, String openid) throws UnsupportedEncodingException {
		WechatUserInfo wechatUserInfo = new WechatUserInfo();
		UserAPI userAPI = new UserAPI(WechatTokenConfig.getInstance().getAccessToken());
		logger.info("getWechatUserInfo start");
		// 已关注
		GetUserInfoResponse userInfoResponse = userAPI.getUserInfo(openid);
		try {
			logger.info(userInfoResponse.toJsonString());
		} catch (Exception e) {
			 logger.info(e.getMessage());
		 }
		if (CheckUtils.isNull(userInfoResponse) || userInfoResponse.getSubscribe() == Constant.Num.INT_ZERO) {
			// 未关注
			try {
				userInfoResponse = userAPI.getSNSUserInfo(token, openid);
				logger.info(userInfoResponse.getOpenid());
				logger.info(userInfoResponse.getHeadimgurl());
				logger.info(userInfoResponse.getNickname());
			} catch (Exception e) {
				logger.info("userInfoResponse失败"+ e.getMessage());
			}
		}
		
		logger.info("yucj ------- success");
			userInfoResponse.setNickname(URLEncoder.encode(userInfoResponse.getNickname(), Constant.Str.UTF8));
			wechatUserInfo.setOpenid(userInfoResponse.getOpenid());
			wechatUserInfo.setNickname(userInfoResponse.getNickname());
			wechatUserInfo.setSex(userInfoResponse.getSex() + "");
			wechatUserInfo.setProvince(userInfoResponse.getProvince());
			wechatUserInfo.setCity(userInfoResponse.getCity());
			wechatUserInfo.setCountry(userInfoResponse.getCountry());
			wechatUserInfo.setHeadimgurl(userInfoResponse.getHeadimgurl());
			wechatUserInfo.setUnionid(userInfoResponse.getUnionid());
			wechatUserInfo.setLanguage(userInfoResponse.getLanguage());
			wechatUserInfo.setSubscribeTime(userInfoResponse.getSubscribeTime());
			wechatUserInfo.setSubscribe(userInfoResponse.getSubscribe());
			
//			BeanUtils.copyProperties(wechatUserInfo, userInfoResponse);
			logger.info("yucj is null : "+wechatUserInfo);
			logger.info(wechatUserInfo.getOpenid());
			return wechatUserInfo;
	}

	
	/**
	 * 添加推荐关系
	 * 
	 */
	protected void createWechatRecommend(WechatRecommend wechatRecommend) {
		if (CheckUtils.isNullOrEmpty(wechatRecommend.getRecOpenid()) 
				|| CheckUtils.isNullOrEmpty(wechatRecommend.getRecEdOpenid())) {
			// 推荐者openid为空、被推荐者openid为空
			logger.error("缺少推荐信息。RecOpenid："+wechatRecommend.getRecOpenid()+"；RecEdOpenid："+wechatRecommend.getRecEdOpenid());
			return;
		}
//		if (wechatRecommend.getRecOpenid().equals(wechatRecommend.getRecEdOpenid())) {
//			// 推荐者openid和被推荐者openid相同
//			logger.info("openid:" + wechatRecommend.getRecOpenid() +"自己推荐自己，返回");
//			return;
//		}
		try {
			// 根据被推荐者openid获取推荐信息
			// 没有历史推荐数据可以添加推荐信息，否则不处理
//			List<WechatRecommend> oldWechatRecommend = wechatRecommendService.selectRecListByRecEdOpenid(wechatRecommend);
//			if (CheckUtils.isNull(oldWechatRecommend) || oldWechatRecommend.size() == Constant.Num.INT_ZERO) {
				// 没有被推荐过，添加推荐关系
				// 根据推荐者openid查看该用户的推荐数
				int recNum = wechatRecommendService.selectRecNumByRecOpenid(wechatRecommend.getRecOpenid());
				// 推荐数跟推荐上限数对比
				if (recNum >= Integer.valueOf(ConfigurationConfig.getInstance().getProperty(ConfigurationEnum.REC_NUM_LIMIT))) {
					// 推荐数已达上限
					logger.info("openid:" + wechatRecommend.getRecOpenid() +"推荐数已达上限");
					return;
				}
				wechatRecommendService.addWechatRecommend(wechatRecommend);
//			}
		} catch(Exception e) {
			logger.error("添加推荐关系失败：wechatRecommend=" + wechatRecommend + "错误信息："+ e.getMessage());
		}
	}
	
	/**
	 * 
	 */
	protected List<WechatCard> getWechatCardList(WechatCard wechatCard) {
		try {
			return wechatCardService.search(wechatCard);
		} catch(Exception e) {
			logger.error("获取卡券列表失败，错误信息：" + e.getMessage());
			return null;
		}
	}
	
	/**
	 * 从微信端获取用户卡券信息
	 * @param openid
	 * @param cardId
	 * @return
	 * @throws Exception
	 */
	protected UserCardCodeDto getUserCardCode(String openid, String cardId) throws Exception {
		// 初始化返回值
		UserCardCodeDto userCardCodeDto = new UserCardCodeDto();
		// 将openid赋值
		userCardCodeDto.setOpenid(openid);
		// 初始化卡券接口
		CardApi cardApi = new CardApi(WechatTokenConfig.getInstance().getAccessToken());
		// 获取用户卡券信息
		CardResponse cardResponse = cardApi.getCardList(openid, cardId);
		if (CheckUtils.isNull(cardResponse) || CheckUtils.isNull(cardResponse.getCardList()) || cardResponse.getCardList().size() == Constant.Num.INT_ZERO) {
			// 用户没有领取过卡券，返回
			return userCardCodeDto;
		}
		logger.info(JsonUtils.toJsonString(cardResponse.getCardList()));
		// 将结果赋值
		userCardCodeDto.setCardList(JsonUtils.toJsonString(cardResponse.getCardList()));
		// 返回结果
		return userCardCodeDto;
	}
	
	protected List<WechatCardCode> selectWechatCardCode(WechatCardCode wechatCardCode) {
		try {
			return wechatCardCodeService.select(wechatCardCode);
		} catch(Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	
	/**
	 * 获取微信二维码
	 * @param qrCode
	 * @return
	 * @throws Exception
	 */
	protected WechatQrcode getQrcode(QrCode qrCode) throws Exception {
		QrcodeAPI qrcodeAPI = new QrcodeAPI(WechatTokenConfig.getInstance().getAccessToken());
		QrcodeResponse qrcodeResponse = null;
		WechatQrcode wechatQrcode = new WechatQrcode();
		qrcodeResponse = qrcodeAPI.createQrcode(qrCode);
		wechatQrcode.setTicket(qrcodeResponse.getTicket());
		wechatQrcode.setUrl(qrcodeResponse.getUrl());
		return wechatQrcode;
	}
	
}
