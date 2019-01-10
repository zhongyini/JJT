package com.xxx.wechat.front.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.xxx.wechat.core.dao.entity.NormalDistribution;
import com.xxx.wechat.core.exception.AppException;
import com.xxx.wechat.front.dto.RestResult;
import com.xxx.wechat.front.service.INormalDistributionService;

/**
 * 正态分布求值
 *
 */
@RestController
@RequestMapping("/normal")
public class NormalDistributionController {
	
	@Autowired
	private INormalDistributionService normalDistributionService;
	
	/**
	 * 
	 * @param numberStr
	 * @return
	 */
	@RequestMapping(value = "/select", method = RequestMethod.GET)
	public RestResult selectNormalDistribution(String numberStr) {
		try{
			BigDecimal number = BigDecimal.valueOf(Double.valueOf(numberStr));
			NormalDistribution normalDistribution = normalDistributionService.selectNormalDistributionByNumber(number);
			return new RestResult(normalDistribution);
		} catch(AppException e) {
			
			return new RestResult(e.getMessage());
		}
		
	}
}
