package com.xxx.wechat.front.controller;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xxx.wechat.common.constant.Constant;
import com.xxx.wechat.common.utils.CheckUtils;
import com.xxx.wechat.common.wechat.api.AccessTokenApi;
import com.xxx.wechat.common.wechat.api.response.AccessTokenResponse;
import com.xxx.wechat.core.dao.entity.WechatUserInfo;
import com.xxx.wechat.front.service.IWechatUserInfoService;
import com.xxx.wechat.helper.ConfigHelper;

/**
 * 跳转网页时获得openid
 *
 */
@Controller
@RequestMapping("/oauth")
public class OAuthController extends BaseController {

	@Autowired
	private ConfigHelper configHelper;

	@Autowired
	private IWechatUserInfoService wechatUserInfoService;

	/**
	 * 做成回调url 调用微信接口获取认证返回code至回调url上
	 * 
	 * @param request
	 * @param resultUrl
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
		}
		// 获取请求认证参数
		String appId = configHelper.appId;
		String scope = Constant.WechatParams.AUTHORIZE_SCOPE_USERINFO;
		String state = configHelper.state;
		// check appID
		if (CheckUtils.isNullOrEmpty(appId)) {// 跳转至错误画面
			logger.error("appId：[" + appId + "]为空，请检查配置文件是否正确");
			return "redirect:" + "/common/error";
		}
		// 加工认证url 并跳转

		String redirect = String.format(Constant.WechatUrl.AUTHORIZE_OAUTH2_CONNECT, appId.trim(), backUrl.trim(),
				scope.trim(), state.trim());
		logger.info("获取微信端code路径：" + redirect);
		logger.info("yucj api redirect : " + redirect);
		return "redirect:" + redirect;

	}

	/**
	 * 根据code获取用户openid后跳转到需要带用户信息的最终页面
	 * 
	 * @param request
	 * @param code
	 *            获取微信重定向到自己设置的URL中code参数
	 * @param redirectUrl
	 *            跳转到最终页面的地址
	 * @return
	 */
	@RequestMapping("/url")
	public String oauth2url(HttpServletRequest request, @RequestParam String code, @RequestParam String redirectUrl,
			RedirectAttributes redirectAttributes, Model model) {

		logger.info("url start");

		// 服务器内controller之间跳转
		if (CheckUtils.isNullOrEmpty(code)) {
			logger.error("获取微信code:" + code + "失败。");
			redirectAttributes.addFlashAttribute("url", redirectUrl);
			return "common/error";
		}

		String appId = configHelper.appId;
		String appSecret = configHelper.appSecret;
		if (CheckUtils.isNullOrEmpty(appId) || CheckUtils.isNullOrEmpty(appSecret)) {
			logger.error("appId：[" + appId + "]或者appSecret：[" + appSecret + "]为空，请检查配置文件是否正确");
			return "/common/error";
		}
		String openid = null;
		// OauthApi api = null;
		try {
			// api = new OauthApi(WechatConfig.getInstance().getAccessToken(),
			// appId,
			// appSecret);
			// openid = api.getOpenId(code);
			AccessTokenApi accessTokenApi = new AccessTokenApi();
			AccessTokenResponse accessTokenResponse = accessTokenApi.getAccessTokenByCode(appId, appSecret, code);

			if (CheckUtils.isNullOrEmpty(accessTokenResponse.getOpenid())) {
				logger.error("获取openid：[" + openid + "]失败");
				redirectAttributes.addFlashAttribute("url", redirectUrl);
				return "/common/error";
			}
			openid = accessTokenResponse.getOpenid();
			WechatUserInfo wecahtUser = getWechatUserInfo(accessTokenResponse.getAccess_token(), openid);
			logger.info(wecahtUser.getOpenid());
			logger.info(wecahtUser.getHeadimgurl());
			wechatUserInfoService.insert(wecahtUser);

		} catch (Exception e) {
			logger.error("获取openid：[" + openid + "]信息失败，失败详细信息：" + e.getMessage());
			redirectAttributes.addFlashAttribute("url", redirectUrl);
			return "/common/error";
		}
		model.addAttribute("openid", openid);
		model.addAttribute("url", redirectUrl);
		setResponseCookie(configHelper.cookieKey, tokenHelper.createJWT(openid));
		logger.info("yucj url redirect : " + redirectUrl);

		return "common/jump";
	}

}
