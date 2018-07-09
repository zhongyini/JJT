package com.xxx.wechat.front.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xxx.wechat.common.utils.CheckUtils;
import com.xxx.wechat.core.dao.WechatCardCodeDao;
import com.xxx.wechat.core.dao.entity.WechatCardCode;
import com.xxx.wechat.core.exception.AppException;
import com.xxx.wechat.front.service.IWechatCardCodeService;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service("wechatCardCodeService")
public class WechatCardCodeServiceImpl implements IWechatCardCodeService {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	WechatCardCodeDao wechatCardCodeDao;
	
	@Override
	public int addWechatCard(WechatCardCode wechatCardCode) throws AppException {
		return wechatCardCodeDao.insert(wechatCardCode);
	}

	@Override
	public List<WechatCardCode> select(WechatCardCode wechatCardCode) throws AppException {
		Example example = new Example(WechatCardCode.class);
		Criteria criteria = example.createCriteria();
		if(!CheckUtils.isNullOrEmpty(wechatCardCode.getCardId())) {
			criteria.andEqualTo("cardId", wechatCardCode.getCardId());
		}
		if(!CheckUtils.isNullOrEmpty(wechatCardCode.getCode())) {
			criteria.andEqualTo("code", wechatCardCode.getCode());
		}
		if(!CheckUtils.isNullOrEmpty(wechatCardCode.getRecEdOpenid())) {
			criteria.andEqualTo("recEdOpenid", wechatCardCode.getRecEdOpenid());
		}
		if(!CheckUtils.isNullOrEmpty(wechatCardCode.getRecOpenid())) {
			criteria.andEqualTo("recOpenid", wechatCardCode.getRecOpenid());
		}
		if(!CheckUtils.isNull(wechatCardCode.getCodeId())) {
			criteria.andEqualTo("codeId", wechatCardCode.getCodeId());
		}
		if(!CheckUtils.isNull(wechatCardCode.getCodeStatus())) {
			criteria.andEqualTo("codeStatus", wechatCardCode.getCodeStatus());
		}
		if(!CheckUtils.isNull(wechatCardCode.getMoney())) {
			criteria.andEqualTo("money", wechatCardCode.getMoney());
		}
		if(!CheckUtils.isNull(wechatCardCode.getRedPacketStatus())) {
			criteria.andEqualTo("redPacketStatus", wechatCardCode.getRedPacketStatus());
		}
		return wechatCardCodeDao.selectByExample(example);
	}

	@Override
	public String selectAmountCashByRecOpenid(String recOpenid) throws AppException {
		return wechatCardCodeDao.selectAmountCashByRecOpenid(recOpenid);
	}

	@Override
	public int selectCountNum() throws AppException {
		return wechatCardCodeDao.selectCountNum();
	}

}
