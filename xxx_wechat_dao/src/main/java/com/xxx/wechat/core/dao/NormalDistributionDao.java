package com.xxx.wechat.core.dao;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.xxx.wechat.core.BaseDao;
import com.xxx.wechat.core.dao.entity.NormalDistribution;

@Mapper
public interface NormalDistributionDao extends BaseDao<NormalDistribution> {
	
	NormalDistribution selectNormalDistributionByNumber(@Param("number") BigDecimal number);

	List<NormalDistribution> selectNormalDistributionListByNumberGreateThan(@Param("number") BigDecimal number);
	
	List<NormalDistribution> selectNormalDistributionListByNumberLessThan(@Param("number") BigDecimal number);
}
