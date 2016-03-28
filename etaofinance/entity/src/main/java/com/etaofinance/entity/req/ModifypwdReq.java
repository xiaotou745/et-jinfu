package com.etaofinance.entity.req;
/**
 * 修改密码
 * @author ofmyi_000
 *
 */
public class ModifypwdReq {

	private String oldPwd;
	private String newPwd;
	private Long userId;
	public String getOldPwd() {
		return oldPwd;
	}
	public void setOldPwd(String oldPwd) {
		this.oldPwd = oldPwd;
	}
	public String getNewPwd() {
		return newPwd;
	}
	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
}
