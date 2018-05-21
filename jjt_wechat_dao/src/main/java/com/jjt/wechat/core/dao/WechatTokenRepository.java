package com.jjt.wechat.core.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jjt.wechat.core.dao.entity.WechatToken;
@Repository
public interface WechatTokenRepository extends JpaRepository<WechatToken, Long>{
	/**
	 * 获取最新的Token
	 * @return
	 */
	WechatToken findFirstByOrderByCreateDateDesc();
}
