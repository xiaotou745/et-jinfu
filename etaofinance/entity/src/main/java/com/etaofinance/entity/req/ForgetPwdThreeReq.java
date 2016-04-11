package com.etaofinance.entity.req;
/**
 * 找回密码第三部
 * @author ofmyi_000
 *
 */
public class ForgetPwdThreeReq {
	private Long userId;
	private String checkKey;
	public String getCheckKey() {
		return checkKey;
	}
	public void setCheckKey(String checkKey) {
		this.checkKey = checkKey;
	}
	private String newPwd;
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getNewPwd() {
		return newPwd;
	}
	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
	}
}
