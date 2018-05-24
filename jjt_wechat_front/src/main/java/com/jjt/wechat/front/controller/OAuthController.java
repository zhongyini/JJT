package com.jjt.wechat.front.controller;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
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
import com.jjt.wechat.common.wechat.api.AccessTokenApi;
import com.jjt.wechat.common.wechat.api.UserAPI;
import com.jjt.wechat.common.wechat.api.response.AccessTokenResponse;
import com.jjt.wechat.common.wechat.api.response.GetUserInfoResponse;
import com.jjt.wechat.core.config.WechatConfig;
import com.jjt.wechat.core.dao.entity.WechatUserInfo;
import com.jjt.wechat.front.service.IWechatUserService;
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
	private ConfigHelper configHelper;
	
	@Autowired
	private IWechatUserService wechatUserService;
	
	/**
	 * 做成回调url 调用微信接口获取认证返回code至回调url上
	 * 
	 * @param request
	 * @param backUrl
	 * @return
	 */
	@RequestMapping("/api")
	public String oauth2Api(HttpServletRequest request, @RequestParam String resultUrl) {
		// 做成回调url，将客户端请求地址埋入回调url中
		String backUrl = configHelper.hostUrl + "/oauth/url?redirectUrl=" + resultUrl;
		try {
			backUrl = java.net.URLEncoder.encode(backUrl, "utf-8");
		} catch (UnsupportedEncodingException e) {
			logger.error("backUrl 进行utf-8编码错误.错误信息：" + e.getMessage());
			e.printStackTrace();
		}
		// 获取请求认证参数
		String appid = configHelper.appId;
		String scope = Constant.WechatParams.AUTHORIZE_SCOPE_BASE;
		String state = configHelper.state;
		// check appID
		if (CheckUtils.isNullOrEmpty(appid)) {// 跳转至错误画面
			logger.error("appid：[" + appid + "]为null或空，请检测配置文件是否正确");
			return "redirect:" + "/common/error";
		}
		// 加工认证url 并跳转

		String redirect = String.format(
				Constant.WechatUrl.AUTHORIZE_OAUTH2_CONNECT, appid.trim(),
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
					+ "]为null或空，请检测配置文件是否正确");
			return "/common/error";
		}
//		OauthApi api = null;
		try {
//			api = new OauthApi(WechatConfig.getInstance().getAccessToken(), appId, appSecret);
//			openId =  api.getOpenId(code);
			AccessTokenApi accessTokenApi = new AccessTokenApi();
			AccessTokenResponse accessTokenResponse = accessTokenApi.getAccessTokenByCode(appId, appSecret, code);
			
			if (CheckUtils.isNullOrEmpty(accessTokenResponse.getOpenid())) {
				logger.error("获取openId：[" + openId + "]失败");
				redirectAttributes.addFlashAttribute("url", redirectUrl);
				return "/common/error";
			}
			openId = accessTokenResponse.getOpenid();
			WechatUserInfo wecahtUser = new WechatUserInfo();
			wecahtUser = getWechatUserInfo(accessTokenResponse.getOpenid(), wecahtUser);
			
		} catch (Exception e) {
			logger.error("获取openId：[" + openId + "]失败，失败详细信息：" + e.getMessage());
			redirectAttributes.addFlashAttribute("url", redirectUrl);
			return "/common/error";
		}
		model.addAttribute("openId", openId);
		model.addAttribute("url", redirectUrl);
		setResponseCookie(configHelper.cookieKey, tokenHelper.createJWT(openId));
		logger.info(redirectUrl);
		return "common/jump";
	}

	private WechatUserInfo getWechatUserInfo(String openId, WechatUserInfo wechatUser) {
		UserAPI userAPI = new UserAPI(WechatConfig.getInstance().getAccessToken());
		GetUserInfoResponse userInfoResponse = userAPI.getUserInfo(openId);
		try {
			BeanUtils.copyProperties(wechatUser, userInfoResponse);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		wechatUserService.insert(wechatUser);
		return wechatUser;
	}
	
}
