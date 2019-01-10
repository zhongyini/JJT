package com.xxx.wechat.front.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xxx.wechat.core.dao.LotteryDltHistoryDetailDao;
import com.xxx.wechat.core.dao.entity.LotteryDltHistoryDetail;
import com.xxx.wechat.core.exception.AppException;
import com.xxx.wechat.front.service.ILotteryDltHistoryDetailService;

@Service
public class LotteryDltHistoryDetailServiceImpl implements ILotteryDltHistoryDetailService {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	LotteryDltHistoryDetailDao lotteryDltHistoryDetailDao;

	@Override
	public int add(List<LotteryDltHistoryDetail> lotteryDltHistoryDetailList) throws AppException {
		try {
			return lotteryDltHistoryDetailDao.insertLotteryDltHistoryDetailList(lotteryDltHistoryDetailList);
		} catch (AppException e) {
			logger.error("错误数据：" + lotteryDltHistoryDetailList.toString());
			logger.error("错误信息：" + e.getMessage());
			throw new AppException("插入大乐透详细数据失败");
		}
	}
}
