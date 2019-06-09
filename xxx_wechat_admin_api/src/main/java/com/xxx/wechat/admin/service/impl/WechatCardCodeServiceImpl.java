package com.xxx.wechat.admin.service.impl;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xxx.wechat.admin.service.IWechatCardCodeService;
import com.xxx.wechat.common.utils.CheckUtils;
import com.xxx.wechat.common.utils.DateUtils;
import com.xxx.wechat.common.wechat.api.response.CouponResponse;
import com.xxx.wechat.core.dao.WechatCardCodeDao;
import com.xxx.wechat.core.dao.entity.WechatCardCode;
import com.xxx.wechat.core.exception.AppException;
import com.xxx.wechat.helper.MessageHelper;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service("wechatCardCodeService")
public class WechatCardCodeServiceImpl implements IWechatCardCodeService{
	private static final Logger logger = LoggerFactory
			.getLogger(WechatCardCodeServiceImpl.class);
	
	@Autowired
	private WechatCardCodeDao cardDao;
	@Autowired
	private MessageHelper messageHelper;
	
	@Override
	public int updateCardCode(String code,String money) throws AppException {
		try {
		WechatCardCode card = cardDao.queryCode(code);
		if (CheckUtils.isNull(card)) {
			logger.error("该卡号不存在");
			throw new AppException(messageHelper.mesg_info_1700);
		}
		card.setUpdatetime(DateUtils.getNowTimestamp());
		card.setCodeStatus(1);
		card.setMoney(money);
		Example rexample = new Example(WechatCardCode.class);
		Criteria criteria = rexample.createCriteria();
		criteria.andEqualTo("code", card.getCode());
		
		return cardDao.updateByExampleSelective(card, rexample);
		
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new AppException(messageHelper.mesg_error_0001);
		}
	}

	@Override
	public void queryCardCode(CouponResponse response,String code) throws AppException {
		if (CheckUtils.isNullOrEmpty(code)) {
			logger.error("卡券code不能为空");
			throw new AppException(messageHelper.mesg_info_1701);
		}
		WechatCardCode cardCode = cardDao.queryCode(code);
		if (CheckUtils.isNull(cardCode)) {
			logger.info("本地DB中不存在该条卡券数据，重新进行添加...");
			int changeStatus = saveCardCode(response,code);
			if (changeStatus != 1) {
				logger.error("添加卡券code失败");
				throw new AppException(messageHelper.mesg_info_1710);
			}
		}
	}

	@Override
	public int saveCardCode(CouponResponse response,String code) throws AppException {
		if (CheckUtils.isNullOrEmpty(response.getCard().getCardId())) {
			logger.error("卡券ID不能为空");
			throw new AppException(messageHelper.mesg_info_1706);
		}
		if (CheckUtils.isNullOrEmpty(code)) {
			logger.error("卡券code不能为空");
			throw new AppException(messageHelper.mesg_info_1701);
		}
		if (CheckUtils.isNullOrEmpty(response.getOpenid())) {
			logger.error("用户的openID不能为空");
			throw new AppException(messageHelper.mesg_info_1711);
		}
		WechatCardCode wechatcardcode = new WechatCardCode();
		wechatcardcode.setCode(code);
		wechatcardcode.setCardId(response.getCard().getCardId());
		wechatcardcode.setRecOpenid(response.getOpenid());
		wechatcardcode.setCodeStatus(0);
		wechatcardcode.setRedPacketStatus(0);
		wechatcardcode.setUpdatetime(DateUtils.getNowTimestamp());
		wechatcardcode.setMoney("0");
		return cardDao.insert(wechatcardcode);
	}

}
