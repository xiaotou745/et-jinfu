package com.etaofinance.admin.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.etaofinance.core.util.CookieUtils;

/**
 * 登录工具
 * @author pengyi
 * @date 20150828
 *
 */
public class LoginUtil {
	public final static String ADMIN_JSESSIONID = "etaofinance_JSESSIONID";
	public final static String LOGIN_COOKIE_NAME = "userinfo_etaofinance";
	public static boolean checkIsLogin(HttpServletRequest request, HttpServletResponse response){
		boolean isLogin= UserContext.getCurrentContext(request)!=null;
		if (!isLogin) {
			CookieUtils.deleteCookie(request, response,LoginUtil.LOGIN_COOKIE_NAME);
		}
		return isLogin;
	}
}
