package com.xxx.wechat.admin.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xxx.wechat.admin.dto.RestResult;


@RestController
@RequestMapping("test")
public class TestController {

	public RestResult index() {
		return new RestResult("Hello World!");
	}
}
