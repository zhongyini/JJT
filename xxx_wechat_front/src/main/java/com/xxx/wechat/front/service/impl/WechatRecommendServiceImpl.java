package com.xxx.wechat.front.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xxx.wechat.common.constant.Constant;
import com.xxx.wechat.common.utils.CheckUtils;
import com.xxx.wechat.common.utils.DateUtils;
import com.xxx.wechat.core.dao.WechatRecommendDao;
import com.xxx.wechat.core.dao.entity.WechatRecommend;
import com.xxx.wechat.core.dao.entity.extend.WechatRecommendExt;
import com.xxx.wechat.core.exception.AppException;
import com.xxx.wechat.front.service.IWechatRecommendService;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service("wechatRecommendService")
public class WechatRecommendServiceImpl implements IWechatRecommendService {

	@Autowired
	WechatRecommendDao wechatRecommendDao;
	
	@Override
	public Page<WechatRecommendExt> selectRecommendListByRecOpenid(String recOpenid, int pageNum, int pageSize) throws AppException {
		PageHelper.startPage(pageNum, pageSize);
		return (Page<WechatRecommendExt>)wechatRecommendDao.selectRecommendListByRecOpenid(recOpenid);
	}
	
	@Override
	public Page<WechatRecommendExt> selectBrotherListByRecEdOpenid(String recEdOpenid, int pageNum, int pageSize) throws AppException {
		PageHelper.startPage(pageNum, pageSize);
		return (Page<WechatRecommendExt>) wechatRecommendDao.selectBrotherListByRecEdOpenid(recEdOpenid);
	}

	@Override
	public int addWechatRecommend(WechatRecommend wechatRecommend) throws AppException {
		wechatRecommend.setUpdatetime(DateUtils.getNowTimestamp());
		return wechatRecommendDao.insert(wechatRecommend);
	}

	@Override
	public List<WechatRecommend> selectRecListByRecEdOpenid(String recEdOpenid) throws AppException {
		return wechatRecommendDao.selectRecListByRecEdOpenid(recEdOpenid);
	}
	
	@Override
	public Integer selectRecNumByRecOpenid(String openid) throws AppException {
		Integer result = wechatRecommendDao.selectRecNumByRecOpenid(openid);
		return CheckUtils.isNull(result)?0:result;
	}

	@Override
	public WechatRecommend selectByTwoOpenid(WechatRecommend wechatRecommend) throws AppException {
		Example example = new Example(WechatRecommend.class);
		Criteria criteria = example.createCriteria();
		if (!CheckUtils.isNullOrEmpty(wechatRecommend.getRecEdOpenid())) {
			criteria.andEqualTo("recEdOpenid", wechatRecommend.getRecEdOpenid());
		}
		if (!CheckUtils.isNullOrEmpty(wechatRecommend.getRecOpenid())) {
			criteria.andEqualTo("recOpenid", wechatRecommend.getRecOpenid());
		}
		List<WechatRecommend> list = wechatRecommendDao.selectByExample(example);
		if(!CheckUtils.isNull(list)) {
			return list.get(list.size()-Constant.Num.INT_ONE);
		}
		return null;
	}

}
