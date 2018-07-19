package com.xxx.wechat.front.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.xxx.wechat.common.constant.ConfigurationEnum;
import com.xxx.wechat.common.constant.Constant;
import com.xxx.wechat.common.utils.CheckUtils;
import com.xxx.wechat.common.utils.DateUtils;
import com.xxx.wechat.common.utils.JsonUtils;
import com.xxx.wechat.common.wechat.api.CardApi;
import com.xxx.wechat.core.config.ConfigurationConfig;
import com.xxx.wechat.core.config.WechatTokenConfig;
import com.xxx.wechat.core.dao.entity.WechatCardCode;
import com.xxx.wechat.core.dao.entity.WechatUser;
import com.xxx.wechat.core.dao.entity.extend.WechatRecommendExt;
import com.xxx.wechat.front.authentication.LoginRequired;
import com.xxx.wechat.front.dto.BasePage;
import com.xxx.wechat.front.dto.RestResult;
import com.xxx.wechat.front.dto.UserCardCodeDto;
import com.xxx.wechat.front.service.IWechatCardCodeService;
import com.xxx.wechat.front.service.IWechatUserService;

@Controller
@RequestMapping("/card")
public class CardController extends BaseController {

	@Autowired
	IWechatCardCodeService wechatCardCodeService;

	@Autowired
	IWechatUserService wechatUserService;

	@LoginRequired(isLand = true)
	@RequestMapping("/view")
	public ModelAndView view() {
		// 从cookie中获取用户openid
		String openid = getOpenid();
		ModelAndView mav = new ModelAndView();
		if (CheckUtils.isNullOrEmpty(openid)) {
			logger.error("未获取到用户openid");
			// openid为空，跳转到错误画面
			mav.setViewName(Constant.ControllerUrl.COMMON_ERROR);
			return mav;
		}
		
		// 传到卡券画面的参数
		UserCardCodeDto userCardCodeDto = new UserCardCodeDto();
		WechatCardCode wechatCardCode = new WechatCardCode();
		try {
			WechatUser wechatUser = wechatUserService.findByOpenid(openid);
			if (!CheckUtils.isNull(wechatUser)) {
				userCardCodeDto.setPhone(wechatUser.getPhone());
			}
			// 根据用户openid获取用户领取的卡券列表
			wechatCardCode.setRecEdOpenid(openid);
			// 未使用的卡券状态
//			wechatCardCode.setCodeStatus(Constant.Num.INT_ZERO);
			List<WechatCardCode> wechatCardCodeList = selectWechatCardCode(wechatCardCode);
			if (!CheckUtils.isNull(wechatCardCodeList) && wechatCardCodeList.size() > Constant.Num.INT_ZERO) {
				for (int i = 0; i < wechatCardCodeList.size(); i++) {
					if (wechatCardCodeList.get(i).getCodeStatus() == Constant.Num.INT_ZERO) {
						userCardCodeDto.setCardId(wechatCardCodeList.get(i).getCardId());
						userCardCodeDto.setCode(wechatCardCodeList.get(i).getCode());
						break;
					} else if (i==wechatCardCodeList.size()-1) {
						userCardCodeDto.setCardId(wechatCardCodeList.get(i).getCardId());
						userCardCodeDto.setCode(wechatCardCodeList.get(i).getCode());
					}
				}
//				userCardCodeDto.setCardId(wechatCardCodeList.get(Constant.Num.INT_ZERO).getCardId());
//				userCardCodeDto.setCode(wechatCardCodeList.get(Constant.Num.INT_ZERO).getCode());
			}
			Page<WechatRecommendExt> brotherList = selectBrotherListByRecEdOpenid(openid, Constant.Num.INT_ONE, Integer.valueOf(ConfigurationConfig.getInstance().getProperty(ConfigurationEnum.LIST_PAGE_SIZE)));
			if (CheckUtils.isNull(brotherList)) {
				brotherList = new Page<WechatRecommendExt>();
			}
			userCardCodeDto.setTotal(brotherList.getTotal());
			userCardCodeDto.setOpenid(openid);
			userCardCodeDto.setBrotherList(brotherList);
			// 领券人数
			String codeNum = String.valueOf(wechatCardCodeService.selectCountNum());
			userCardCodeDto.setCodeNum(codeNum);
			// list
			mav.addObject("result", userCardCodeDto);
			mav.setViewName(Constant.ControllerUrl.CARD_VIEW);
			return mav;
		} catch (Exception e) {
			logger.error("获取卡券信息失败：" + e.getMessage());
			mav.setViewName(Constant.ControllerUrl.COMMON_ERROR);
			return mav;
		}

	}

	/**
	 * 添加卡券信息
	 * 
	 * @param res
	 *            微信端添加卡券时返回的数据
	 * @param recommendOpenid
	 *            推荐人openid
	 * @return
	 */
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public @ResponseBody String insert(@RequestParam("res") String res, @RequestParam("openid") String openid,
			@RequestParam("recommend_openid") String recommendOpenid) {
		logger.info("添加卡券信息微信端返回结果：" + res);
		if (CheckUtils.isNullOrEmpty(res) || CheckUtils.isNullOrEmpty(openid)
				|| CheckUtils.isNullOrEmpty(recommendOpenid)) {
			// 卡券信息、用户openid、推荐者openid为空
			logger.error("卡券信息或用户openid为空：res=" + res + ",openid=" + openid + ",recommendOpenid=" + recommendOpenid);
			// 返回空
			return Constant.EMPTY;
		}
		WechatCardCode wechatCardCode = null;
		try {
			// 获取卡券code表信息
			wechatCardCode = createWechatCardCode(res, openid, recommendOpenid);
			// 添加卡券code表信息
			wechatCardCodeService.addWechatCard(wechatCardCode);
		} catch (Exception e) {
			logger.error("用户openid：" + openid + "添加卡券信息失败：" + wechatCardCode + e.getMessage());
			// 返回空
			return Constant.EMPTY;
		}
		return "添加成功";
	}

