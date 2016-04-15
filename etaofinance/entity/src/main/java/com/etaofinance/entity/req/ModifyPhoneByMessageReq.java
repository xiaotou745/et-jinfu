package com.etaofinance.entity.req;
/**
 * 修改手机号第二步
 * @author ofmyi_000
 *
 */
public class ModifyPhoneByMessageReq {
	private String phoneNo;
	private String veriCode;
	private String checkKey;
	private Long userId;
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getCheckKey() {
		return checkKey;
	}
	public void setCheckKey(String checkKey) {
		this.checkKey = checkKey;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getVeriCode() {
		return veriCode;
	}
	public void setVeriCode(String veriCode) {
		this.veriCode = veriCode;
	}
}
