package com.xxx.wechat.front.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.xxx.wechat.common.baidu.api.response.AccessTokenResponse;
import com.xxx.wechat.front.dto.RestResult;
import com.xxx.wechat.front.service.IApiService;

/**
 * api接口
 *
 */
@RestController
@RequestMapping("/api")
public class ApiController {

	@Autowired
	private IApiService apiService;
	
	@RequestMapping(value = "/baidu/getAccessToken", method = RequestMethod.GET)
	public RestResult getAccessToken() {
		AccessTokenResponse accessTokenResponse = apiService.getAccessToken("1BlRbyM9ZcQuvy7FbjazsGQw", "tG1f474lKkVoIBESkWGMXZwX22awRfpA");
		return new RestResult(accessTokenResponse);
		
	}
}
