package com.xxx.wechat.front.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xxx.wechat.core.dao.LotteryDltHistoryDao;
import com.xxx.wechat.core.dao.entity.LotteryDltHistory;
import com.xxx.wechat.core.exception.AppException;
import com.xxx.wechat.front.service.ILotteryDltHistoryService;

@Service
public class LotteryDltHistoryServiceImpl implements ILotteryDltHistoryService {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	LotteryDltHistoryDao lotteryDltHistoryDao;
	
	@Override
	public int add(LotteryDltHistory lotteryDltHistory) throws AppException {
		try {
			return lotteryDltHistoryDao.insert(lotteryDltHistory);
		} catch (AppException e) {
			logger.error("错误数据：" + lotteryDltHistory.toString());
			logger.error("错误信息：" + e.getMessage());
			throw new AppException("插入大乐透数据失败");
		}
	}
}
