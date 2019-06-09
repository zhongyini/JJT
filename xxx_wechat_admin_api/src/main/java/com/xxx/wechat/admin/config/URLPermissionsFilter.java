package com.xxx.wechat.admin.config;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.xxx.wechat.admin.enums.AdminStatus;
import com.xxx.wechat.common.constant.Constant;
import com.xxx.wechat.common.utils.CheckUtils;
import com.xxx.wechat.core.dao.AdminAuthorityDao;
import com.xxx.wechat.core.dao.entity.AdminAuthority;
import com.xxx.wechat.core.dao.entity.AdminUser;
import com.xxx.wechat.core.dao.entity.extend.AdminAuthorityExt;

/**
 * 自定义url拦截
 *
 */
public class URLPermissionsFilter extends AuthorizationFilter {
	
	private static Log log = LogFactory.getLog(URLPermissionsFilter.class);

	protected String unauthorizedUrl;
	private static final String LOGIN = "login";
	private static final String PERMS = "perms";
	protected static final String DEFAULT_ENCODING = "UTF-8";
	protected static final String DEFAULT_CONTENT_TYPE = "application/json";
	
	@Autowired
	protected JwtConfig jwtConfig;

	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
			throws Exception {
		// 获取用户信息
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		String token=httpRequest.getHeader(Constant.Str.HIS_TOKEN);
		if(StringUtils.isNotBlank(token)){
			if (CheckUtils.isNull(jwtConfig)) {
				jwtConfig = new JwtConfig();
			}
			AdminUser accountInfo = jwtConfig.parseJWT(token);
			// 需要登录
			if (CheckUtils.isNull(accountInfo)) {
				request.setAttribute(LOGIN, LOGIN);
				return false;
			}

			// roleId 为 1 直接 跳过 (所有权限)
			String roleId = String.valueOf(Constant.Num.INT_ONE);
			if (roleId.compareTo(accountInfo.getRoleId()) == Constant.Num.INT_ZERO) {
				return true;
			}
			String[] authorityArray = (String[]) mappedValue;
			if (authorityArray == null || authorityArray.length == 0) { // 无权限访问
				return false;
			}
			AdminAuthorityDao adminAuthorityDao = SpringUtil.getBean(AdminAuthorityDao.class);
			List<AdminAuthorityExt> authoritys = adminAuthorityDao.selectByRoleId(accountInfo.getRoleId());
			// 没有权限
			if (null == authoritys || authoritys.size() == Constant.Num.INT_ZERO) {
				return false;
			}
			List<String> list = new ArrayList<>();
			for (AdminAuthority authority : authoritys) {
				if (null != authority) {
					if (StringUtils.isNotBlank(authority.getAuthorityCode())) {
						list.add(authority.getAuthorityCode());
					}
				}
	
			}
			for (int i = 0; i < authorityArray.length; i++) {
				// 若当前用户是authorityArray中的任何一个，则有权限访问
				if (list.contains(authorityArray[i])) {
					return true;
				}
			}
		}
		request.setAttribute(PERMS, PERMS);
		return false;
	}
		

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response)
			throws UnsupportedEncodingException, IOException {
		log.debug("=======>onAccessDenied :" + this.getUnauthorizedUrl());
		// check 是否登录 或是否有权限
		if (!check(request)) {
			Writer writer = new OutputStreamWriter(response.getOutputStream(), DEFAULT_ENCODING);
			try {
				response.setContentType(DEFAULT_CONTENT_TYPE);

				Map<String, Object> map = new HashMap<>();
				// 未登录
				if (null != request.getAttribute(LOGIN) && LOGIN.equals(request.getAttribute(LOGIN))) {
					map.put("resultCode", AdminStatus.OVERDUE.getStatus());
					map.put("resultMsg", "未登录");
				}
				// 权限
				if (null != request.getAttribute(PERMS) && PERMS.equals(request.getAttribute(PERMS))) {
					map.put("resultCode", AdminStatus.NORMAL.getStatus());
					map.put("resultMsg", "权限不足");
				}
				String resultStr = JSONObject.toJSONString(map);
				response.setContentLength(resultStr.getBytes().length);
				writer.write(resultStr);
			} finally {
				writer.close();
			}
			return false;
		}
		return true;
	}

	/***
	 * check 是否登录 或是否有权限
	 * 
	 * @param request
	 * @return
	 */
	private boolean check(ServletRequest request) {
		// 未登录
		if (null != request.getAttribute(LOGIN) && LOGIN.equals(request.getAttribute(LOGIN))) {
			return false;
		}
		// 权限
		if (null != request.getAttribute(PERMS) && PERMS.equals(request.getAttribute(PERMS))) {
			return false;
		}
		return true;
	}

	public String getUnauthorizedUrl() {
		return unauthorizedUrl;
	}

	public void setUnauthorizedUrl(String unauthorizedUrl) {
		this.unauthorizedUrl = unauthorizedUrl;
	}

}
