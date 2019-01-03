package com.xxx.wechat.front.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.xxx.wechat.core.dao.entity.NameConfig;
import com.xxx.wechat.front.dto.NameConfigDto;
import com.xxx.wechat.front.dto.RestResult;
import com.xxx.wechat.front.service.INameConfigService;

/**
 * 名称控制器
 *
 */
@RestController
@RequestMapping("/name")
public class NameController {

	@Autowired
	private INameConfigService nameConfigService;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public RestResult list() {
		List<NameConfig> nameList = nameConfigService.list();
		return new RestResult(nameList);
		
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public RestResult add(@RequestBody(required = false) NameConfigDto nameConfigDto) {
		NameConfig nameConfig = new NameConfig();
		nameConfig.setWord(nameConfigDto.getWord());
		NameConfig result = nameConfigService.insert(nameConfig);
		return new RestResult(result);
		
	}
	
}
