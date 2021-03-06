package com.etaofinance.admin.common;


import javax.servlet.http.HttpServletRequest;

import com.etaofinance.core.security.AES;
import com.etaofinance.core.util.CookieUtils;
import com.etaofinance.core.util.JsonUtil;
import com.etaofinance.core.util.PropertyUtils;
import com.etaofinance.core.util.SpringBeanHelper;
import com.etaofinance.entity.domain.SimpleUserInfoModel;

public class UserContext {
	private SimpleUserInfoModel account;
	private String host="";
//	private final static IMenuInfoService authorityMenuClassService;
//	static {
//		authorityMenuClassService = SpringBeanHelper
//				.getCustomBeanByType(IMenuInfoService.class);
//	}

	private UserContext(SimpleUserInfoModel account,String host) {
		this.account = account;
		this.host = host;
	}

	/**
	 * 判断当前登录用户是否有给定菜单的权限
	 * 
	 * @author hailongzhao
	 * @date 20150828
	 * @param menuID
	 * @return
	 */
	public boolean isHasAuth(int menuID) {
		return true;
		//return authorityMenuClassService.checkHasAuth(account.getId(), menuID);
	}
	/**
	 * 判断当前用户是否有某个AuthCode权限
	 * 茹化肖
	 * @param authCode
	 * @return
	 */
	public boolean isHasAuthByCode(String authCode) {
		return true;
		//return authorityMenuClassService.checkHasAuthByCode(account.getId(), authCode);
	}
	public int getId() {
		return account.getId();
	}


	public String getLoginName() {
		return account.getLoginName();
	}
	public String getUserName() {
		return account.getUserName();
	}
	public int getRoleId()
	{
		return account.getRoleId();
	}
	public static  UserContext getCurrentContext(HttpServletRequest request) {
		final String cookieKey = LoginUtil.LOGIN_COOKIE_NAME;
		String cookieValue = CookieUtils.getCookie(request, cookieKey);
		if (cookieValue != null&&!cookieValue.isEmpty()) {
			String edcrCookie=AES.aesDecrypt(cookieValue);
			SimpleUserInfoModel account = JsonUtil.str2obj(edcrCookie,SimpleUserInfoModel.class);
			if (account != null&&
				account.getUserName()!=null&&
				!account.getUserName().isEmpty()&&
				account.getLoginName()!=null&&
				!account.getLoginName().isEmpty()) {
				return new UserContext(account,request.getHeader("host"));
			}
		}
		return null;
	}
	/**
	 * 登录来源，0表示从net版后台登录，1表示从java版后台登录
	 * @author hailongzhao
	 * @date 20150916
	 * @param loginfrom
	 */
	public  int getLoginFrom() {
		String staticUrl=PropertyUtils.getProperty("java.admin.url");
		int index=staticUrl.indexOf(".");
		if (index>0) {
			String webDomain=staticUrl.substring(staticUrl.indexOf("."));
			if (host!=null&&host.indexOf(webDomain)>0) {
				return 0;
			}
		}
		return 1;
	}
}
