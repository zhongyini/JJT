package com.jjt.wechat.core.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jjt.wechat.core.dao.entity.WechatUser;
@Repository
public interface UserRepository extends JpaRepository<WechatUser, String> {
	
	WechatUser findByOpenId(String openId);

}
