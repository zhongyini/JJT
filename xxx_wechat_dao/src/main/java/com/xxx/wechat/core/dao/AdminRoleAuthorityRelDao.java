package com.xxx.wechat.core.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.xxx.wechat.core.BaseDao;
import com.xxx.wechat.core.dao.entity.AdminRoleAuthorityRel;

@Mapper
public interface AdminRoleAuthorityRelDao extends BaseDao<AdminRoleAuthorityRel> {

	List<AdminRoleAuthorityRel> selectByRoleId(String roleId);
}
