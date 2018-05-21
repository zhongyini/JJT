package com.jjt.wechat.common.wechat.api;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

import com.jjt.wechat.common.constant.Constant;
import com.jjt.wechat.common.http.HttpRequest;
import com.jjt.wechat.common.http.HttpResponseNullException;
import com.jjt.wechat.common.utils.CheckUtils;
import com.jjt.wechat.common.utils.JsonUtils;

/**
 * 网页授权API
 *
 */
public class OauthApi extends BaseApi {


    private final String  appid;
    private final String  secret;

    public OauthApi(String accessToken,String appid,String secret){
    	super(accessToken);
    	this.appid = appid;
    	this.secret = secret;
    }


    /**
     * 用户同意授权后在回调url中会得到code，调用此方法用code换token以及openid，所以如果仅仅是授权openid，到这步就结束了
     *
     * @param code 授权后得到的code
     * @return token对象
     * @throws HttpResponseNullException 
     * @throws IOException 
     * @throws ClientProtocolException 
     */
    public String getOpenId(String code) throws ClientProtocolException, IOException, HttpResponseNullException {
    	CheckUtils.requireNonNull(code, "code is null");
    	String tokenurl =  String.format(Constant.WechatUrl.ACCESS_TOKEN_OAUTH2_GET,this.appid, this.secret,code);
    	String response = HttpRequest.httpGetRequest(tokenurl);
    	//String openId = JsonUtils.getStringFromJSONObject(response, "openid").toString();
    	return response;
    }
    
    

   
}
