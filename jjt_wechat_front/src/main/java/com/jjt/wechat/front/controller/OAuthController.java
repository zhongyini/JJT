package com.jjt.wechat.front.controller;

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
import com.jjt.wechat.common.wechat.api.OauthApi;
import com.jjt.wechat.core.config.SystemConfig;
import com.jjt.wechat.core.service.WechatTokenService;


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

		String appId =SystemConfig.getInstance().getProperty(Constant.Configuration.APPID);
		String appSecret = SystemConfig.getInstance().getProperty(Constant.Configuration.APPSECRET);
		if (CheckUtils.isNullOrEmpty(appId)
				|| CheckUtils.isNullOrEmpty(appSecret)) {
			logger.error("appid：[" + appId + "]或者appSecret：[" + appSecret
					+ "]为null或空，请检测数据库配置是否正确");
			return "/common/error";
		}
		OauthApi api = null;
		try {
			api = new OauthApi(wechatTokenService.findFirstByOrderByCreateDateDesc().getAccessToken()
					, appId, appSecret);
			openId =  api.getOpenId(code);
			if (CheckUtils.isNullOrEmpty(openId)) {
				logger.error("获取openId：[" + openId + "]失败");
				redirectAttributes.addFlashAttribute("url", redirectUrl);
				return "/common/error";
			}
		} catch (Exception e) {
			logger.error("获取openId：[" + openId + "]失败，失败详细信息：" + e.getMessage());
			redirectAttributes.addFlashAttribute("url", redirectUrl);
			return "/common/error";
		}
		logger.info("获取openId：[" + openId + "]成功。");
		model.addAttribute("openId", openId);
		model.addAttribute("url", redirectUrl);
		setResponseCookie(configHelper.cookieKey,
				tokenHelper.createJWT(openId));
		logger.info(redirectUrl);
		return "common/jump";
	}

}
