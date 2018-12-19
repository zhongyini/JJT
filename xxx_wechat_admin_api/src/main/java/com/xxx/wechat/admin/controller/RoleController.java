package com.xxx.wechat.admin.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.Page;
import com.xxx.wechat.admin.BaseController;
import com.xxx.wechat.admin.RestResult;
import com.xxx.wechat.admin.auth.AuthRequired;
import com.xxx.wechat.admin.dto.BasePage;
import com.xxx.wechat.admin.service.IAuthorityService;
import com.xxx.wechat.admin.service.IRoleService;
import com.xxx.wechat.common.utils.CheckUtils;
import com.xxx.wechat.common.utils.DateUtils;
import com.xxx.wechat.constants.Constants;
import com.xxx.wechat.core.dao.entity.AdminAuthority;
import com.xxx.wechat.core.dao.entity.AdminRole;
import com.xxx.wechat.core.dao.entity.extend.AdminRoleExt;
import com.xxx.wechat.core.exception.AppException;
import com.xxx.wechat.helper.AuthorityHelper;

@RestController
@RequestMapping("/role")
public class RoleController extends BaseController {

	@Autowired
	private IRoleService roleService;
	@Autowired
	private IAuthorityService authorityService;

	@Autowired
	private AuthorityHelper authorityHelper;

	@AuthRequired(permission = "authority.role.list")
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public RestResult list(@RequestBody AdminRoleExt roleExt) {
		// 查询所有角色信息
		try {
			// 返回查询结果
			Page<AdminRole> list = roleService.searchAll(roleExt);
			return new RestResult(new BasePage(list.getTotal(),
					list.getResult()));
		} catch (AppException e) {
			// 返回异常信息
			return new RestResult(e.getMessage());
		}

	}

	@AuthRequired(permission = "authority.role.create")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public RestResult create(@RequestBody AdminRoleExt roleDto) {
		// 参数检测角色ID
		if (CheckUtils.isNullOrEmpty(roleDto.getRoleId())) {
			return new RestResult(messageHelper.mesg_info_1001);
		}
		if (!CheckUtils.checkPara(roleDto.getRoleId(),
				CheckUtils.LETTERORDIGIT, 20)) {
			return new RestResult(messageHelper.mesg_info_1001);
		}
		if (CheckUtils.isNullOrEmpty(roleDto.getRoleName())) {
			return new RestResult(messageHelper.mesg_info_1002);
		}
		if (!CheckUtils.checkPara(roleDto.getRoleName(),
				CheckUtils.CHINESE_LETTER_DIGIT, 40)) {
			return new RestResult(messageHelper.mesg_info_1002);
		}

		if (CheckUtils.isNullOrEmpty(roleDto.getPermissions())) {
			return new RestResult(messageHelper.mesg_info_1005);
		}
		
		Timestamp tt = DateUtils.getNowTimestamp();
		AdminRole role = null;
		AdminRole old = null;
		try {
			old = roleService.detail(roleDto.getRoleId());
		} catch (Exception e) {
			// 返回异常信息
			return new RestResult(e.getMessage());
		}
		if (!CheckUtils.isNull(old)) {
			return new RestResult(messageHelper.mesg_info_0016);
		}
		try {
			// 角色信息初始化
			role = new AdminRole(roleDto.getRoleId(), roleDto.getRoleName());
			role.setCreateUser(getAdminId());
			role.setModifyUser(getAdminId());
			role.setCreateDate(tt);
			role.setModifyDate(tt);

			List<AdminAuthority> authorities = new ArrayList<AdminAuthority>();
			List<String> codes = roleDto.getPermissions();
			AdminAuthority authority = null;
			for (String code : codes) {
				authority = new AdminAuthority();
				authority.setAuthorityCode(code);
				authority.setRoleId(roleDto.getRoleId());
				authorities.add(authority);
			}
			if (authorityHelper.hasPermission(Constants.APP_AUTHORITY,
					authorities)) {
				authority = new AdminAuthority();
				authority.setAuthorityCode(Constants.APP_AUTHORITY);
				authority.setRoleId(roleDto.getRoleId());
				authorities.add(authority);
			}
			if (authorityHelper
					.hasPermission(Constants.APP_REPORT, authorities)) {
				authority = new AdminAuthority();
				authority.setAuthorityCode(Constants.APP_REPORT);
				authority.setRoleId(roleDto.getRoleId());
				authorities.add(authority);
			}
			if (authorityHelper
					.hasPermission(Constants.APP_SEARCH, authorities)) {
				authority = new AdminAuthority();
				authority.setAuthorityCode(Constants.APP_SEARCH);
				authority.setRoleId(roleDto.getRoleId());
				authorities.add(authority);
			}
			if (authorityHelper.hasPermission(Constants.APP_WX, authorities)) {
				authority = new AdminAuthority();
				authority.setAuthorityCode(Constants.APP_WX);
				authority.setRoleId(roleDto.getRoleId());
				authorities.add(authority);
			}
			int ret = roleService.create(role, authorities);
			
			if (ret > Constants.SUCCESS) {
				authorityHelper.reLoad();
				RestResult rett = new RestResult();
				rett.setMessage(messageHelper.mesg_info_0001);
				return rett;
			} else {
				return new RestResult(messageHelper.mesg_error_0001);
			}

		} catch (AppException e) {
			return new RestResult(e.getMessage());
		}

	}

