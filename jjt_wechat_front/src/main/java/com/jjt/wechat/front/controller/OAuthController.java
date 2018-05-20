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

import com.jjt.common.api.OauthAPI;
import com.jjt.common.api.response.OauthGetTokenResponse;
import com.jjt.common.content.url.HttpRequestUrl;
import com.jjt.common.utils.CheckUtils;
import com.jjt.wechat.constants.WechatConstants;
import com.jjt.wechat.core.constants.ConfigConstants;
import com.jjt.wechat.core.util.SysPropUtil;
import com.jjt.wechat.front.service.IAccessTokenService;


@Controller
@RequestMapping("/oauth")
public class OAuthController extends BaseController {

	protected final Logger logger = LoggerFactory
			.getLogger(OAuthController.class);

	@Autowired
	private IAccessTokenService accessTokenService;

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
		String backUrl = SysPropUtil.getString(ConfigConstants.HOST_URL)
				+ "/oauth/url?redirectUrl=" + resultUrl;
		try {
			backUrl = java.net.URLEncoder.encode(backUrl, "utf-8");
		} catch (UnsupportedEncodingException e) {
			logger.error("backUrl 进行utf-8编码错误.错误信息：" + e.getMessage());
			e.printStackTrace();
		}
		// 获取请求认证参数
		String appid = SysPropUtil.getString(ConfigConstants.APP_ID);
		String scope = HttpRequestUrl.AUTHORIZE_SCOPE_BASE;
		String state = SysPropUtil.getString(ConfigConstants.STATE);
		// check appID
		if (CheckUtils.isNullOrEmpty(appid)) {// 跳转至错误画面
			logger.error("appid：[" + appid + "]为null或空，请检测数据库配置是否正确");
			return "redirect:" + WechatConstants.View.ERROR_ACTION;
		}
		// 加工认证url 并跳转

		String redirect = String.format(
				HttpRequestUrl.AUTHORIZE_OAUTH2_CONNECT, appid.trim(),
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
			return "redirect:" + WechatConstants.View.ERROR_ACTION;
		}

		String appId = SysPropUtil.getString(ConfigConstants.APP_ID);
		String appSecret = SysPropUtil.getString(ConfigConstants.APP_SECRET);
		if (CheckUtils.isNullOrEmpty(appId)
				|| CheckUtils.isNullOrEmpty(appSecret)) {
			logger.error("appid：[" + appId + "]或者appSecret：[" + appSecret
					+ "]为null或空，请检测数据库配置是否正确");
			return "redirect:" + WechatConstants.View.ERROR_ACTION;
		}
		OauthAPI api = null;
		OauthGetTokenResponse oauthGetTokenResponse = null;

		try {
			api = new OauthAPI(accessTokenService.getAccessToken()
					.getAccess_token(), appId, appSecret);
			oauthGetTokenResponse = api.getToken(code);
			openId = oauthGetTokenResponse.getOpenid();
			if (CheckUtils.isNullOrEmpty(openId)) {
				logger.error("获取openId：[" + openId + "]失败，失败详细信息："
						+ oauthGetTokenResponse.toJsonString());
				redirectAttributes.addFlashAttribute("url", redirectUrl);
				return "redirect:" + WechatConstants.View.ERROR_ACTION;
			}

		} catch (Exception e) {
			logger.error("获取openId：[" + openId + "]失败，失败详细信息：" + e.getMessage());
			redirectAttributes.addFlashAttribute("url", redirectUrl);
			return "redirect:" + WechatConstants.View.ERROR_ACTION;
		}
		logger.info("获取openId：[" + openId + "]成功。");
		model.addAttribute("openId", openId);
		model.addAttribute("url", redirectUrl);
		setResponseCookie(configHelper.cookieKey,
				tokenHelper.createJWT(openId));
		// return "redirect:" + redirectUrl;
		return "common/jump";
	}

}
