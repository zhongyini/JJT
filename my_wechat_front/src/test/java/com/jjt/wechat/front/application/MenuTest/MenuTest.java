package com.jjt.wechat.front.application.MenuTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.jjt.wechat.common.http.HttpResponseNullException;
import com.jjt.wechat.common.wechat.api.MenuApi;
import com.jjt.wechat.common.wechat.api.entity.Menu;
import com.jjt.wechat.common.wechat.api.entity.MenuButton;
import com.jjt.wechat.common.wechat.api.enums.MenuType;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class MenuTest {
	
//	@Test
	public void menuTest() throws ClientProtocolException, IOException, HttpResponseNullException{
		/*Menu menu = new Menu();
		// 准备一级主菜单
		MenuButton menuB1 = new MenuButton();
		menuB1.setName("千人影家");
		
		// 准备子菜单
		MenuButton menuB11 = new MenuButton();
		menuB11.setName("千人影家");
		menuB11.setType(MenuType.VIEW.toString());
		menuB11.setUrl("http://www.baidu.com");
		
		// 准备子菜单
		MenuButton menuB12 = new MenuButton();
		menuB12.setName("影家回顾");
		menuB12.setType(MenuType.VIEW.toString());
		menuB12.setUrl("http://www.baidu.com");
		
		List<MenuButton> list1 = new ArrayList<MenuButton>();
		list1.add(menuB11);
		list1.add(menuB12);
		
		menuB1.setSub_button(list1);
		List<MenuButton> list = new ArrayList<MenuButton>();
		
		list.add(menuB1);
		menu.setButton(list);

		
		MenuApi menuApi = new MenuApi("");
		menuApi.createMenu(menu);*/
		
	}
}
