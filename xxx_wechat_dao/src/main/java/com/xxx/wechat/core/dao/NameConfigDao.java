package com.xxx.wechat.core.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.xxx.wechat.core.BaseDao;
import com.xxx.wechat.core.dao.entity.NameConfig;

@Mapper
public interface NameConfigDao extends BaseDao<NameConfig> {

	List<NameConfig> list();
	
	Integer insertNameConfig(NameConfig nameConfig);
}
