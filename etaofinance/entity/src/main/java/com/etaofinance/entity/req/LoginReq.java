package com.etaofinance.entity.req;
/**
 * wap登录实体类
 * @author ofmyi_000
 *
 */
public class LoginReq {
	private String loginName;
	private String pwd;
	private String remberMe;
	private String reUrl;
	public String getReUrl() {
		return reUrl;
	}
	public void setReUrl(String reUrl) {
		this.reUrl = reUrl;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getRemberMe() {
		return remberMe;
	}
	public void setRemberMe(String remberMe) {
		this.remberMe = remberMe;
	}

}
