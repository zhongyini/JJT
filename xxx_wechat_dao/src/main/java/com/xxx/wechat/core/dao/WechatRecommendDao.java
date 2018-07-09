package com.xxx.wechat.core.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.xxx.wechat.core.BaseDao;
import com.xxx.wechat.core.dao.entity.WechatRecommend;
import com.xxx.wechat.core.dao.entity.extend.WechatRecommendExt;

@Mapper
public interface WechatRecommendDao extends BaseDao<WechatRecommend> {

	public List<WechatRecommendExt> selectRecommendListByRecOpenid(String recOpenid);
	
	public List<WechatRecommendExt> selectBrotherListByRecEdOpenid(String recEdOpenid);
	
	public Integer selectRecNumByRecOpenid(String recOpenid);
	
	public List<WechatRecommend> selectRecListByRecEdOpenid(String recEdOpenid);
	
	public List<WechatRecommend> referrer(String recEdOpenId);
	
	public Integer selectAmountCashByRecOpenid(String openid);
	
}
