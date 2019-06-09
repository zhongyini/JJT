package com.xxx.wechat.admin.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.xxx.wechat.admin.BaseController;
import com.xxx.wechat.admin.RestResult;
import com.xxx.wechat.admin.dto.LoginReq;
import com.xxx.wechat.admin.service.IWechatCardCodeService;
import com.xxx.wechat.admin.service.IWechatCardConsumeService;
import com.xxx.wechat.admin.service.IWechatUserAccountService;
import com.xxx.wechat.common.constant.ConfigurationEnum;
import com.xxx.wechat.common.utils.CheckUtils;
import com.xxx.wechat.common.wechat.api.CouponApi;
import com.xxx.wechat.common.wechat.api.response.CouponResponse;
import com.xxx.wechat.constants.Constants;
import com.xxx.wechat.core.config.ConfigurationConfig;
import com.xxx.wechat.core.config.WechatTokenConfig;

@RestController
@RequestMapping("/coupon")
public class CouponController extends BaseController{
	
	@Autowired
	private DataSourceTransactionManager txManager;
	@Autowired
	private IWechatCardCodeService cardCodeService;
	@Autowired
	private IWechatCardConsumeService consumeService;
	@Autowired
	private IWechatUserAccountService userAccountService;
	
	
	@RequestMapping(value = "/cancel", method = RequestMethod.POST)
	public RestResult cardCancellation (String code,String cardId) {
		//code参数检测
		if (CheckUtils.isNullOrEmpty(code)) {
			return new RestResult(messageHelper.mesg_info_1701);
		}
		
		TransactionStatus status = null;
		
		try {
		//开启事务
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		status = txManager.getTransaction(def);
		//从session中拿出核销人的ID
		HttpSession session = request.getSession();
		LoginReq appAdmin = (LoginReq)session.getServletContext().getAttribute(Constants.USER);
		//如果为空则提示重新登录
		if (CheckUtils.isNull(appAdmin)) {
			return new RestResult(messageHelper.mesg_info_1715);
		}
		
		CouponApi couponApi = new CouponApi(WechatTokenConfig.getInstance().getAccessToken());
		//查询微信卡券的状态
		CouponResponse response = couponApi.queryCode(code, cardId);
		//如果为空则该卡券不存在
		if (CheckUtils.isNull(response)) {
			return new RestResult(messageHelper.mesg_info_1700);
		}
		//如果卡券异常则提示不可用
		//NORMAL：正常， CONSUMED：已核销 ，EXPIRE：已过期 ，GIFTING：转赠中，GIFT_TIMEOUT：转赠超时，
		//DELETE：已删除，UNAVAILABLE：已失效，code未被添加或被转赠领取的情况则统一报错：invalid serial code
		if ("CONSUMED".equals(response.getUserCardStatus())) {
			return new RestResult(messageHelper.mesg_info_1717);
		}
		if ("EXPIRE".equals(response.getUserCardStatus())) {
			return new RestResult(messageHelper.mesg_info_1718);
		}
		if ("DELETE".equals(response.getUserCardStatus())) {
			return new RestResult(messageHelper.mesg_info_1719);
		}
		if (!"NORMAL".equals(response.getUserCardStatus())) {
			return new RestResult(messageHelper.mesg_info_1702);
		}
		logger.info("该卡券可以正常使用");
		//获取返点金额
		String money = ConfigurationConfig.getInstance().getProperty(ConfigurationEnum.MONEY);
		if (CheckUtils.isNullOrEmpty(money)) {
			return new RestResult(messageHelper.mesg_info_1714);
		}
		
		//查询本地卡券code是否存在,如果不存在则进行添加
		cardCodeService.queryCardCode(response,code);

		
		//修改卡券核销状态WechatCardCode
		int changeStatus = cardCodeService.updateCardCode(code,money);
		if (changeStatus != 1) {
			return new RestResult(messageHelper.mesg_info_1703);
		}
		
		//插入核销表WechatCardConsume
		int saveStatus = consumeService.saveCardConsume(appAdmin,response,money,code);
		//如果插入失败则提示
		if (saveStatus != 1) {
			return new RestResult(messageHelper.mesg_info_1709);
		}
		
		//查询该用户是否有开通账户,如果不存在则进行添加,如果有推荐人则修改推荐人的余额
		userAccountService.selectUserAccount(response.getOpenid(),money);
		
		//开始正式（微信）核销
		response = couponApi.CancelAfterVerification(code, response.getCard().getCardId());
		//如果核销失败则提示核销失败
		if (!"0".equals(response.getErrcode()) && !"ok".equals(response.getErrmsg())) {
			return new RestResult(messageHelper.mesg_info_1703);
		}
		//提交事务
		txManager.commit(status);
		} catch (Exception e) {  
			logger.error("发生未知错误，请重新操作或联系管理员");
			logger.error(e.getMessage());
			// 出现异常回滚事务
			txManager.rollback(status);
			return new RestResult(messageHelper.mesg_info_1703);
		}
		return new RestResult(Constants.SUCCESS,messageHelper.mesg_info_1704);
	}
	
	
	/*@RequestMapping(value = "/cardDetails", method = RequestMethod.POST)
	public RestResult cardDetails (String code) {
		WechatCardCode cardcode = cardCodeService.queryCardCode(code);
		if (!CheckUtils.isNull(cardcode)) {
			WechatCard card = cardService.query(cardcode.getCardId());
			return new RestResult(card);
		}
		return new RestResult();
	}*/
}
