package com.xxx.wechat.core.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.xxx.wechat.core.BaseDao;
import com.xxx.wechat.core.dao.entity.LotteryDltHistory;
import com.xxx.wechat.core.dao.entity.extend.LotteryDltHistoryExt;

@Mapper
public interface LotteryDltHistoryDao extends BaseDao<LotteryDltHistory> {

	List<LotteryDltHistory> selectList(LotteryDltHistory lotteryDltHistory);
	
	int selectMaxTerm();
	
	List<String> selectTermList();
	
	List<LotteryDltHistoryExt> selectFieldAndFieldCountByField(@Param("fieldName") String fieldName);
	
	LotteryDltHistory selectLastTerm();
	
}
