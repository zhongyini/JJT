package com.jjt.wechat.core.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jjt.wechat.core.dao.entity.User;
@Repository
public interface UserRepository extends JpaRepository<User, String> {
	
	User findByOpenId(String openId);

}
