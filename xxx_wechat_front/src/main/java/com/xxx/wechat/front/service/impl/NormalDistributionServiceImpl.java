package com.xxx.wechat.front.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xxx.wechat.common.utils.CheckUtils;
import com.xxx.wechat.common.utils.DateUtils;
import com.xxx.wechat.core.dao.NormalDistributionDao;
import com.xxx.wechat.core.dao.entity.NormalDistribution;
import com.xxx.wechat.core.exception.AppException;
import com.xxx.wechat.front.service.INormalDistributionService;

@Service
public class NormalDistributionServiceImpl implements INormalDistributionService {

	@Autowired
	NormalDistributionDao normalDistributionDao;

	@Override
	public NormalDistribution selectNormalDistributionByNumber(BigDecimal number) throws AppException {
		NormalDistribution normalDistribution = normalDistributionDao.selectNormalDistributionByNumber(number);
		// 不为空返回结果,为空继续查询
		if (!CheckUtils.isNull(normalDistribution)) {
			return normalDistribution;
		}
		// 查询大于这个数的集合
		List<NormalDistribution> normalDistributionGreateThanList = normalDistributionDao
				.selectNormalDistributionListByNumberGreateThan(number);

		if (CheckUtils.isNull(normalDistributionGreateThanList) || normalDistributionGreateThanList.size() == 0) {
			throw new AppException("没有大于该数的值，无法计算");
		}

		// 查询大于这个数的集合
		List<NormalDistribution> normalDistributionLessThanList = normalDistributionDao
				.selectNormalDistributionListByNumberLessThan(number);
		if (CheckUtils.isNull(normalDistributionLessThanList) || normalDistributionLessThanList.size() == 0) {
			throw new AppException("没有小于该数的值，无法计算");
		}
		normalDistribution = new NormalDistribution();
		normalDistribution.setDeleteFlag(0);
		normalDistribution.setNumber(number);
		normalDistribution.setUpdateTime(DateUtils.getNowTimestamp());
		normalDistribution.setUpdateUser("sys");

		BigDecimal greateThanNumber = normalDistributionGreateThanList.get(0).getNumber();
		BigDecimal lessThanNumber = normalDistributionLessThanList.get(0).getNumber();
		BigDecimal greateThanResult = normalDistributionGreateThanList.get(0).getResult();
		BigDecimal lessThanResult = normalDistributionLessThanList.get(0).getResult();
		BigDecimal result = number.subtract(lessThanNumber);
		result = result.divide(greateThanNumber.subtract(lessThanNumber));
		result = result.multiply(greateThanResult.subtract(lessThanResult));
		result = result.add(lessThanResult);
		normalDistribution.setResult(result);
		insert(normalDistribution);
		return normalDistribution;
	}

	@Override
	public int insert(NormalDistribution normalDistribution) throws AppException {
		normalDistribution.setDeleteFlag(0);
		normalDistribution.setUpdateTime(DateUtils.getNowTimestamp());
		normalDistribution.setUpdateUser("sys");
		return normalDistributionDao.insert(normalDistribution);
	}

}
