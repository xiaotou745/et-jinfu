package com.etaofinance.entity.req;
/**
 *  忘记密码第一步
 * @author ofmyi_000
 *
 */
public class ForgetPwdOneReq {
private String loginName;
private String imgCode;
private String cookieKey;
public String getCookieKey() {
	return cookieKey;
}
public void setCookieKey(String cookieKey) {
	this.cookieKey = cookieKey;
}
public String getLoginName() {
	return loginName;
}
public void setLoginName(String loginName) {
	this.loginName = loginName;
}
public String getImgCode() {
	return imgCode;
}
public void setImgCode(String imgCode) {
	this.imgCode = imgCode;
}
}
