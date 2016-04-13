package com.etaofinance.entity.req;
/**
 *  找回密码第一步
 * @author ofmyi_000
 *
 */
public class ForgetPayPwdReq {
private Long userId;
private String phoneNo;
private String veriCode;
private String passWord;
private String checkKey;

public Long getUserId() {
	return userId;
}
public void setUserId(Long userId) {
	this.userId = userId;
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
public String getPassWord() {
	return passWord;
}
public void setPassWord(String passWord) {
	this.passWord = passWord;
}
public String getCheckKey() {
	return checkKey;
}
public void setCheckKey(String checkKey) {
	this.checkKey = checkKey;
}


}
