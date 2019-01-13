package com.xxx.wechat.front.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xxx.wechat.common.utils.CheckUtils;
import com.xxx.wechat.core.exception.AppException;
import com.xxx.wechat.front.dto.RestResult;
import com.xxx.wechat.front.service.ILotteryDltHistoryService;

/**
 * 
 * 大乐透
 * @author Administrator
 *
 */
@RestController
@RequestMapping("lotteryDlt")
public class LotteryDltController {
	
	@Autowired
	ILotteryDltHistoryService lotteryDltHistoryService;
	/**
	 * 获取下一次出现的结果
	 * @return
	 */
	@GetMapping("guess")
	public RestResult guess() {
		try {
			int result = lotteryDltHistoryService.generateLotteryDltGuess();
		return new RestResult(result);
		} catch(AppException e) {
			return new RestResult(!CheckUtils.isNullOrEmpty(e.getErrorCode()) ? e.getErrorCode() : "预测失败");
		}
		
	}
	
}
