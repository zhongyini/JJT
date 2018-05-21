package com.jjt.wechat.core.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jjt.wechat.core.dao.entity.Configuration;

@Repository
public interface ConfigurationRepository extends JpaRepository<Configuration, Long>{
	
}
