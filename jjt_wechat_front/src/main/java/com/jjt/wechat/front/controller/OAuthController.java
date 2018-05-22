package com.jjt.wechat.front.controller;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jjt.wechat.common.constant.Constant;
import com.jjt.wechat.common.utils.CheckUtils;
import com.jjt.wechat.common.utils.DateUtils;
import com.jjt.wechat.common.utils.JsonUtils;
import com.jjt.wechat.common.wechat.api.OauthApi;
import com.jjt.wechat.core.config.SystemConfig;
import com.jjt.wechat.core.dao.entity.SnsToken;
import com.jjt.wechat.core.dao.entity.WechatToken;
import com.jjt.wechat.core.service.SnsTokenService;
import com.jjt.wechat.core.service.WechatTokenService;
import com.jjt.wechat.helper.ConfigHelper;


/**
 * 跳转网页时获得openId
 *
 */
@Controller
@RequestMapping("/oauth")
public class OAuthController extends BaseController {
	
	protected final Logger logger = LoggerFactory
			.getLogger(OAuthController.class);
	
	@Autowired
	private WechatTokenService wechatTokenService;
	
	@Autowired
	private SnsTokenService snsTokenService;
	
	@Autowired
	private ConfigHelper configHelper;
	
	/**
	 * 做成回调url 调用微信接口获取认证返回code至回调url上
	 * 
	 * @param request
	 * @param backUrl
	 * @return
	 */
	@RequestMapping("/api")
	public String oauth2Api(HttpServletRequest request,
			@RequestParam String resultUrl) {
		// 做成回调url，将客户端请求地址埋入回调url中
		SystemConfig systemConfig = SystemConfig.getInstance();
		String backUrl = systemConfig.getProperty(Constant.Configuration.HOST_URL) + "/oauth/url?redirectUrl=" + resultUrl;
		try {
			backUrl = java.net.URLEncoder.encode(backUrl, "utf-8");
		} catch (UnsupportedEncodingException e) {
			logger.error("backUrl 进行utf-8编码错误.错误信息：" + e.getMessage());
			e.printStackTrace();
		}
		// 获取请求认证参数
		String appid = systemConfig.getProperty(Constant.Configuration.APPID);
		String scope = Constant.WechatParams.AUTHORIZE_SCOPE_BASE;
		String state = systemConfig.getProperty(Constant.Configuration.STATE);
		// check appID
		if (CheckUtils.isNullOrEmpty(appid)) {// 跳转至错误画面
			logger.error("appid：[" + appid + "]为null或空，请检测数据库配置是否正确");
			return "redirect:" + "/common/error";
		}
		// 加工认证url 并跳转

		String redirect = String.format(
				Constant.WechatUrl.AUTHORIZE_CONNECT_QRCONNECT, appid.trim(),
				backUrl.trim(), scope.trim(), state.trim());
		logger.info(redirect);
		return "redirect:" + redirect;

	}
	
	/**
	 * 根据code获取用户openId后跳转到需要带用户信息的最终页面
	 * 
	 * @param request
	 * @param code
	 *            获取微信重定向到自己设置的URL中code参数
	 * @param redirectUrl
	 *            跳转到最终页面的地址
	 * @return
	 */
	@RequestMapping("/url")
	public String oauth2url(HttpServletRequest request,
			@RequestParam String code, @RequestParam String redirectUrl,
			RedirectAttributes redirectAttributes, Model model) {
		String openId = getOpenId();
		if (!CheckUtils.isNull(openId)) {
			logger.info("cookie中有openId：[" + openId + "]。");
			model.addAttribute("openId", openId);
			model.addAttribute("url", redirectUrl);
			return "common/jump";
		}

		// 服务器内controller之间跳转
		if (CheckUtils.isNullOrEmpty(code)) {
			logger.error("获取微信code:" + code + "失败。");
			redirectAttributes.addFlashAttribute("url", redirectUrl);
			return "common/error";
		}

		String appId = configHelper.appId;
		String appSecret = configHelper.appSecret;
		if (CheckUtils.isNullOrEmpty(appId)
				|| CheckUtils.isNullOrEmpty(appSecret)) {
			logger.error("appid：[" + appId + "]或者appSecret：[" + appSecret
					+ "]为null或空，请检测配置是否正确");
			return "/common/error";
		}
		OauthApi api = null;
//		SnsToken snsToken = new SnsToken();
//		String snsTokenStr = Constant.EMPTY;
		try {
			api = new OauthApi(wechatTokenService.findFirstByOrderByCreateDateDesc().getAccessToken()
					, appId, appSecret);
			openId =  api.getOpenId(code);
			if (CheckUtils.isNullOrEmpty(openId)) {
				logger.error("获取openId：[" + openId + "]失败");
				redirectAttributes.addFlashAttribute("url", redirectUrl);
				return "/common/error";
			}
			
			//api = new OauthApi("",appId, appSecret);
//			snsTokenStr =  api.getOpenId(code);
//			if (CheckUtils.isNullOrEmpty(snsTokenStr)) {
//				logger.error("获取snstoken：[" + openId + "]失败");
//				redirectAttributes.addFlashAttribute("url", redirectUrl);
//				return "/common/error";
//			}
//			snsToken.setAccessToken(JsonUtils.getStringFromJSONObject(snsTokenStr, "access_token").toString());
//			snsToken.setExpiresIn(Integer.valueOf(JsonUtils.getStringFromJSONObject(snsTokenStr, "expires_in").toString()));
//			snsToken.setOpenid(JsonUtils.getStringFromJSONObject(snsTokenStr, "openid").toString());
//			snsToken.setRefreshToken(JsonUtils.getStringFromJSONObject(snsTokenStr, "refresh_token").toString());
//			snsToken.setScope(JsonUtils.getStringFromJSONObject(snsTokenStr, "scope").toString());
//			snsToken.setUnionid(JsonUtils.getStringFromJSONObject(snsTokenStr, "unionid").toString());
		} catch (Exception e) {
//			if (!CheckUtils.isNullOrEmpty(snsTokenStr)) {
//				snsToken.setErrcode(JsonUtils.getStringFromJSONObject(snsTokenStr, "errcode").toString());
//				snsToken.setErrmsg(JsonUtils.getStringFromJSONObject(snsTokenStr, "errmsg").toString());
//			}
			logger.error("获取openId：[" + openId + "]失败，失败详细信息：" + e.getMessage());
			redirectAttributes.addFlashAttribute("url", redirectUrl);
			return "/common/error";
		}
//		snsToken.setCreateDate(DateUtils.getNowTimestamp());
//		logger.info(snsToken.toString());
//		snsTokenService.save(snsToken);
//		logger.info("获取openId：[" + snsToken.getOpenid() + "]成功。");
		model.addAttribute("openId", openId);
		model.addAttribute("url", redirectUrl);
		setResponseCookie(configHelper.cookieKey,
				tokenHelper.createJWT(openId));
		logger.info(redirectUrl);
		return "common/jump";
	}

}
