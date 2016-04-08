package com.etaofinance.wap.common;


import javax.servlet.http.HttpServletRequest;

import org.fusesource.hawtbuf.codec.VariableCodec;

import com.etaofinance.api.redis.RedisService;
import com.etaofinance.core.consts.RedissCacheKey;
import com.etaofinance.core.security.AES;
import com.etaofinance.core.util.CookieUtils;
import com.etaofinance.core.util.JsonUtil;
import com.etaofinance.core.util.PropertyUtils;
import com.etaofinance.core.util.SpringBeanHelper;
import com.etaofinance.entity.Member;
import com.etaofinance.entity.domain.SimpleUserInfoModel;

public class UserContext {
	private Member account;
	private String host="";
	private final static RedisService redisService;
	static {
		redisService = SpringBeanHelper.getCustomBeanByType(RedisService.class);
	}


	private UserContext(Member account,String host) {
		this.account = account;
		this.host = host;
	}
	
	public Member getUserInfo(){
		return this.account;
	}
	public String getHost(){
		return this.host;
	}

	public static  UserContext getCurrentContext(HttpServletRequest request) {
		final String cookieKey = LoginUtil.LOGIN_COOKIE_NAME;
		String cookieValue = CookieUtils.getCookie(request, cookieKey);
		if (cookieValue != null&&!cookieValue.isEmpty()) {
			String key=String.format(RedissCacheKey.LOGIN_COOKIE, cookieValue);
			Member account=null;
			String value= redisService.get(key, String.class);
			if(value!=null&&!value.equals(""))
			{
				account=JsonUtil.str2obj(value, Member.class);
			}
			if (account != null) {
				return new UserContext(account,request.getHeader("host"));
			}
		}
	    return new UserContext(null,request.getHeader("host"));
	}
	/**
	 * 用户是否在线
	 * @return
	 */
	public boolean IsOnline(){
		return this.account!=null;
	}

}
