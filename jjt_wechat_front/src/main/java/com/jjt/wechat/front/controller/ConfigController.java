package com.jjt.wechat.front.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jjt.wechat.core.util.SignUtil;
import com.jjt.wechat.core.util.WeiXinTokenUtil;

@Controller
@RequestMapping("/config")
public class ConfigController extends BaseController {

	@RequestMapping("/jsToken")
	public @ResponseBody Map<String, String> jsToken(@RequestParam(value = "url", required = true) String url) {
		try {
			String appId = "wxc085afb8ad75c822";//SysPropUtil.getString(ConfigConstants.APP_ID);
			String access_token = "9_IthANdX2sLHfnd-N8-kdL4nWnlIyZD2oB6VDdn-XmKle7tUAoOsfyrR22J4b_R7TxShHXKHLSOU3uz2C_9WlKuhXvXnGOYpXQa8BvnwDJ5aCwCdLEs0icW-io9AsckDtrBDagaBspdwRusQHYVCcAEAPMT";
			String jsApiTicket_url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token="+ access_token +"&type=jsapi";
			String jsApiTicket = "kgt8ON7yVITDhtdwci0qefL8blC0fNUBF9inj4zc0B3S81PVbQzFZ0ZiLBXMflVY4BjtlZOSA-CnV8qmxWHEAA";//ApiConfig.getInstance().getJsApiTicket();
			Map<String, String> resultMap = SignUtil.sign(jsApiTicket, url);
			resultMap.put("appid", appId);
			return resultMap;
		} catch (Exception e) {
			logger.error("配置 jsToken 失败：" + e.getMessage());
			return null;
		}
		
	}
	
	 @RequestMapping("/weixin_token")
	    public void getToken(Model model, HttpServletRequest request,HttpServletResponse response) throws IOException {
	        boolean isGet = request.getMethod().toLowerCase().equals("get");
	        PrintWriter print;
	        if (isGet) {
	            // 微信加密签名
	            String signature = request.getParameter("signature");
	            // 时间戳
	            //1512622929
	            String timestamp = request.getParameter("timestamp");
	            // 随机数
	            String nonce = request.getParameter("nonce");
	            // 随机字符串
	            String echostr = request.getParameter("echostr");
	            // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
	            if (signature != null && WeiXinTokenUtil.checkSignature(signature, timestamp, nonce)) {
	                try {
	                    print = response.getWriter();
	                    print.write(echostr);
	                    print.flush();
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            }
	        }
	    }
}
