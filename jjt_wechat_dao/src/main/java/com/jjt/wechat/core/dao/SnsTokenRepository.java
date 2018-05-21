package com.jjt.wechat.core.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jjt.wechat.core.dao.entity.SnsToken;
@Repository
public interface SnsTokenRepository extends JpaRepository<SnsToken, Long>{
	/**
	 * 获取最新的Token
	 * @return
	 */
	SnsToken findFirstByOrderByCreateDateDesc();
}