	@AuthRequired(permission = "authority.role.update")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public RestResult update(@RequestBody AdminRoleExt roleDto) {

		if (CheckUtils.isNullOrEmpty(roleDto.getRoleId())) {
			return new RestResult(messageHelper.mesg_info_1001);
		}
		if (!CheckUtils.checkPara(roleDto.getRoleId(),
				CheckUtils.LETTERORDIGIT, 20)) {
			return new RestResult(messageHelper.mesg_info_1001);
		}
		if (CheckUtils.isNullOrEmpty(roleDto.getRoleName())) {
			return new RestResult(messageHelper.mesg_info_1002);
		}
		if (!CheckUtils.checkPara(roleDto.getRoleName(),
				CheckUtils.CHINESE_LETTER_DIGIT, 40)) {
			return new RestResult(messageHelper.mesg_info_1002);
		}

		if (CheckUtils.isNullOrEmpty(roleDto.getPermissions())) {
			return new RestResult(messageHelper.mesg_info_1003);
		}

		AdminRole role = null;
		try {
			// 角色信息初始化
			role = new AdminRole(roleDto.getRoleId(), roleDto.getRoleName());

			role.setCreateDate(roleDto.getCreateDate());
			role.setCreateUser(roleDto.getCreateUser());
			role.setModifyDate(roleDto.getModifyDate());
			role.setModifyUser(getAdminId());
			List<AdminAuthority> authorities = new ArrayList<AdminAuthority>();
			List<String> codes = roleDto.getPermissions();
			AdminAuthority authority = null;
			for (String code : codes) {
				authority = new AdminAuthority();
				authority.setAuthorityCode(code);
				authority.setRoleId(roleDto.getRoleId());
				//如果存在code就进行更新
				authorities.add(authority);
			}
			if (!authorityHelper.hasPermission(Constants.APP_AUTHORITY,
					authorities)) {
				authority = new AdminAuthority();
				authority.setAuthorityCode(Constants.APP_AUTHORITY);
				authority.setRoleId(roleDto.getRoleId());
				authorities.add(authority);
			}
			if (!authorityHelper
					.hasPermission(Constants.APP_REPORT, authorities)) {
				authority = new AdminAuthority();
				authority.setAuthorityCode(Constants.APP_REPORT);
				authority.setRoleId(roleDto.getRoleId());
				authorities.add(authority);
			}
			if (!authorityHelper
					.hasPermission(Constants.APP_SEARCH, authorities)) {
				authority = new AdminAuthority();
				authority.setAuthorityCode(Constants.APP_SEARCH);
				authority.setRoleId(roleDto.getRoleId());
				authorities.add(authority);
			}
			if (!authorityHelper.hasPermission(Constants.APP_WX, authorities)) {
				authority = new AdminAuthority();
				authority.setAuthorityCode(Constants.APP_WX);
				authority.setRoleId(roleDto.getRoleId());
				authorities.add(authority);
			}
			int ret = roleService.update(role, authorities);
			if (ret > Constants.SUCCESS) {
				authorityHelper.reLoad();
				RestResult rett = new RestResult();
				rett.setMessage(messageHelper.mesg_info_0003);
				return rett;
			} else {
				return new RestResult(messageHelper.mesg_error_0001);
			}

		} catch (AppException e) {
			return new RestResult(e.getMessage());
		}

	}

	@AuthRequired(permission = "authority.role.update")
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public RestResult detail(String id) {

		AdminRole role = null;
		try {
			role = roleService.detail(id);
			if (CheckUtils.isNull(role)) {
				return new RestResult(messageHelper.mesg_error_0001);
			}

			AdminRoleExt roleDto = new AdminRoleExt();
			roleDto.setRoleId(role.getRoleId());
			roleDto.setRoleName(role.getRoleName());
			roleDto.setCreateDate(role.getCreateDate());
			roleDto.setCreateUser(role.getCreateUser());
			roleDto.setModifyDate(role.getModifyDate());
			roleDto.setModifyUser(role.getModifyUser());

			roleDto.setPermissions(authorityService
					.searchAuthorityCodeByRoleId(id));
			return new RestResult(roleDto);
		} catch (AppException e) {
			return new RestResult(e.getMessage());
		}
	}

	@AuthRequired(permission = "authority.role.delete")
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public RestResult delete(@RequestBody AdminRole role) {
		try {
			
			//删除角色
			// 角色信息初始化
			int ret = roleService.delete(role);
			if (ret > Constants.SUCCESS) {

				return new RestResult(Constants.SUCCESS,
						messageHelper.mesg_info_0002);
			} else {
				return new RestResult(messageHelper.mesg_info_0006);
			}

		} catch (AppException e) {
			return new RestResult(e.getMessage());
		}

	}
}
