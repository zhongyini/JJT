package com.xxx.wechat.front.service;

import java.util.List;

import com.github.pagehelper.Page;
import com.xxx.wechat.core.dao.entity.WechatRecommend;
import com.xxx.wechat.core.dao.entity.extend.WechatRecommendExt;
import com.xxx.wechat.core.exception.AppException;

public interface IWechatRecommendService {

	/**
	 * 查询用户推荐的用户信息
	 * @param openid
	 * @return
	 * @throws AppException
	 */
	Page<WechatRecommendExt> selectRecommendListByRecOpenid(String recOpenid, int pageNum, int pageSize) throws AppException;
	
	/**
	 * 查询兄弟节点
	 * @param recEdOpenid
	 * @return
	 * @throws AppException
	 */
	Page<WechatRecommendExt> selectBrotherListByRecEdOpenid(String recEdOpenid, int pageNum, int pageSize) throws AppException;
	
	/**
	 * 添加推荐信息
	 * @param wechatRecommend
	 * @return
	 * @throws AppException
	 */
	int addWechatRecommend(WechatRecommend wechatRecommend) throws AppException;
	
	/**
	 * 查询用户推荐数
	 * @param openid
	 * @return
	 * @throws AppException
	 */
	Integer selectRecNumByRecOpenid(String openid) throws AppException;
	
	/**
	 * 根据被推荐者openid和二维码类型查询推荐信息
	 * @param openid 被推荐者openid
	 * @return
	 * @throws AppException
	 */
	List<WechatRecommend> selectRecListByRecEdOpenid(String recEdOpenid) throws AppException;
	
	/**
	 * 根据推荐者和被推荐者查询
	 * @param wechatRecommend
	 * @return
	 * @throws AppException
	 */
	WechatRecommend selectByTwoOpenid(WechatRecommend wechatRecommend) throws AppException;
	
}
