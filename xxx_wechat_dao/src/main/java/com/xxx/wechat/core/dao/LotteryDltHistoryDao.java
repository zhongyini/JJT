package com.xxx.wechat.core.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.xxx.wechat.core.BaseDao;
import com.xxx.wechat.core.dao.entity.LotteryDltHistory;

@Mapper
public interface LotteryDltHistoryDao extends BaseDao<LotteryDltHistory> {

	List<LotteryDltHistory> selectList(LotteryDltHistory lotteryDltHistory);
	
	int selectLength();
	
}
