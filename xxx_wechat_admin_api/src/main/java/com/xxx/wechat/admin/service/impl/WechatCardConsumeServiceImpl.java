package com.xxx.wechat.admin.service.impl;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xxx.wechat.admin.dto.LoginReq;
import com.xxx.wechat.admin.service.IWechatCardConsumeService;
import com.xxx.wechat.common.utils.CheckUtils;
import com.xxx.wechat.common.utils.DateUtils;
import com.xxx.wechat.common.wechat.api.response.CouponResponse;
import com.xxx.wechat.core.dao.WechatCardConsumeDao;
import com.xxx.wechat.core.dao.entity.WechatCardConsume;
import com.xxx.wechat.core.exception.AppException;
import com.xxx.wechat.helper.MessageHelper;

@Service(value = "wechatCardConsumeService")
public class WechatCardConsumeServiceImpl implements IWechatCardConsumeService{
	
	private static final Logger logger = LoggerFactory
			.getLogger(WechatCardConsumeServiceImpl.class);
	
	@Autowired
	private WechatCardConsumeDao cardConsumeDao;
	@Autowired
	private MessageHelper messageHelper;
	
	@Override
	public int saveCardConsume(LoginReq appAdmin,CouponResponse response,String money,String code) throws AppException {
		if (CheckUtils.isNullOrEmpty(appAdmin.getName())) {
			logger.error("核销人ID不能为空");
			throw new AppException(messageHelper.mesg_info_1705);
		}
		if (CheckUtils.isNullOrEmpty(response.getCard().getCardId())) {
			logger.error("卡券ID不能为空");
			throw new AppException(messageHelper.mesg_info_1706);
		}
		if (CheckUtils.isNullOrEmpty(code)) {
			logger.error("卡券code不能为空");
			throw new AppException(messageHelper.mesg_info_1701);
		}
		if (CheckUtils.isNullOrEmpty(response.getOpenid())) {
			logger.error("被核销人的openId不能为空");
			throw new AppException(messageHelper.mesg_info_1707);
		}
		WechatCardConsume cardConsume = new WechatCardConsume();
		cardConsume.setAdminId(appAdmin.getName());
		//卡号
		cardConsume.setCodeId(code);
		//修改时间
		cardConsume.setUpdatetime(DateUtils.getNowTimestamp());
		//返点金额
		cardConsume.setMoney(String.valueOf(money));
		return cardConsumeDao.insert(cardConsume);
	}

}
