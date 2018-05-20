package com.jjt.wechat.config;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;

import com.alibaba.fastjson.JSON;
import com.jjt.common.api.MenuAPI;
import com.jjt.common.api.entity.Menu;
import com.jjt.common.api.entity.MenuButton;
import com.jjt.common.api.enums.MenuType;
import com.jjt.common.api.enums.ResultType;
import com.jjt.common.content.url.ControllerUrl;
import com.jjt.common.content.url.HttpRequestUrl;
import com.jjt.common.utils.CheckUtils;
import com.jjt.wechat.constants.WechatConstants;
import com.jjt.wechat.core.config.ApiConfig;
import com.jjt.wechat.core.config.UrlConfig;
import com.jjt.wechat.core.constants.ConfigConstants;
import com.jjt.wechat.core.constants.UrlConstant;
import com.jjt.wechat.core.util.SysPropUtil;

@Configuration
public class AppListener implements ApplicationListener<ContextRefreshedEvent> {
	private static final Logger logger = LoggerFactory
			.getLogger(AppListener.class);

	private static final String jump = "/oauth/url?redirectUrl=";

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		logger.info("AppListener.start");

//		if (!CheckUtils.isNull(accessTokenService)) {
//
//			createMenu();
//		}
	}

	private static void createMenu() {

		// String hostUrl = SysPropUtil.getString(ConfigConstants.HOST_URL);
		// if (CheckUtils.isNullOrEmpty(hostUrl)) {
		// logger.error("系统配置参数hostUrl错误：" + "[" + hostUrl + "]");
		// // 强制系统退出
		// System.exit(-1);
		// }
		// hostUrl = hostUrl.trim();
		// if (hostUrl.endsWith("/")) {
		// hostUrl = hostUrl.substring(0, hostUrl.length() - 1);
		// }
		// String accessToken = ApiConfig.getInstance().getAccessToken();
		// if (CheckUtils.isNullOrEmpty(accessToken)) {
		// logger.error("系统配置参数accessToken错误：" + "[" + accessToken + "]");
		// // 强制系统退出
		// System.exit(-1);
		// }

		String hostUrl = SysPropUtil.getString(ConfigConstants.HOST_URL);
		String appid = SysPropUtil.getString(ConfigConstants.APP_ID);
		String scope = HttpRequestUrl.AUTHORIZE_SCOPE_BASE;
		String state = SysPropUtil.getString(ConfigConstants.STATE);

		String accessToken = ApiConfig.getInstance().getAccessToken();
		if (CheckUtils.isNullOrEmpty(accessToken)) {
			logger.error("系统配置参数accessToken错误：" + "[" + accessToken + "]");
			// 强制系统退出
			System.exit(-1);
		}
		MenuAPI menuAPI = new MenuAPI(accessToken);

		Menu request = new Menu();

		// 准备一级主菜单
		MenuButton main1 = new MenuButton();
		main1.setType(MenuType.CLICK);
		main1.setKey(WechatConstants.ButtonKey.WD);
		main1.setName("我的");
		// 准备子菜单
		MenuButton sub11 = new MenuButton();
		sub11.setKey(WechatConstants.ButtonKey.HYK);
		sub11.setName("会员卡");
		sub11.setType(MenuType.VIEW);
		sub11.setUrl(ControllerUrl.setAuthorizeConnUrl(appid, hostUrl, jump
				+ hostUrl + ControllerUrl.MEMBER_SHIP_CARD, scope, state));
		MenuButton sub12 = new MenuButton();
		sub12.setKey(WechatConstants.ButtonKey.SPSYZD);
		sub12.setName("商品使用指导");
		sub12.setType(MenuType.VIEW);
		sub12.setUrl(ControllerUrl.setAuthorizeConnUrl(appid, hostUrl, jump
				+ hostUrl + ControllerUrl.COMMONDITY_DESCRIPTION, scope, state));
		MenuButton sub13 = new MenuButton();
		sub13.setKey(WechatConstants.ButtonKey.WDWL);
		sub13.setName("我的物流");
		sub13.setType(MenuType.VIEW);
		sub13.setUrl(ControllerUrl.setAuthorizeConnUrl(appid, hostUrl, jump
				+ hostUrl + ControllerUrl.SEARCH_LOGISTICS, scope, state));
		MenuButton sub14 = new MenuButton();
		sub14.setKey(WechatConstants.ButtonKey.WDJF);
		sub14.setName("我的积分");
		sub14.setType(MenuType.CLICK);
		MenuButton sub15 = new MenuButton();
		sub15.setKey(WechatConstants.ButtonKey.WDYHQ);
		sub15.setName("我的优惠券");
		sub15.setType(MenuType.VIEW);
		sub15.setUrl(ControllerUrl.setAuthorizeConnUrl(appid, hostUrl, jump
				+ hostUrl + ControllerUrl.SEARCH_COUPON, scope, state));

		List<MenuButton> list = new ArrayList<MenuButton>();
		list.add(sub11);
		list.add(sub12);
		list.add(sub13);
		list.add(sub14);
		list.add(sub15);
		// 将子菜单放入主菜单里
		main1.setSubButton(list);

		// 准备二级主菜单
		MenuButton main2 = new MenuButton();
		main2.setType(MenuType.CLICK);
		main2.setKey(WechatConstants.ButtonKey.QHXBS);
		main2.setName("巧虎小帮手");
		// 准备子菜单
		MenuButton sub21 = new MenuButton();
		sub21.setKey(WechatConstants.ButtonKey.DGXD);
		sub21.setName("订购&续订");
		sub21.setType(MenuType.VIEW);
		sub21.setUrl(ControllerUrl.setAuthorizeConnUrl(appid, hostUrl, jump
				+ hostUrl + ControllerUrl.COMMONDITY_ORDER, scope, state));
		MenuButton sub22 = new MenuButton();
		sub22.setKey(WechatConstants.ButtonKey.GDZ);
		sub22.setName("改地址");
		sub22.setType(MenuType.VIEW);
		sub22.setUrl(ControllerUrl.setAuthorizeConnUrl(appid, hostUrl, jump
				+ hostUrl + ControllerUrl.CHANGE_ADDRESS, scope, state));
		MenuButton sub23 = new MenuButton();
		sub23.setKey(WechatConstants.ButtonKey.QHSJDT);
		sub23.setName("巧虎世界地图");
		sub23.setType(MenuType.VIEW);
		sub23.setUrl(ControllerUrl.setAuthorizeConnUrl(appid, hostUrl, jump
				+ hostUrl + ControllerUrl.WORLD_MAP, scope, state));
		
		MenuButton sub24 = new MenuButton();
		sub24.setKey(WechatConstants.ButtonKey.CJWT);
		sub24.setName("常见问题");
		sub24.setType(MenuType.CLICK);
		// sub24.setUrl(ControllerUrl.setAuthorizeConnUrl(appid, hostUrl,
		// ControllerUrl.COMMON_PROBLEM, scope, state));
		MenuButton sub25 = new MenuButton();
		sub25.setKey(WechatConstants.ButtonKey.RGKF);
		sub25.setName("小助手在线");
		sub25.setType(MenuType.VIEW);
		sub25.setUrl(ControllerUrl.setAuthorizeConnUrl(appid, hostUrl, jump
				+ hostUrl + ControllerUrl.ONLINE_SERVICE, scope, state));
		
		

		List<MenuButton> list2 = new ArrayList<MenuButton>();
		list2.add(sub21);
		list2.add(sub22);
		list2.add(sub23);
		list2.add(sub24);
		list2.add(sub25);
		// 将子菜单放入主菜单里
		main2.setSubButton(list2);

		// 准备三级主菜单
		MenuButton main3 = new MenuButton();
		main3.setType(MenuType.CLICK);
		main3.setKey(WechatConstants.ButtonKey.HDFL);
		main3.setName("巧虎福利");
		// 准备子菜单
		MenuButton sub31 = new MenuButton();
		sub31.setKey(WechatConstants.ButtonKey.DGXD);
		sub31.setName("巧虎秘籍");
		sub31.setType(MenuType.VIEW);
		sub31.setUrl(UrlConfig.getInstance().getUrl(
				UrlConstant.QIAOHU_CHEAT));
		
		MenuButton sub32 = new MenuButton();
		sub32.setKey(WechatConstants.ButtonKey.QHFL);
		sub32.setName("巧虎福利");
		sub32.setType(MenuType.VIEW);
		sub32.setUrl(ControllerUrl.setAuthorizeConnUrl(appid, hostUrl, jump
				+ hostUrl + ControllerUrl.WELFARE, scope, state));
		MenuButton sub33 = new MenuButton();
		sub33.setKey(WechatConstants.ButtonKey.XSHD);
		sub33.setType(MenuType.VIEW);
		sub33.setName("线上活动");
		sub33.setUrl(ControllerUrl.setAuthorizeConnUrl(appid, hostUrl, jump
				+ hostUrl + ControllerUrl.ONLINE_ACTIVITY, scope, state));
		MenuButton sub34 = new MenuButton();
		sub34.setKey(WechatConstants.ButtonKey.XXHD);
		sub34.setName("线下活动");
		sub34.setType(MenuType.LOCATION_SELECT);

		List<MenuButton> list3 = new ArrayList<MenuButton>();
		list3.add(sub31);
		list3.add(sub32);
		list3.add(sub33);
		list3.add(sub34);
		// 将子菜单放入主菜单里
		main3.setSubButton(list3);

		List<MenuButton> mainList = new ArrayList<MenuButton>();
		mainList.add(main1);
		mainList.add(main2);
		mainList.add(main3);
		// 将主菜单加入请求对象
		request.setButton(mainList);
		logger.info(JSON.toJSONString(request));
		// 创建菜单
		ResultType resultType = menuAPI.createMenu(request);
		if (CheckUtils.isNull(resultType)) {
			logger.error("Create menu error.");
		} else {
			logger.info(resultType.getDescription());
		}
		// 初始化是设置行业信息
		// TemplateMsgAPI templateMsgAPI = new
		// TemplateMsgAPI("CBclLQhkcVa8Lbj5f9qDwGPJYaE5PwEDyYy_NUmbWvwJalYHhemxU-XuHThXR3ObkPkFJciYArFyd3owiQoCnllD0SSw280oF74RvdbaBs9HoKTqEVIFOnCxhoTZAwoPSLGeAFAWPK");
		// Industry industry = new Industry();
		// ConfigHelper config = new ConfigHelper();
		// industry.setIndustryId1(config.industryId1);
		// industry.setIndustryId2(config.industryId2);
		// templateMsgAPI.setIndustry(industry);
	}

}
