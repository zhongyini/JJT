package com.xxx.wechat.front.controller;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xxx.wechat.front.authentication.LoginRequired;

/**
 * 按鈕控制器
 *
 */
@RestController
@RequestMapping("/menu")
public class MenuViewController extends BaseController {
	
    /**
     * 体检券 以微信网页开发方式，获取用户openID
     * 跳转固定画面
     * @param code
     * @param state
     * @return
     */
	@LoginRequired(isLand = true)
	@RequestMapping(value = "/voucher")
	public ModelAndView membershipcard(@ModelAttribute("openId") String openId,
			RedirectAttributes redirectAttributes) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("user/button");
		logger.info(openId);
		return mav; 
	}
	
}
