package com.xxx.wechat.core.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.xxx.wechat.core.BaseDao;
import com.xxx.wechat.core.dao.entity.AdminAuthority;

@Mapper
public interface AuthorityDao extends BaseDao<AdminAuthority> {

	List<AdminAuthority> selectByRoleId(String roleId);
}
