package com.xxx.wechat.front.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.xxx.wechat.front.dto.RestResult;

@RestController
@RequestMapping("/answer")
public class AnswerController {

	@RequestMapping(method = RequestMethod.GET)
	public RestResult answer(String question) {
		return new RestResult("success", question);
	}
}
