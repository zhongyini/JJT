package com.jjt.wechat.front.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jjt.wechat.auth.LoginRequired;

@Controller
@RequestMapping("test")  
public class TestController {

	@LoginRequired
	@RequestMapping("/index")  
    String dawin() {  
        return "/hello/hello";
    }
	
}
