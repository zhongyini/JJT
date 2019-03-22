package com.xxx.wechat.admin.controller;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.xxx.wechat.admin.BaseController;
import com.xxx.wechat.admin.config.JwtConfig;
import com.xxx.wechat.admin.dto.LoginReq;
import com.xxx.wechat.admin.dto.LoginResp;
import com.xxx.wechat.admin.dto.RestResult;
import com.xxx.wechat.admin.enums.AdminStatus;
import com.xxx.wechat.admin.service.IAdminService;
import com.xxx.wechat.admin.service.IAuthorityService;
import com.xxx.wechat.common.utils.CheckUtils;
import com.xxx.wechat.config.AppConfig;
import com.xxx.wechat.constants.Constants;
import com.xxx.wechat.core.dao.entity.AdminUser;
import com.xxx.wechat.core.exception.AppException;

@RestController
public class LoginController extends BaseController {

	@Autowired
	private IAdminService adminService;

	@Autowired
	private IAuthorityService authorityService;

	@Autowired
	private JwtConfig tokenHelper;

	@Autowired
	protected AppConfig appConfig;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public RestResult getLogin(String name, String password, HttpServletResponse response) {
		// 登录验证
		AdminUser admin = new AdminUser(name, password);
		return adminService.login(admin);
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public RestResult login(@RequestBody LoginReq loginReq, HttpServletResponse response) {
		// 登录画面用户ID输入check
		if (CheckUtils.isNullOrEmpty(loginReq.getName())) {
			return new RestResult(messageHelper.mesg_info_0101);
		}
		// 登录画面用户ID格式check
		if (!CheckUtils.checkParas(loginReq.getName(), CheckUtils.CHINESE_LETTER_DIGIT, 20)) {
			return new RestResult(messageHelper.mesg_info_0102);
		}
		// 登录画面 密码输入 check
		if (CheckUtils.isNullOrEmpty(loginReq.getPassword())) {
			return new RestResult(messageHelper.mesg_info_0103);
		}
		LoginResp resp = null;
		// 判断是否为初始化密码,如果不是初始密码，check密码格式
		if (!appConfig.defaultPwd.equals(loginReq.getPassword())) {
			// 登录画面 密码格式 check
			if (CheckUtils.checkParas(loginReq.getPassword(), CheckUtils.PWD, 20)) {
				return new RestResult(messageHelper.mesg_info_0104);
			}
		}

		// 登录验证
		AdminUser admin = new AdminUser(loginReq.getName(), loginReq.getPassword());
		try {
			admin = new AdminUser();//adminService.login(admin);
			resp = new LoginResp();
			if (AdminStatus.OVERDUE.getStatus().equals(admin.getDeleteFlag())) {                  
				// 将查询到的管理者存入登录响应对象中，传到过期密码更新画面
				resp.setAdmin(admin);
				resp.setPasswordOverdue(true);
				RestResult restResult = new RestResult();
				restResult.setResult(resp);
				return restResult;
			}
			// 登录成功，处理单机登录，判断是否踢出该账户之前登录的session
			HttpSession session = request.getSession();
			String token = tokenHelper.createJWT(admin);
			response.addHeader("x-access-token", token);
			resp.setAdmin(admin);
			// 设置用户权限
			// 将用户信息放入session中
			session.setAttribute(Constants.USER, admin);
			session.getServletContext().setAttribute(Constants.USER, admin);
			resp.setPermissions(authorityService.searchAuthorityCodeByRoleId(admin.getRoleId()));
		} catch (AppException e) {
			return new RestResult(e.getMessage());
		}

		return new RestResult(messageHelper.mesg_info_0109, resp);
	}

	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public RestResult logout() {
		// 清除session
		request.getSession().removeAttribute(Constants.USER);
		return new RestResult();
	}

}
