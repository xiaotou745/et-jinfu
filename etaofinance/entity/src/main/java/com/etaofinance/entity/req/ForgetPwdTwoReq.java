package com.etaofinance.entity.req;
/**
 * 找回密码第二步
 * @author ofmyi_000
 *
 */
public class ForgetPwdTwoReq {

	private String phoneNo;
	private String veriCode;
	private String cacheKey;
	public String getCacheKey() {
		return cacheKey;
	}
	public void setCacheKey(String cacheKey) {
		this.cacheKey = cacheKey;
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
