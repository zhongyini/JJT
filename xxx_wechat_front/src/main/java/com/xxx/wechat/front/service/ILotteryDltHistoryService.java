package com.xxx.wechat.front.service;

import com.xxx.wechat.core.dao.entity.LotteryDltHistory;
import com.xxx.wechat.core.exception.AppException;

public interface ILotteryDltHistoryService {

	int add(LotteryDltHistory lotteryDltHistory) throws AppException;
}
