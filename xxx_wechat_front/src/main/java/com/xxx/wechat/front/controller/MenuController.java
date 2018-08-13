package com.xxx.wechat.front.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 按鈕控制器
 *
 */
@Controller
@RequestMapping("/menu")
public class MenuController extends BaseController {

	@RequestMapping(value = "/one/one")
	public @ResponseBody String oneOne() {
		return "redirect:http://www.ykstudy.cn"; 
	}
	@RequestMapping(value = "/one/two")
	public String oneTwo() {
		return "redirect:http://www.ykstudy.cn"; 
	}
	@RequestMapping(value = "/one/three")
	public String oneThree() {
		return "redirect:http://www.ykstudy.cn"; 
	}
	@RequestMapping(value = "/one/four")
	public String oneFour() {
		return "redirect:http://www.ykstudy.cn"; 
	}
	@RequestMapping(value = "/one/five")
	public String oneFive() {
		return "redirect:http://www.ykstudy.cn"; 
	}
	@RequestMapping(value = "/two/one")
	public String twoOne() {
		String url = "https://apis.map.qq.com/tools/poimarker?type=0&marker=coord:37.978845,114.541397;title:NO.1;addr:河北省石家庄市裕华区鑫源东路|coord:37.991293,114.640961;title:NO.2;addr:河北省石家庄市裕华区冲江河道|coord:38.059175,114.479942;title:NO.3;addr:河北省石家庄市新华区和平西路223号&key=FMEBZ-W5B66-YRVSG-EKKE2-AEVRJ-6RBGO&referer=cdztest";
		System.out.println(url);
		return "redirect:"+url;
	}
	@RequestMapping(value = "/two/two")
	public String twoTwo() {
		return "redirect:http://www.taobao.com"; 
	}
	@RequestMapping(value = "/two/three")
	public String twoThree() {
		return "redirect:http://www.aqiyi.com"; 
	}
	@RequestMapping(value = "/two/four")
	public String twoFour() {
		return "redirect:http://music.163.com"; 
	}
	@RequestMapping(value = "/two/five")
	public String twoFive() {
		return "redirect:http://r.9669.cn/"; 
	}
	@RequestMapping(value = "/three/one")
	public String threeOne() {
		return "redirect:http://www.ykstudy.cn"; 
	}
	@RequestMapping(value = "/three/two")
	public String threeTwo() {
		return "redirect:http://www.ykstudy.cn"; 
	}
	@RequestMapping(value = "/three/three")
	public String threeThree() {
		return "redirect:http://www.ykstudy.cn"; 
	}
	@RequestMapping(value = "/three/four")
	public String threeFour() {
		return "redirect:http://www.ykstudy.cn"; 
	}
	@RequestMapping(value = "/three/five")
	public String threeFive() {
		return "redirect:http://www.ykstudy.cn"; 
	}
	
}
