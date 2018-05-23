package com.jjt.wechat.core.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jjt.wechat.common.constant.Constant;
import com.jjt.wechat.common.utils.CheckUtils;
import com.jjt.wechat.common.utils.DateUtils;
import com.jjt.wechat.common.wechat.api.AccessTokenApi;
import com.jjt.wechat.common.wechat.api.response.AccessTokenResponse;
import com.jjt.wechat.core.dao.entity.WechatToken;
import com.jjt.wechat.core.service.IWechatTokenService;
import com.jjt.wechat.core.util.AppContextUtils;

public class WechatConfig {
	//日志
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private String appId;
	private String appSecret;
	private static WechatConfig wechatConfig;
	
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
	
	/**
	 * 获取Token
	 * 
	 * 注意Token获取可能为null
	 * @return
	 */
	public String getAccessToken(){
		WechatToken wechatToken = null;
		String accessToken = null;
		
		try {
			//获取wechatTokenService对象
			IWechatTokenService wechatTokenService = AppContextUtils.getBean(IWechatTokenService.class);
			//调用方法，从数据库获取最新的Token
			wechatToken = wechatTokenService.findFirstByOrderByCreateDateDesc();
			//若从数据库获取的Token为不为空时，将accessToken赋值
			if(!CheckUtils.isNull(wechatToken)){
				accessToken = wechatToken.getAccessToken();
			}
		} catch (Exception e) {
			logger.error("Get wechat AccessToken Error." + e.getMessage());
		}
		//若access token 没有获取到，则从网络中获取
		if(CheckUtils.isNullOrEmpty(accessToken)){
			accessToken = getAccessTokenOnline();
			//若网络中都没有获取到，则返回null
			if(CheckUtils.isNullOrEmpty(accessToken)){
				return null;
			}
		}
		//返回token
		return accessToken;
	}
	
	/**
	 * 从微信端重新获取Token，并且存储至数据库中
	 * @return
	 */
	public String getAccessTokenOnline(){
		AccessTokenResponse response = null;
		WechatToken wechatToken = null;
		AccessTokenApi accessTokenApi = new AccessTokenApi();

		IWechatTokenService wechatTokenService = AppContextUtils.getBean(IWechatTokenService.class);
		
		//若没有获取到Token则直接返回null
		try {
			response = accessTokenApi.getAccessToken(appId, appSecret);
		} catch (Exception e) {
			logger.error("Get Access Token failed."+e.getMessage());
			return null;
		}
		
		//若返回值为空，则直接返回null
		if(CheckUtils.isNull(response)){
			return null;
		}
		
		//将微信返回的response转为数据库的实体类
		wechatToken = conversion(response);
		
		//将新的Token插入数据库
		try {
			wechatTokenService.add(wechatToken);
		} catch (Exception e) {
			logger.error("Insert Access Token error."+e.getMessage());
			return null;
		}
		//返回Token
		return response.getAccess_token();

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
