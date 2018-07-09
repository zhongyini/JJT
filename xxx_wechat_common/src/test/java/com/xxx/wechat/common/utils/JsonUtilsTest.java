package com.xxx.wechat.common.utils;

//import org.junit.Test;

//import com.xxx.wechat.common.wechat.api.response.AccessTokenResponse;

public class JsonUtilsTest {
	
	/**
	 * 测试：
	 * 		将字符串转为具体的实体类
	 */
	/*public void parseObjectTest(){
		String accessToken = "{\"access_token\":\"ACCESS_TOKEN\",\"expires_in\":7200}";
		AccessTokenResponse access = JsonUtils.parseObject(accessToken, AccessTokenResponse.class);
		System.out.println(access.getAccess_token());
		System.out.println(access.getExpires_in());
		
	}*/
//	@Test
	/**
	 * 测试：
	 * 		将object转为String类型
	 */
	/*public void toJsonStringTest(){
		AccessTokenResponse access = new AccessTokenResponse();
		access.setAccess_token("getAccess_token");
		access.setExpires_in(3600);
		System.out.println(JsonUtils.toJsonString(access));
	}*/
}
