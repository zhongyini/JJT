package com.xxx.wechat.front.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xxx.wechat.common.utils.CheckUtils;
import com.xxx.wechat.core.dao.WechatCardDao;
import com.xxx.wechat.core.dao.entity.WechatCard;
import com.xxx.wechat.core.exception.AppException;
import com.xxx.wechat.front.service.IWechatCardService;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service("wechatCardService")
public class WechatCardServiceImpl implements IWechatCardService {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	WechatCardDao wechatCardDao;
	
	@Override
	public int addWechatCard(WechatCard wechatCard) throws AppException {
		return wechatCardDao.insert(wechatCard);
	}

	@Override
	public List<WechatCard> search(WechatCard wechatCard) throws AppException {
		
		Example example = new Example(WechatCard.class);
		Criteria criteria = example.createCriteria();
		if(!CheckUtils.isNullOrEmpty(wechatCard.getCardId())) {
			criteria.andEqualTo("cardId", wechatCard.getCardId());
		}
		if(!CheckUtils.isNullOrEmpty(wechatCard.getCardId())) {
			criteria.andEqualTo("cardType", wechatCard.getCardType());
		}
		if(!CheckUtils.isNullOrEmpty(wechatCard.getCodeType())) {
			criteria.andEqualTo("codeType", wechatCard.getCodeType());
		}
		if(!CheckUtils.isNullOrEmpty(wechatCard.getTitle())) {
			criteria.andEqualTo("title", wechatCard.getTitle());
		}
		return wechatCardDao.selectByExample(example);
	}

}
