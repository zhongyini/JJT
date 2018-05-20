package com.jjt.common.content.url;

/**
 * 巧虎微信Controller 地址
 * @author YUCJ
 *
 */
public class ControllerUrl {
	public static final String USER_LOGIN = "/user/login";
	
	public static final String INTEGRAL_MALL="/menu/integral/mall";
	public static final String SEARCH_COUPON="/menu/search/coupon";
	public static final String COMMONDITY_DESCRIPTION="/menu/commondity/description";
	public static final String SEARCH_LOGISTICS="/menu/search/logistics";
	public static final String MEMBER_SHIP_CARD="/menu/membershipcard";
	public static final String COMMONDITY_ORDER="/menu/commondity/order";
	public static final String WORLD_MAP="/menu/worldMap";
	public static final String CHANGE_ADDRESS="/menu/change/address";
	public static final String COMMON_PROBLEM="/menu/commonProblem";
	public static final String WELFARE="/menu/welfare";
	public static final String ONLINE_ACTIVITY="/menu/onlineActivity";
	public static final String SEARCH_INTEGRAL="/menu/search/integral";
	public static final String ONLINE_SERVICE="/menu/onlineService";
//	public static final String 
//	public static final String 
//	public static final String 
	
	
	//拼接页面授权URL
		public static String setAuthorizeConnUrl(String appid, String hostUrl,String conUrl, String scope, String state) {
			hostUrl = hostUrl.trim();
			if(hostUrl.endsWith("/")){
				hostUrl = hostUrl.substring(0, hostUrl.length()-1);
			}
			if(!conUrl.startsWith("/")){
				conUrl = "/"+conUrl;
			}
			String redirect_uri = hostUrl+conUrl;
			// 页面授权URL设定
			if (!(scope == null || scope.trim().equals(HttpRequestUrl.AUTHORIZE_SCOPE_USERINFO) || scope.trim().equals(HttpRequestUrl.AUTHORIZE_SCOPE_BASE))) {
				scope = HttpRequestUrl.AUTHORIZE_SCOPE_USERINFO;
			}
			return String.format(HttpRequestUrl.AUTHORIZE_OAUTH2_CONNECT, appid.trim(), redirect_uri.trim(), scope.trim(), state.trim());
		}
}
