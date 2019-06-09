package com.xxx.wechat.admin.config;

import java.util.HashMap;
import java.util.List;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.xxx.wechat.common.constant.Constant;
import com.xxx.wechat.core.dao.AdminUserDao;
import com.xxx.wechat.core.dao.AdminAuthorityDao;
import com.xxx.wechat.core.dao.entity.AdminAuthority;
import com.xxx.wechat.core.dao.entity.AdminRoleAuthorityRel;
import com.xxx.wechat.core.dao.entity.AdminUser;
import com.xxx.wechat.core.dao.entity.extend.AdminAuthorityExt;

/**
 * Created by Administrator on 2017/12/11. 自定义权限匹配和账号密码匹配
 */
public class MyShiroRealm extends AuthorizingRealm {

	@Autowired
	private AdminUserDao accountMapper;

	@Autowired
	private AdminAuthorityDao adminAuthorityDao;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

		// 获取登录用户名
		String account = (String) principals.getRealmNames().iterator().next();;
		// 查询用户信息
		AdminUser accountBean = accountMapper.selectById(account);
		// 根据用户名查询权限
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		List<AdminAuthorityExt> authoritys = adminAuthorityDao.selectByRoleId(accountBean.getRoleId());
		// 没有权限
		if (null == authoritys || authoritys.size() == Constant.Num.INT_ZERO) {
			return null;
		}
		for (AdminAuthorityExt authority : authoritys) {
			authorizationInfo.addStringPermission(authority.getAuthorityCode());
		}
		return authorizationInfo;
	}

	/* 主要是用来进行身份认证的，也就是说验证用户输入的账号和密码是否正确。 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken upToken = (UsernamePasswordToken) token;
		String username = upToken.getUsername();
		String password = String.valueOf(upToken.getPassword());

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("account", username);
		map.put("password", password);
		AdminUser adminUser = new AdminUser();
		adminUser.setAdminId(username);
		adminUser.setPassword(password);
		AdminUser account = accountMapper.selectOne(adminUser);
		if (account == null) {
			throw new NullPointerException();
		}
		if (account.getDeleteFlag().equals("1")) { // 账户冻结
			throw new LockedAccountException();
		}
		if (!account.getPassword().equals(password.trim())) {
			throw new AuthenticationException();
		}
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(account, account.getPassword(),
				account.getAdminId());
		return authenticationInfo;
	}

}
