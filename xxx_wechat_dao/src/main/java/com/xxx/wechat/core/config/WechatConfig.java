package com.xxx.wechat.core.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xxx.wechat.common.constant.Constant;
import com.xxx.wechat.common.utils.CheckUtils;
import com.xxx.wechat.common.utils.DateUtils;
import com.xxx.wechat.common.wechat.api.AccessTokenApi;
import com.xxx.wechat.common.wechat.api.ApiTicketApi;
import com.xxx.wechat.common.wechat.api.JsApiTicketApi;
import com.xxx.wechat.common.wechat.api.response.AccessTokenResponse;
import com.xxx.wechat.common.wechat.api.response.GetApiTicketResponse;
import com.xxx.wechat.common.wechat.api.response.GetJsApiTicketResponse;
import com.xxx.wechat.core.dao.entity.WechatToken;
import com.xxx.wechat.core.service.IWechatTokenService;
import com.xxx.wechat.core.util.AppContextUtils;

public class WechatConfig {
	//日志
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private String appId;
	private String appSecret;
	private static WechatConfig wechatConfig;
	
	private String accessToken;
	private String jsapiTicket;
	private String apiTicket;
	
	/**
	 * 获取微信配置的对象
	 * @return
	 */
	public static WechatConfig getInstance(){
		if(CheckUtils.isNull(wechatConfig)){
			wechatConfig = new WechatConfig();
		}
		return wechatConfig;
	}

	private WechatConfig() {
		appId = SystemConfig.getInstance().getProperty(Constant.Configuration.APPID);
		appSecret = SystemConfig.getInstance().getProperty(Constant.Configuration.APPSECRET);
	}
	
	public String getAccessToken() {
		initWechatToken();
		return accessToken;
	}
	
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getApiTicket() {
		initWechatToken();
		return apiTicket;
	}

	public void setApiTicket(String apiTicket) {
		this.apiTicket = apiTicket;
	}

	public String getJsapiTicket() {
		initWechatToken();
		return jsapiTicket;
    }
	
	public void setJsapiTicket(String jsapiTicket) {
		this.jsapiTicket = jsapiTicket;
	}

	public String getAppid() {
		return appId;
	}

	public String getSecret() {
		return appSecret;
	}
	
	private WechatToken getTokenByDB() {
		WechatToken wechatToken = null;
		for (int i = 0; i < 3; i++) {
			try {
				//调用方法，从数据库获取最新的Token
				wechatToken = AppContextUtils.getBean(IWechatTokenService.class).findFirstByOrderByCreateDateDesc();
				//若从数据库获取的Token为不为空时，将accessToken赋值
				if(!CheckUtils.isNull(wechatToken) && DateUtils.checkTokenDate(DateUtils.getNowTimestamp(), wechatToken.getCreateDate())){
					return wechatToken;
				}
				logger.info("token为空或超时");
				wechatToken = getWechatTokenOnline();
				if (!CheckUtils.isNull(wechatToken)) {
					return wechatToken;
				}
			} catch (Exception e) {
				logger.error("Get WechatToken Error." + e.getMessage());
			}
		}
		return null;
		
	}
	
	/**
	 * 获取Token
	 * 
	 * 注意Token获取可能为null
	 * @return
	 */
	private void initWechatToken(){
		//调用方法，从数据库获取最新的Token
		WechatToken wechatToken = getTokenByDB();
		//若从数据库获取的Token为不为空时，将accessToken赋值
		if (!CheckUtils.isNull(wechatToken)) {
			accessToken = wechatToken.getAccessToken();
			jsapiTicket = wechatToken.getJsapiTicket();
			apiTicket = wechatToken.getApiTicket();
		} else {
			logger.error("获取WechatToken为空，请查看表中是否有最新token");
		}
	}
	
	/**
	 * 从微信端重新获取Token，并且存储至数据库中
	 * @return
	 * @throws Exception 
	 */
	public WechatToken getWechatTokenOnline() throws Exception{
		
		AccessTokenResponse response = null;
		WechatToken wechatToken = new WechatToken();
		AccessTokenApi accessTokenApi = new AccessTokenApi();
		IWechatTokenService wechatTokenService = AppContextUtils.getBean(IWechatTokenService.class);
		
		//若没有获取到Token则直接返回null
		try {
			response = accessTokenApi.getAccessToken(appId, appSecret);
		} catch (Exception e) {
			logger.error("Get Access Token failed."+e.getMessage());
			throw new Exception(e);
		}
		
		//若返回值为空，则直接返回null
		if(CheckUtils.isNull(response) || CheckUtils.isNullOrEmpty(response.getAccess_token())){
			logger.error("获取AccessToken响应为空,Errcode="+response.getErrcode()+",Errmsg="+response.getErrmsg());
			throw new Exception("获取AccessToken响应为空");
		}
		
		// 将微信返回的response转为数据库的实体类
		wechatToken = conversion(response);
		// 添加jsapiTicket
		GetJsApiTicketResponse jsapiTicketResponse = new GetJsApiTicketResponse();
		JsApiTicketApi jsapiTicketAPi = new JsApiTicketApi(wechatToken.getAccessToken());
		try {
			jsapiTicketResponse = jsapiTicketAPi.getJsApiTicket();
		} catch (Exception e) {
			logger.error("Get jsapi_ticket failed."+e.getMessage());
			throw new Exception(e);
		}
		//若返回值为空，则直接返回null
		if(CheckUtils.isNull(jsapiTicketResponse) || CheckUtils.isNullOrEmpty(jsapiTicketResponse.getTicket())){
			logger.error("获取jsapi_ticket响应为空,Errcode="+jsapiTicketResponse.getErrcode()+",Errmsg="+jsapiTicketResponse.getErrmsg());
			throw new Exception("获取jsapi_ticket响应为空");
		}
		wechatToken.setJsapiTicket(jsapiTicketResponse.getTicket());
		// 添加apiTicket
		GetApiTicketResponse apiTicketResponse = new GetApiTicketResponse();
		ApiTicketApi apiTicketAPi = new ApiTicketApi();
		try {
			apiTicketResponse = apiTicketAPi.getApiTicket(wechatToken.getAccessToken());
		} catch (Exception e) {
			logger.error("Get api_ticket failed."+e.getMessage());
			throw new Exception(e);
		}
		//若返回值为空，则直接返回null
		if(CheckUtils.isNull(apiTicketResponse) || CheckUtils.isNullOrEmpty(apiTicketResponse.getTicket())){
			logger.error("获取api_ticket响应为空,Errcode="+apiTicketResponse.getErrcode()+",Errmsg="+apiTicketResponse.getErrmsg());
			throw new Exception("获取api_ticket响应为空");
		}
		wechatToken.setApiTicket(apiTicketResponse.getTicket());
		
		//将新的Token插入数据库
		try {
			// TODO 放开后删除所有token
//			wechatTokenService.deleteAll();
			// 添加token
			wechatTokenService.add(wechatToken);
		} catch (Exception e) {
			logger.error("Insert Access Token error."+e.getMessage());
			throw new Exception(e);
		}
		//返回Token
		return wechatToken;

	}
	//将微信返回的response转为数据库的实体类
	private WechatToken conversion(AccessTokenResponse response){
		WechatToken token = new WechatToken();
		token.setAccessToken(response.getAccess_token());
		token.setExpiresIn(response.getExpires_in());
		token.setErrcode(response.getErrcode());
		token.setErrmsg(response.getErrmsg());
		token.setCreateDate(DateUtils.getNowTimestamp());
		return token;
	}
	
}
