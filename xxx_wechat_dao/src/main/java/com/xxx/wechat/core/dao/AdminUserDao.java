package com.xxx.wechat.core.dao;

import org.apache.ibatis.annotations.Mapper;

import com.xxx.wechat.core.BaseDao;
import com.xxx.wechat.core.dao.entity.AdminUser;

@Mapper
public interface AdminUserDao extends BaseDao<AdminUser>{
	
	AdminUser selectById(String adminId);
	
	AdminUser login(AdminUser adminUser);
}
