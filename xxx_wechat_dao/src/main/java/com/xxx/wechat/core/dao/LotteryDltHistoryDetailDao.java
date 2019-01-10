package com.xxx.wechat.core.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.xxx.wechat.core.BaseDao;
import com.xxx.wechat.core.dao.entity.LotteryDltHistoryDetail;

@Mapper
public interface LotteryDltHistoryDetailDao extends BaseDao<LotteryDltHistoryDetail> {

	int insertLotteryDltHistoryDetailList(List<LotteryDltHistoryDetail> lotteryDltHistoryDetailList);
}
