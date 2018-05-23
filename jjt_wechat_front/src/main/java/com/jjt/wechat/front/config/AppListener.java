package com.jjt.wechat.front.config;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;


import org.apache.http.client.ClientProtocolException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;

import com.jjt.wechat.common.constant.Constant;
import com.jjt.wechat.common.http.HttpResponseNullException;
import com.jjt.wechat.common.utils.CheckUtils;
import com.jjt.wechat.common.wechat.api.MenuApi;
import com.jjt.wechat.common.wechat.api.entity.Menu;
import com.jjt.wechat.common.wechat.api.entity.MenuButton;
import com.jjt.wechat.common.wechat.api.enums.MenuType;
import com.jjt.wechat.common.wechat.api.enums.ResultType;
import com.jjt.wechat.core.config.SystemConfig;
import com.jjt.wechat.core.config.WechatConfig;
import com.jjt.wechat.core.service.IWechatTokenService;
import com.jjt.wechat.helper.ConfigHelper;

/**
 * 程序啟動時創建菜單
 *
 */
@Configuration
public class AppListener implements ApplicationListener<ContextRefreshedEvent>{
	
	private static final Logger logger = LoggerFactory.getLogger(AppListener.class);
	
	@Autowired
	private IWechatTokenService wechatTokenService;
	
	private static final String menu_1 = "我的";
	
	private static final String menu_1_1 = "体检券";
	
	private static final String jump = "/oauth/url?redirectUrl=";
	
	private static final String http = "http://";
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		
		logger.info("AppListener.start");
		
		if (!CheckUtils.isNull(wechatTokenService)) {
			createMenu();
		}
	}

	/**
	 * 創建菜單
	 */
	private static void createMenu() {
		
		String accessToken = WechatConfig.getInstance().getAccessToken();
		SystemConfig systemConfig = SystemConfig.getInstance();
//		String appId = systemConfig.getProperty(Constant.Configuration.APPID);
//		String scope = Constant.WechatParams.AUTHORIZE_SCOPE_BASE;
//		String url = Constant.WechatUrl.AUTHORIZE_OAUTH2_CONNECT;
//		String state = systemConfig.getProperty(Constant.Configuration.STATE);
		String hosturl = systemConfig.getProperty(Constant.Configuration.HOST_URL);
		if (CheckUtils.isNullOrEmpty(accessToken)) {
			logger.error("系统配置参数accessToken错误：" + "[" + accessToken + "]");
			// 强制系统退出
			System.exit(-1);
		}
		Menu menu = new Menu();
		MenuButton menuB1 = new MenuButton();
		menuB1.setName(menu_1);
		MenuButton menuB11 = new MenuButton();
		menuB11.setName(menu_1_1);
		menuB11.setType(MenuType.VIEW.toString());
		String redirectUrl = hosturl+Constant.ControllerUrl.MENU_VOUCHER;
		String urlre = redirectUrl;//String.format(url,appId,redirectUrl ,scope,state);
		menuB11.setUrl(urlre);
		
		List<MenuButton> list1 = new ArrayList<MenuButton>();
		list1.add(menuB11);
		menuB1.setSub_button(list1);
		List<MenuButton> mainList = new ArrayList<MenuButton>();
		mainList.add(menuB1);
		menu.setButton(mainList);

		// 创建菜单
		ResultType resultType;
		try {
			MenuApi menuApi = new MenuApi(accessToken);
			resultType = menuApi.createMenu(menu);
			if (CheckUtils.isNull(resultType)) {
				logger.error("Create menu error.");
			} else {
				logger.info(resultType.getDescription());
			}
		} catch (ClientProtocolException e) {
			logger.info("错误原因:"+e.getMessage());
		} catch (IOException e) {
			logger.info("错误原因:"+e.getMessage());
		} catch (HttpResponseNullException e) {
			logger.info("错误原因:"+e.getMessage());
		}
	}
}
