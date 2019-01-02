package com.xxx.wechat.front.service;

import java.math.BigDecimal;

import com.xxx.wechat.core.dao.entity.NormalDistribution;
import com.xxx.wechat.core.exception.AppException;

public interface INormalDistributionService {

	NormalDistribution selectNormalDistributionByNumber(BigDecimal number) throws AppException;
	
	int insert(NormalDistribution normalDistribution) throws AppException;
}
