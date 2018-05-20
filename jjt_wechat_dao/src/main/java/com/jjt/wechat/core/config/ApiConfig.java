package com.jjt.wechat.core.config;

import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.alibaba.fastjson.JSONObject;
import com.jjt.common.api.response.GetJsApiTicketResponse;
import com.jjt.common.content.url.HttpRequestUrl;
import com.jjt.common.exception.WeixinException;
import com.jjt.common.httpmanagement.SendHttpRequest;
import com.jjt.common.utils.CheckUtils;
import com.jjt.common.utils.DateUtils;
import com.jjt.common.utils.JSONUtil;
import com.jjt.common.utils.NetWorkCenter;
import com.jjt.common.utils.StrUtil;
import com.jjt.wechat.core.constants.ConfigConstants;
import com.jjt.wechat.core.util.AppContextUtils;
import com.jjt.wechat.core.util.SysPropUtil;


/**
 * API配置类，项目中请保证其为单例 实现观察者模式，用于监控token变化
 *
 */
public final class ApiConfig {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private final String appid;
	private final String secret;
	private String accessToken;
	private int retryiIndex = 0;
	private static final int RETRY_MAX = 3;

	private static ApiConfig apiConfig;
	
	private String jsApiTicket;
	/**
	 * 单例模式
	 * 
	 * @return
	 */
	public static ApiConfig getInstance() {
		if (CheckUtils.isNull(apiConfig)) {
			apiConfig = new ApiConfig();
		}
		return apiConfig;
	}

	/**
	 * 构造方法一，实现同时获取access_token。不启用jsApi
	 *
	 * @param appid
	 *            公众号appid
	 * @param secret
	 *            公众号secret
	 */
	private ApiConfig() {
		this.appid = SysPropUtil.getString(ConfigConstants.APP_ID);
		this.secret = SysPropUtil.getString(ConfigConstants.APP_SECRET);
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getAccessToken() {
		initAccessToken();
		return accessToken;
	}

	public String getAppid() {
		return appid;
	}

	public String getSecret() {
		return secret;
	}

	/**
	 * 初始化accesstoken
	 */
	private void initAccessToken() {
		try {
			// 从数据库获取access token
//			WxToken token = AppContextUtils.getBean(AccessTokenService.class).getAccessToken();
			// 若token获取失败，则从微信重新获取token
//			if (CheckUtils.isNull(token)) {
//				accessToken = getAccessTokenRetry();
//			}else{
//				accessToken = token.getAccess_token();
//			}
		} catch (Exception e) {
			logger.error("GetAccess Token is error." + e.getMessage());
		}

	}
	
	private String getAccessTokenRetry(){
		//获取一次token
		accessToken = getAccessTokenFromWechat();
		//重试次数增加一次
		retryiIndex++;
		//当重试次数达到设定值时，直接返回token
		if(retryiIndex == RETRY_MAX){
			return accessToken;
		}
		//若token不为空，则直接返回
		if(!CheckUtils.isNullOrEmpty(accessToken)){
			return accessToken;
		}
		accessToken = getAccessTokenRetry();
		return accessToken;
	}

	/**
	 * 从微信端获取token
	 */
	private String getAccessTokenFromWechat() {
		DataSourceTransactionManager txManager = null;
		try {
			//获取事务manager的bean
			txManager = AppContextUtils.getBean(DataSourceTransactionManager.class);
			if(CheckUtils.isNull(txManager)){
				logger.error("txManager is null");
				return null;
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		// 开启新事务
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		TransactionStatus status = txManager.getTransaction(def);
		
		//从调用微信的接口，获取bean
//		WxToken newToken = null;
		try {
			String requestUrl = String.format(HttpRequestUrl.GET_ACCESS_TOKEN_GET, appid, secret);
			String result = SendHttpRequest.httpGetRequest(requestUrl);
//			newToken = JSONObject.parseObject(result).toJavaObject(WxToken.class);
//			if (CheckUtils.isNull(newToken) || CheckUtils.isNullOrEmpty(newToken.getAccess_token())) {
//				newToken.setAccess_token("ERROR");
//				newToken.setExpires_in(0);
//				logger.error("Access_token get error. Error Code:"
//						+ newToken.getErrcode() + " Error msg:"
//						+ newToken.getErrcode() + ".");
//			}
//			newToken.setCreateDate(DateUtils.getNowTimestamp());
//			String jsapiTicketRequestUrl = String.format(HttpRequestUrl.GET_JSAPI_TICKET_GET, newToken.getAccess_token());
//			String jsapiTicketResult = SendHttpRequest.httpGetRequest(jsapiTicketRequestUrl);
//			logger.info("GET JsapiTicket over: "+jsapiTicketResult);
//			jsApiTicket = JSONObject.parseObject(jsapiTicketResult).getString("ticket");
//			newToken.setJsapi_ticket(jsApiTicket);
//			AppContextUtils.getBean(AccessTokenService.class).addWxToken(newToken);
//			txManager.commit(status);
//			return newToken.getAccess_token();
			return null;
		} catch (Exception e) {
			logger.error(e.getMessage());
			logger.error("AccessToken Batch get token error.");
			// 回滚
			txManager.rollback(status);
			return null;
		} 

	}
	
	public String getJsApiTicket() {
		initJSToken();
		return jsApiTicket;
    }

	/**
	 * 初始化微信JS-SDK，获取JS-SDK token
	 */
	private void initJSToken() {
		try {
			// 从数据库获取access token
//			WxToken token = AppContextUtils.getBean(AccessTokenService.class).getAccessToken();
			// 若token获取失败，则从微信重新获取token
//			if (CheckUtils.isNull(token) || CheckUtils.isNullOrEmpty(token.getJsapi_ticket())) {
				accessToken = getAccessTokenRetry();
				String url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=" + accessToken + "&type=jsapi";
				NetWorkCenter.get(url, null, new NetWorkCenter.ResponseCallback() {
			        @Override
			        public void onResponse(int resultCode, String resultJson) {
			            if (HttpStatus.SC_OK == resultCode) {
			                GetJsApiTicketResponse response = JSONUtil.toBean(resultJson, GetJsApiTicketResponse.class);
			                logger.debug("获取jsapi_ticket:{}", response.getTicket());
			                if (StrUtil.isBlank(response.getTicket())) {
			                    //刷新时间回滚
			                    throw new WeixinException("微信公众号jsToken获取出错，错误信息:" + response.getErrcode() + "," + response.getErrmsg());
			                }
			                jsApiTicket = response.getTicket();
			            }
			        }
			    });
//			}else{
//				jsApiTicket = token.getJsapi_ticket();
//			}
		} catch (Exception e) {
			logger.error("Get JsApi Ticket is error." + e.getMessage());
		}
	}

}
