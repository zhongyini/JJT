package com.xxx.wechat.front.service;

import java.util.List;

import com.xxx.wechat.core.dao.entity.LotteryDltHistoryDetail;
import com.xxx.wechat.core.exception.AppException;

public interface ILotteryDltHistoryDetailService {

	int add(List<LotteryDltHistoryDetail> lotteryDltHistoryDetailList) throws AppException;
}
