package com.xxx.wechat.front.config;

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

import com.alibaba.fastjson.JSON;
import com.xxx.wechat.common.constant.ConfigurationEnum;
import com.xxx.wechat.common.constant.Constant;
import com.xxx.wechat.common.http.HttpResponseNullException;
import com.xxx.wechat.common.utils.CheckUtils;
import com.xxx.wechat.common.wechat.api.MenuApi;
import com.xxx.wechat.common.wechat.api.entity.Matchrule;
import com.xxx.wechat.common.wechat.api.entity.Menu;
import com.xxx.wechat.common.wechat.api.entity.MenuButton;
import com.xxx.wechat.common.wechat.api.enums.MenuType;
import com.xxx.wechat.common.wechat.api.enums.ResultType;
import com.xxx.wechat.core.config.ConfigurationConfig;
import com.xxx.wechat.core.config.WechatTokenConfig;
import com.xxx.wechat.core.service.IWechatTokenService;

/**
 * 程序啟動時創建菜單
 *
 */
@Configuration
public class AppListener implements ApplicationListener<ContextRefreshedEvent>{
	
	private static final Logger logger = LoggerFactory.getLogger(AppListener.class);
	
	@Autowired
	private IWechatTokenService wechatTokenService;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		
		logger.info("Create WeChat Menu Start");
		
		if (!CheckUtils.isNull(wechatTokenService)) {
			try {
				WechatTokenConfig.getInstance().getWechatTokenOnline();
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
			createMenu();
			createAddconditionalMenu();
		}
	}

