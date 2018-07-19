package com.xxx.wechat.front.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xxx.wechat.common.constant.ConfigurationEnum;
import com.xxx.wechat.common.utils.SignUtil;
import com.xxx.wechat.common.wechat.api.entity.WxCardApiSignature;
import com.xxx.wechat.core.config.ConfigurationConfig;
import com.xxx.wechat.core.config.WechatTokenConfig;

/**
 * 微信jssdk配置
 * @author yangk
 *
 */
@Controller
@RequestMapping("/config")
public class ConfigController extends BaseController {

	/**
	 * 配置jsapi_ticket
	 * @param url
	 * @return
	 */
	@RequestMapping("/jsapiTicket")
	public @ResponseBody Map<String, String> jsapiTicket(@RequestParam(value = "url", required = true) String url) {
		try {
			String appId = ConfigurationConfig.getInstance().getProperty(ConfigurationEnum.APPID);
			String jsApiTicket = WechatTokenConfig.getInstance().getJsapiTicket();
			Map<String, String> resultMap = SignUtil.jsApiTicketSign(jsApiTicket, url);
			resultMap.put("appid", appId);
			return resultMap;
		} catch (Exception e) {
			logger.error("配置 jsapiTicket 失败：" + e.getMessage());
			return null;
		}
		
	}
	
	/**
	 * 配置api_ticket
	 * @param apiSignature
	 * @return
	 */
	@RequestMapping("/apiTicket")
	public @ResponseBody WxCardApiSignature apiTicket(WxCardApiSignature apiSignature) {
		try {
			String apiTicket = WechatTokenConfig.getInstance().getApiTicket();
			apiSignature = SignUtil.createWxCardJsApiSignature(apiTicket, apiSignature);
			return apiSignature;
		} catch (Exception e) {
			logger.error("配置 apiTicket 失败：" + e.getMessage());
			return null;
		}
		
	}
	
}
