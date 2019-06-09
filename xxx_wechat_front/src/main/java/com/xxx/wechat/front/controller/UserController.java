package com.xxx.wechat.front.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.xxx.wechat.common.constant.ConfigurationEnum;
import com.xxx.wechat.common.constant.Constant;
import com.xxx.wechat.common.utils.CheckUtils;
import com.xxx.wechat.common.utils.DateUtils;
import com.xxx.wechat.core.config.ConfigurationConfig;
import com.xxx.wechat.core.dao.entity.WechatCard;
import com.xxx.wechat.core.dao.entity.WechatCardCode;
import com.xxx.wechat.core.dao.entity.WechatRecommend;
import com.xxx.wechat.core.dao.entity.WechatUser;
import com.xxx.wechat.front.authentication.LoginRequired;
import com.xxx.wechat.front.dto.LoginDto;
import com.xxx.wechat.front.dto.RestResult;
import com.xxx.wechat.front.dto.VerifyCodeDto;
import com.xxx.wechat.front.service.IWechatCardCodeService;
import com.xxx.wechat.front.service.IWechatCardService;
import com.xxx.wechat.front.service.IWechatRecommendService;
import com.xxx.wechat.front.service.IWechatUserService;
import com.xxx.wechat.helper.SmsHelper;

/**
 * 按鈕控制器
 *
 */
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {
	
	@Autowired
	IWechatRecommendService wechatRecommendService;
	
	@Autowired
	IWechatUserService wechatUserService;
	
	@Autowired
	IWechatCardService wechatCardService;
	
	@Autowired
	IWechatCardCodeService wechatCardCodeService;
	
	/**
	 * 用户登录方法
	 * 
	 * @param recommendOpenid 推荐人的openid
	 * @return
	 */
	@LoginRequired(isLand = true)
	@RequestMapping(value = "/login")
	 public ModelAndView login(@ModelAttribute(binding=false, value="recommend_openid") String recommendOpenid) {
		ModelAndView mav = new ModelAndView();
		// 从cookie中获取用户openid
		String openid = getOpenid();
		if (CheckUtils.isNullOrEmpty(openid)) {
			// openid为空，跳转到错误画面
			mav.setViewName(Constant.ControllerUrl.COMMON_ERROR);
			return mav;
		}
		WechatRecommend wechatRecommend = new WechatRecommend();
		if (CheckUtils.isNullOrEmpty(recommendOpenid)) {
			// 没有推荐人，进入分享画面
			mav.setViewName("redirect:"+Constant.ControllerUrl.SHARE_VIEW);
			return mav;
		} /*else if(recommendOpenid.equals(openid)) {
			// 推荐者openid和被推荐者openid相同，进入分享画面
			logger.info("openid:" + openid +"自己推荐自己，返回");
			mav.setViewName("redirect:"+Constant.ControllerUrl.SHARE_VIEW);
			return mav;
		}*/ else {
			// 存在推荐者信息，添加推荐关系
			logger.info("推荐人openid:"+recommendOpenid+"被推荐人openid："+openid);
			// 被推荐者的openid
			wechatRecommend.setRecEdOpenid(openid);
			// 推荐者的openid
			wechatRecommend.setRecOpenid(recommendOpenid);
			// 添加推荐者信息
			createWechatRecommend(wechatRecommend);
		}
		// 插入推荐关系，查询当前
		LoginDto loginDto = new LoginDto();
		try {
			WechatCardCode wechatCardCode = new WechatCardCode();
			wechatCardCode.setRecEdOpenid(openid);
			// 根据被推荐者openid查询已领过多少券
			List<WechatCardCode> wechatCardCodeList = selectWechatCardCode(wechatCardCode);
			// 查询领券上限
			String limitStr = ConfigurationConfig.getInstance().getProperty(ConfigurationEnum.GET_NUM_LIMIT);
			// 转型
			int limit = CheckUtils.isNullOrEmpty(limitStr) ? 0 : Integer.valueOf(limitStr);
			// 没领过或领取次数未到达上限，可再次领取
			if (!CheckUtils.isNull(wechatCardCodeList) && wechatCardCodeList.size() >= limit) {
				//用户领取卡券超过上限，进入卡券画面
				mav.setViewName("redirect:"+Constant.ControllerUrl.CARD_VIEW);
				return mav;
			} else {
				// 传到卡券画面的参数
				WechatCard wechatCard = new WechatCard();
				// 根据卡券名去查找卡券
				wechatCard.setTitle(Constant.Str.TITLE);
				// 获取卡券信息
				List<WechatCard> wechatCardList = getWechatCardList(wechatCard);
				if (CheckUtils.isNull(wechatCardList) || wechatCardList.size() == Constant.Num.INT_ZERO) {
					// 没有卡券信息
					logger.error("没有成功获取卡券信息，openid："+openid+" 不可领取卡券");
					mav.setViewName(Constant.ControllerUrl.COMMON_ERROR);
					return mav;
				} else {
					// 根据用户openid获取用户信息
					WechatUser wechatUser = wechatUserService.findByOpenid(openid);
					if (!CheckUtils.isNull(wechatUser)) {
						// 手机号
						loginDto.setPhone(wechatUser.getPhone());
					}
					if (!CheckUtils.isNullOrEmpty(wechatUser.getVerifyCode())) {
						loginDto.setVerifyCode(wechatUser.getVerifyCode());
					}
					// 第一个卡券信息
					wechatCard = wechatCardList.get(wechatCardList.size()-Constant.Num.INT_ONE);
					// 卡券id
					loginDto.setCardId(wechatCard.getCardId());
					loginDto.setCodeNum(String.valueOf(wechatCardCodeService.selectCountNum()));
					// 用户openid
					loginDto.setOpenid(openid);
					// 推荐者openid
					loginDto.setRecOpenid(recommendOpenid);
					mav.setViewName(Constant.ControllerUrl.USER_LOGIN);
					mav.addObject("result", loginDto);
					return mav;
				}
			}
		} catch (Exception e) {
			logger.error("openid："+openid+"进入注册画面失败："+e.getMessage());
			mav.setViewName(Constant.ControllerUrl.COMMON_ERROR);
			return mav;
		}
		
	}
	
	/**
	 * 
	 * 
	 */
	@RequestMapping(value = "/verifyCode")
	public @ResponseBody RestResult verifyCode(VerifyCodeDto verifyCodeDto) {
		if (CheckUtils.isNull(verifyCodeDto) || CheckUtils.isNullOrEmpty(verifyCodeDto.getOpenid())) {
			return new RestResult(Constant.Num.ERROR, "数据传输错误");
		}
		try {
			// 根据用户openid查询用户信息
			WechatUser wechatUser = wechatUserService.findByOpenid(verifyCodeDto.getOpenid());
			if (CheckUtils.isNull(wechatUser)) {
				return new RestResult(Constant.Num.ERROR, "信息获取失败，请刷新重试");
			}
			long now = DateUtils.getNowTimestamp().getTime()/1000;
			if (!CheckUtils.isNull(wechatUser.getVerifyCodeTime()) && 
				!CheckUtils.isNullOrEmpty(wechatUser.getVerifyCode())) {
				long verifyCodeTime = wechatUser.getVerifyCodeTime().getTime()/1000;
				if (now < verifyCodeTime+120) {
					return new RestResult("操作过于频繁<br>请稍后再试", wechatUser.getVerifyCode());
				}
				if (now < verifyCodeTime+300) {
					verifyCodeDto.setRandomNum(wechatUser.getVerifyCode());
				}
			}
			SendSmsResponse response = SmsHelper.sendSms(verifyCodeDto.getPhoneNumber(), Constant.Str.SIGN_NAME, verifyCodeDto.getRandomNum());
	        System.out.println("短信接口返回的数据----------------");
	        System.out.println("Code=" + response.getCode());
	        System.out.println("Message=" + response.getMessage());
	        System.out.println("RequestId=" + response.getRequestId());
	        System.out.println("BizId=" + response.getBizId());
	        if (response.getCode().equals("OK")) {
	        	// 更新手机号
	        	wechatUser.setVerifyPhone(verifyCodeDto.getPhoneNumber());
	        	// 更新验证码
	        	wechatUser.setVerifyCode(verifyCodeDto.getRandomNum());
	        	// 更新验证码时间
	        	wechatUser.setVerifyCodeTime(DateUtils.getNowTimestamp());
	        	// 更新用户信息
				wechatUserService.update(wechatUser);
	        	return new RestResult("短信发送成功", wechatUser.getVerifyCode());
	        }
	        if (response.getCode().equals("isv.BUSINESS_LIMIT_CONTROL")) {
	        	if (response.getMessage().contains("触发天级流控")) {
	        		return new RestResult(Constant.Num.ERROR, "触发天级流控");
	        	} else {
	        		return new RestResult(Constant.Num.ERROR, "操作过于频繁<br>请稍后再试");
	        	}
	        } else {
	        	return new RestResult(Constant.Num.ERROR, "失败，请稍后再试");
	        }
		} catch (ClientException e) {
			logger.error("发送验证码失败："+e.getMessage());
			return new RestResult(Constant.Num.ERROR, "请重试！");
		}
	}
	/**
	 * 输入手机号点击领取卡券后入口
	 * @param loginReqDto
	 * @return
	 */
	@RequestMapping(value = "/doLogin", method = RequestMethod.POST)
	public @ResponseBody RestResult doLogin(VerifyCodeDto verifyCodeDto) {
		if (CheckUtils.isNull(verifyCodeDto) || CheckUtils.isNullOrEmpty(verifyCodeDto.getOpenid())) {
			return new RestResult(Constant.Num.ERROR, "数据传输错误");
		}
		if (!verifyCodeDto.getIfVerify().equals("no")) {
			// 根据用户openid查询用户信息
			WechatUser wechatUser = wechatUserService.findByOpenid(verifyCodeDto.getOpenid());
			if (CheckUtils.isNull(wechatUser)) {
				return new RestResult(Constant.Num.ERROR, "信息获取失败，请刷新重试");
			}
			if (CheckUtils.isNull(wechatUser.getVerifyCodeTime()) || 
				CheckUtils.isNullOrEmpty(wechatUser.getVerifyCode())) {
				return new RestResult(Constant.Num.ERROR, "请确认验证码是否正确");
			}
			if (CheckUtils.isNullOrEmpty(wechatUser.getVerifyPhone()) || wechatUser.getVerifyPhone().length() != Constant.Num.INT_ELEVEN) {
				return new RestResult(Constant.Num.ERROR, "请检查手机号是否正确");
			}
			if (CheckUtils.isNullOrEmpty(verifyCodeDto.getPhoneNumber()) || verifyCodeDto.getPhoneNumber().length() != Constant.Num.INT_ELEVEN) {
				return new RestResult(Constant.Num.ERROR, "请检查手机号是否正确");
			}
			if (!wechatUser.getVerifyPhone().equals(verifyCodeDto.getPhoneNumber()) && !wechatUser.getVerifyCode().equals(verifyCodeDto.getVerifyCode())) {
				return new RestResult(Constant.Num.ERROR, "请输入获取短信验证码的手机号");
			}
			long now = DateUtils.getNowTimestamp().getTime()/1000;
			long verifyCodeTime = wechatUser.getVerifyCodeTime().getTime()/1000;
			if (now > verifyCodeTime+300) {
				return new RestResult(Constant.Num.ERROR, "请确认验证码是否正确");
			}
			try {
				// 设置用户手机号
				wechatUser.setPhone(verifyCodeDto.getPhoneNumber());
				// 更新用户信息
				wechatUserService.update(wechatUser);
				// 进入卡券画面
				return new RestResult(Constant.Num.SUCCESS, "success"); 
			} catch(Exception e) {
				// 错误处理
				logger.error("添加手机号码失败："+e.getMessage());
				return new RestResult(Constant.Num.ERROR, "添加失败，请稍后重试"); 
			}
		} else {
			// 进入卡券画面
			return new RestResult(Constant.Num.SUCCESS, "success");
		}
	}
	
	/**
	 * 添加用户分享数计数
	 * @param openid
	 * @return
	 */
	@RequestMapping(value = "/addShareNumber")
	@ResponseBody
	public int addShareNumber(@RequestParam(value = "openid", required = true) String openid) {
		if (CheckUtils.isNullOrEmpty(openid)) {
			return Constant.Num.INT_ZERO;
		}
		try {
			// 添加分享数
			int result = wechatUserService.addShareNumber(openid);
			return result;
		} catch(Exception e) {
			logger.error("添加分享数失败："+e.getMessage());
			return Constant.Num.INT_ZERO;
		}
	}
	
}