	@RequestMapping("/page")
	public @ResponseBody RestResult page(String openid, int pageNum, int pageSize) {
		Page<WechatRecommendExt> brotherList = selectBrotherListByRecEdOpenid(openid, pageNum, pageSize);
		return new RestResult(new BasePage(brotherList.getTotal(), brotherList.getResult()));
	}
	
	@RequestMapping("/wechatCard")
	public ModelAndView wechatCard(String card_id, String encrypt_code, String openid) {
		
		ModelAndView mav = new ModelAndView();
		try {
			String code = decryptCode(encrypt_code);
			mav.addObject("code", code);
			mav.setViewName("adminCode/goAdmin");
			return mav;
		} catch (Exception e) {
			logger.error("请重试");
			mav.setViewName(Constant.ControllerUrl.COMMON_ERROR);
			return mav;
		}
	}
	
	@RequestMapping("/newWCard")
	public ModelAndView redirectWechatCard(String code, String message) {
		ModelAndView mav = new ModelAndView();
		try {
			mav.addObject("code", code);
			mav.addObject("message", message);
			mav.setViewName("adminCode/goAdmin");
			return mav;
		} catch (Exception e) {
			logger.error("请重试");
			mav.setViewName(Constant.ControllerUrl.COMMON_ERROR);
			return mav;
		}
	}
	
	/**
	 * 
	 * @param encryptCode
	 *            加密的卡号
	 * @return
	 */
	private String decryptCode(String encryptCode) {
		try {
			// 解密卡号接口，返回解密后的卡号
			return new CardApi(WechatTokenConfig.getInstance().getAccessToken()).decryptCode(encryptCode);
		} catch (Exception e) {
			logger.error("解码code失败：" + encryptCode);
		}
		return Constant.EMPTY;
	}

	/**
	 * 将微信返回添加卡券结果解析
	 * 
	 * @param res
	 *            微信端添加卡券时返回的数据
	 * @param openid
	 *            卡券所有者openid
	 * @return
	 */
	private WechatCardCode createWechatCardCode(String res, String openid, String recommendOpenid) {
		WechatCardCode wechatCardCode = new WechatCardCode();
		String code = null;
		try {
			// 返回领取的卡券集合
			String cardList = JsonUtils.getStringFromJSONObject(res, "cardList").toString();
			// 将卡券集合转成json数组格式
			JSONArray array = JSONObject.parseArray(cardList);
			// 第一个卡券信息
			cardList = array.getString(0);
			// 获取加密的卡号
			String encryptCode = JsonUtils.getStringFromJSONObject(cardList, "code").toString();
			// 获取卡券id
			String cardId = JsonUtils.getStringFromJSONObject(cardList, "cardId").toString();
			// 添加卡券是否成功
			String isSuccess = JsonUtils.getStringFromJSONObject(cardList, "isSuccess").toString();
			if (!"true".equals(isSuccess)) {
				// 添加卡券失败
				logger.error("添加领取卡券信息失败res：" + res);
				return null;
			}
			// 卡券id
			wechatCardCode.setCardId(cardId);
			// 解密后的卡号
			code = decryptCode(encryptCode);
			if (CheckUtils.isNullOrEmpty(code)) {
				logger.error("用户openid:" + openid + "的code解码失败");
			}
		} catch (Exception e) {
			logger.error("添加领取卡券信息失败：" + wechatCardCode + e.getMessage());
			return null;
		}
		// 卡号
		wechatCardCode.setCode(code);
		// 被推荐人openid
		wechatCardCode.setRecEdOpenid(openid);
		// 推荐人openid
		wechatCardCode.setRecOpenid(recommendOpenid);
		// 金额初始为0
		wechatCardCode.setMoney(Constant.Str.STR_ZERO);
		// 卡券状态：0、未使用；1、已使用
		wechatCardCode.setCodeStatus(Constant.Num.INT_ZERO);
		// 红包领取状态：0、未领取；1、已领取
		wechatCardCode.setRedPacketStatus(Constant.Num.INT_ZERO);
		// 更新时间
		wechatCardCode.setUpdatetime(DateUtils.getNowTimestamp());
		return wechatCardCode;
	}
	
	/**
	 * 查询卡券兄弟关系
	 */
	private Page<WechatRecommendExt> selectBrotherListByRecEdOpenid(String recEdOpenid, int pageNum, int pageSize) {
		try {
			return wechatRecommendService.selectBrotherListByRecEdOpenid(recEdOpenid, pageNum, pageSize);
		} catch (Exception e) {
			logger.error("用户openid："+recEdOpenid+"获取兄弟关系失败"+e.getMessage());
		}
		return null;
	}

}
