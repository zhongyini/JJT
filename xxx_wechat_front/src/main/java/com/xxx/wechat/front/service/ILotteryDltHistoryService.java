package com.xxx.wechat.front.service;

import java.util.List;

import com.xxx.wechat.core.dao.entity.LotteryDltHistory;
import com.xxx.wechat.core.exception.AppException;

public interface ILotteryDltHistoryService {

	int add(LotteryDltHistory lotteryDltHistory) throws AppException;
	
	List<String> getTermList() throws AppException;
	
	int generateLotteryDltGuess() throws AppException;
	
	String getLastTerm() throws AppException;
}
