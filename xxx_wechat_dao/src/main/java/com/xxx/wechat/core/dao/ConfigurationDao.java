package com.xxx.wechat.core.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.xxx.wechat.core.BaseDao;
import com.xxx.wechat.core.dao.entity.Configuration;

@Mapper
public interface ConfigurationDao extends BaseDao<Configuration>{
	
	List<Configuration> selectList();
}
