package com.xxx.wechat.front.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.Page;
import com.xxx.wechat.common.constant.Constant;
import com.xxx.wechat.common.utils.CheckUtils;
import com.xxx.wechat.core.config.SystemConfig;
import com.xxx.wechat.core.dao.entity.WechatUserAccount;
import com.xxx.wechat.core.dao.entity.extend.WechatRecommendExt;
import com.xxx.wechat.front.authentication.LoginRequired;
import com.xxx.wechat.front.dto.BasePage;
import com.xxx.wechat.front.dto.RestResult;
import com.xxx.wechat.front.dto.ShareResultDto;
import com.xxx.wechat.front.service.IWechatCardCodeService;
import com.xxx.wechat.front.service.IWechatQrcodeService;
import com.xxx.wechat.front.service.IWechatRecommendService;
import com.xxx.wechat.front.service.IWechatUserAccountService;
import com.xxx.wechat.helper.ConfigHelper;

@Controller
@RequestMapping("/share")
public class ShareController extends BaseController {

	@Autowired
	IWechatQrcodeService wechatQrcodeService;
	
	@Autowired
	IWechatRecommendService wechatRecommendService;
	
	@Autowired
	IWechatCardCodeService wechatCardCodeService;
	
	@Autowired
	IWechatUserAccountService wechatUserAccountService;
	
	@Autowired
	ConfigHelper configHelper;
	
	/**
	 * 卡券画面
	 * @return
	 */
	@LoginRequired(isLand = true)
	@RequestMapping("/view")
	public ModelAndView view() {
		ModelAndView mav = new ModelAndView();
		// 从cookie中获取用户openid
		String openid = getOpenid();
//		WechatQrcode wechatQrcode = null;
		if (CheckUtils.isNullOrEmpty(openid)) {
			// 没有获取到openid，跳转到错误画面
			mav.setViewName(Constant.ControllerUrl.COMMON_ERROR);
			return mav;
		}
		// 可领取金额数
		String amountCash = Constant.EMPTY;
		WechatUserAccount wechatUserAccount = wechatUserAccountService.get(openid);
		if (!CheckUtils.isNull(wechatUserAccount)) {
			amountCash = String.valueOf(wechatUserAccount.getBalance()/100);
		}
		// 推荐列表
		Page<WechatRecommendExt> recommendList = selectRecommendListByRecOpenid(openid, Constant.Num.INT_ONE, Integer.valueOf(SystemConfig.getInstance().getProperty(Constant.Configuration.LIST_PAGE_SIZE)));
		if (CheckUtils.isNull(recommendList)) {
			recommendList = new Page<WechatRecommendExt>();
		}
		ShareResultDto shareResultDto = new ShareResultDto();
		shareResultDto.setOpenid(openid);
		shareResultDto.setTotal(recommendList.getTotal());
		shareResultDto.setRecommendList(recommendList);
		shareResultDto.setAmountCash(String.valueOf(amountCash));
		shareResultDto.setRecommendNum(String.valueOf(recommendList.size()));
		mav.addObject("result", shareResultDto);
		mav.setViewName(Constant.ControllerUrl.SHARE_VIEW);
		return mav;
	}
	
	@RequestMapping("/page")
	public @ResponseBody RestResult page(String openid, int pageNum, int pageSize) {
		Page<WechatRecommendExt> recommendList = selectRecommendListByRecOpenid(openid, pageNum, pageSize);
		return new RestResult(new BasePage(recommendList.getTotal(), recommendList.getResult()));
	}
	
	@RequestMapping("/consume")
	public @ResponseBody ModelAndView consume(@RequestParam("openid") String openid) {
		ModelAndView mav = new ModelAndView();
		logger.info(openid);
		/**
		 * 红包提现
		 */
		mav.setViewName(Constant.ControllerUrl.SHARE_VIEW);
		return mav;
	}
	
	/**
	 * 查询推荐关系
	 */
	private Page<WechatRecommendExt> selectRecommendListByRecOpenid(String recOpenid, int pageNum, int pageSize) {
		try {
			return wechatRecommendService.selectRecommendListByRecOpenid(recOpenid, pageNum, pageSize);
		} catch (Exception e) {
			logger.error("用户openid："+recOpenid+"获取推荐的用户信息失败"+e.getMessage());
		}
		return null;
	}
	
}