	/**
	 * 創建菜單
	 */
	private static void createMenu() {
		String accessToken = WechatTokenConfig.getInstance().getAccessToken();
		String hosturl = ConfigurationConfig.getInstance().getProperty(ConfigurationEnum.HOST_URL);
		if (CheckUtils.isNullOrEmpty(accessToken)) {
			logger.error("系统配置参数accessToken错误：" + "[" + accessToken + "]");
			// 强制系统退出
			System.exit(-1);
		}
		Menu menu = new Menu();
		// 准备一级主菜单
		MenuButton main1 = new MenuButton();
		main1.setName("我");
		// 准备子菜单
		MenuButton sub11 = new MenuButton();
		sub11.setName("愿");
		sub11.setType(MenuType.VIEW.toString());
//		sub11.setUrl(hosturl+Constant.ControllerUrl.MENU_ONE_ONE);
		sub11.setUrl("http://zmall.99zmall.com/hot_goods?shopId=73808&kind=00&type=0");
		MenuButton sub12 = new MenuButton();
		sub12.setName("得");
		sub12.setType(MenuType.VIEW.toString());
		sub12.setUrl(hosturl+Constant.ControllerUrl.MENU_ONE_TWO);
		sub12.setUrl("http://www.elizabece.com/#/?uname=test");
		MenuButton sub13 = new MenuButton();
		sub13.setName("一");
		sub13.setType(MenuType.VIEW.toString());
		sub13.setUrl(hosturl+Constant.ControllerUrl.MENU_ONE_THREE);
		MenuButton sub14 = new MenuButton();
		sub14.setName("人");
		sub14.setType(MenuType.VIEW.toString());
		sub14.setUrl(hosturl+Constant.ControllerUrl.MENU_ONE_FOUR);
		MenuButton sub15 = new MenuButton();
		sub15.setName("心");
		sub15.setType(MenuType.VIEW.toString());
		sub15.setUrl(hosturl+Constant.ControllerUrl.MENU_ONE_FIVE);

		List<MenuButton> list = new ArrayList<MenuButton>();
		list.add(sub11);
		list.add(sub12);
		list.add(sub13);
		list.add(sub14);
		list.add(sub15);
		// 将子菜单放入主菜单里
		main1.setSub_button(list);

		// 准备二级主菜单
		MenuButton main2 = new MenuButton();
		main2.setName("爱");
		// 准备子菜单
		MenuButton sub21 = new MenuButton();
		sub21.setName("我要充电");
		sub21.setType(MenuType.VIEW.toString());
		sub21.setUrl(hosturl+Constant.ControllerUrl.MENU_TWO_ONE);
		MenuButton sub22 = new MenuButton();
		sub22.setName("发照片");
		sub22.setKey("FZP");
		sub22.setType(MenuType.PIC_PHOTO_OR_ALBUM.toString());
		MenuButton sub23 = new MenuButton();
		sub23.setName("扫码事件");
		sub23.setKey("SMSJ");
		sub23.setType(MenuType.SCANCODE_PUSH.toString());
		
		MenuButton sub24 = new MenuButton();
		sub24.setName("扫码提示");
		sub24.setKey("SMTS");
		sub24.setType(MenuType.SCANCODE_WAITMSG.toString());
		MenuButton sub25 = new MenuButton();
		sub25.setName("我的位置");
		sub25.setKey("WDWZ");
		sub25.setType(MenuType.LOCATION_SELECT.toString());
		
		List<MenuButton> list2 = new ArrayList<MenuButton>();
		list2.add(sub21);
		list2.add(sub22);
		list2.add(sub23);
		list2.add(sub24);
		list2.add(sub25);
		// 将子菜单放入主菜单里
		main2.setSub_button(list2);

		// 准备三级主菜单
		MenuButton main3 = new MenuButton();
		main3.setName("你");
		// 准备子菜单
		MenuButton sub31 = new MenuButton();
		sub31.setName("白");
		sub31.setType(MenuType.VIEW.toString());
		sub31.setUrl(hosturl+Constant.ControllerUrl.MENU_THREE_ONE);
		
		MenuButton sub32 = new MenuButton();
		sub32.setName("首");
		sub32.setType(MenuType.VIEW.toString());
		sub32.setUrl(hosturl+Constant.ControllerUrl.MENU_THREE_TWO);
		MenuButton sub33 = new MenuButton();
		sub33.setType(MenuType.VIEW.toString());
		sub33.setName("不");
		sub33.setUrl(hosturl+Constant.ControllerUrl.MENU_THREE_THREE);
		MenuButton sub34 = new MenuButton();
		sub34.setName("相");
		sub34.setType(MenuType.VIEW.toString());
		sub34.setUrl(hosturl+Constant.ControllerUrl.MENU_THREE_FOUR);
		MenuButton sub35 = new MenuButton();
		sub35.setName("离");
		sub35.setType(MenuType.VIEW.toString());
		sub35.setUrl(hosturl+Constant.ControllerUrl.MENU_THREE_FIVE);

		List<MenuButton> list3 = new ArrayList<MenuButton>();
		list3.add(sub31);
		list3.add(sub32);
		list3.add(sub33);
		list3.add(sub34);
		list3.add(sub35);
		// 将子菜单放入主菜单里
		main3.setSub_button(list3);

		List<MenuButton> mainList = new ArrayList<MenuButton>();
		mainList.add(main1);
		mainList.add(main2);
		mainList.add(main3);
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
	
	// 创建个性化菜单
	private static void createAddconditionalMenu() {

		String accessToken = WechatTokenConfig.getInstance().getAccessToken();
		String hosturl = ConfigurationConfig.getInstance().getProperty(ConfigurationEnum.HOST_URL);
		if (CheckUtils.isNullOrEmpty(accessToken)) {
			logger.error("系统配置参数accessToken错误：" + "[" + accessToken + "]");
			// 强制系统退出
			System.exit(-1);
		}
		MenuApi menuAPI = new MenuApi(accessToken);

		Menu request = new Menu();

		// 准备一级主菜单
		MenuButton main1 = new MenuButton();
		main1.setName("我");
		// 准备子菜单
		MenuButton sub11 = new MenuButton();
		sub11.setName("愿");
		sub11.setType(MenuType.VIEW.toString());
//		sub11.setUrl(hosturl+Constant.ControllerUrl.MENU_ONE_ONE);
		sub11.setUrl("http://zmall.99zmall.com/hot_goods?shopId=73808&kind=00&type=0");
		MenuButton sub12 = new MenuButton();
		sub12.setName("得");
		sub12.setType(MenuType.VIEW.toString());
		sub12.setUrl(hosturl+Constant.ControllerUrl.MENU_ONE_TWO);
		sub12.setUrl("http://www.elizabece.com/#/?uname=test");
		MenuButton sub13 = new MenuButton();
		sub13.setName("一");
		sub13.setType(MenuType.VIEW.toString());
		sub13.setUrl(hosturl+Constant.ControllerUrl.MENU_ONE_THREE);
		MenuButton sub14 = new MenuButton();
		sub14.setName("人");
		sub14.setType(MenuType.VIEW.toString());
		sub14.setUrl(hosturl+Constant.ControllerUrl.MENU_ONE_FOUR);
		MenuButton sub15 = new MenuButton();
		sub15.setName("心");
		sub15.setType(MenuType.VIEW.toString());
		sub15.setUrl(hosturl+Constant.ControllerUrl.MENU_ONE_FIVE);

		List<MenuButton> list = new ArrayList<MenuButton>();
		list.add(sub11);
		list.add(sub12);
		list.add(sub13);
		list.add(sub14);
		list.add(sub15);
		// 将子菜单放入主菜单里
		main1.setSub_button(list);

		// 准备二级主菜单
		MenuButton main2 = new MenuButton();
		main2.setName("爱");
		// 准备子菜单
		MenuButton sub21 = new MenuButton();
		sub21.setName("我要充电");
		sub21.setType(MenuType.VIEW.toString());
		sub21.setUrl(hosturl+Constant.ControllerUrl.MENU_TWO_ONE);
		MenuButton sub22 = new MenuButton();
		sub22.setName("发照片");
		sub22.setKey("FZP");
		sub22.setType(MenuType.PIC_PHOTO_OR_ALBUM.toString());
		MenuButton sub23 = new MenuButton();
		sub23.setName("扫码事件");
		sub23.setKey("SMSJ");
		sub23.setType(MenuType.SCANCODE_PUSH.toString());
		
		MenuButton sub24 = new MenuButton();
		sub24.setName("扫码提示");
		sub24.setKey("SMTS");
		sub24.setType(MenuType.SCANCODE_WAITMSG.toString());
		MenuButton sub25 = new MenuButton();
		sub25.setName("我的位置");
		sub25.setKey("WDWZ");
		sub25.setType(MenuType.LOCATION_SELECT.toString());
		
		List<MenuButton> list2 = new ArrayList<MenuButton>();
		list2.add(sub21);
		list2.add(sub22);
		list2.add(sub23);
		list2.add(sub24);
		list2.add(sub25);
		// 将子菜单放入主菜单里
		main2.setSub_button(list2);

		// 准备三级主菜单
		MenuButton main3 = new MenuButton();
		main3.setName("你");
		// 准备子菜单
		MenuButton sub31 = new MenuButton();
		sub31.setName("白");
		sub31.setType(MenuType.VIEW.toString());
		sub31.setUrl(hosturl+Constant.ControllerUrl.MENU_THREE_ONE);
		
		MenuButton sub32 = new MenuButton();
		sub32.setName("首");
		sub32.setType(MenuType.VIEW.toString());
		sub32.setUrl(hosturl+Constant.ControllerUrl.MENU_THREE_TWO);
		MenuButton sub33 = new MenuButton();
		sub33.setType(MenuType.VIEW.toString());
		sub33.setName("不");
		sub33.setUrl(hosturl+Constant.ControllerUrl.MENU_THREE_THREE);
		MenuButton sub34 = new MenuButton();
		sub34.setName("相");
		sub34.setType(MenuType.VIEW.toString());
		sub34.setUrl(hosturl+Constant.ControllerUrl.MENU_THREE_FOUR);
		MenuButton sub35 = new MenuButton();
		sub35.setName("离");
		sub35.setType(MenuType.VIEW.toString());
		sub35.setUrl(hosturl+Constant.ControllerUrl.MENU_THREE_FIVE);

		List<MenuButton> list3 = new ArrayList<MenuButton>();
		list3.add(sub31);
		list3.add(sub32);
		list3.add(sub33);
		list3.add(sub34);
		list3.add(sub35);
		// 将子菜单放入主菜单里
		main3.setSub_button(list3);

		List<MenuButton> mainList = new ArrayList<MenuButton>();
		mainList.add(main1);
		mainList.add(main2);
		mainList.add(main3);
		
		// 个性化菜单匹配规则
		Matchrule matchrule = new Matchrule();
		matchrule.setTag_id(ConfigurationConfig.getInstance().getProperty(ConfigurationEnum.WECHAT_TAG_ID));
		
		// 将主菜单加入请求对象
		request.setButton(mainList);
		// 添加匹配规则
		request.setMatchrule(matchrule);
		logger.info(JSON.toJSONString(request));
		// 创建菜单
		ResultType resultType;
		try {
			resultType = menuAPI.createMenu(request);
			if (CheckUtils.isNull(resultType)) {
				logger.error("Create menu error.");
			} else {
				logger.info(resultType.getDescription());
			}
		} catch (IOException | HttpResponseNullException e) {
			logger.error("Create menu error.");
		}
	}
}
