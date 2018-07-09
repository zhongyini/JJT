package com.jjt.wechat.admin.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jjt.wechat.admin.RestResult;

@RestController
@RequestMapping("test")
public class TestController {

	public RestResult index() {
		return new RestResult("Hello World!");
	}
}
