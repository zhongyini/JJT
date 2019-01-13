package com.xxx.wechat.core.dao;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.xxx.wechat.core.BaseDao;
import com.xxx.wechat.core.dao.entity.LotteryDltGuess;

@Mapper
public interface LotteryDltGuessDao extends BaseDao<LotteryDltGuess> {

	int insertLotteryDltGuessList(List<LotteryDltGuess> list);
}
