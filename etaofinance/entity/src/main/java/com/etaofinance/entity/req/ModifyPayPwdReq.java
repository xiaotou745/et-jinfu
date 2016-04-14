package com.etaofinance.entity.req;
/**
 *  修改支付密码第一步
 * @author ofmyi_000
 *
 */
public class ModifyPayPwdReq {
private Long userId;
private String passWord;
private String checkKey;

public Long getUserId() {
	return userId;
}
public void setUserId(Long userId) {
	this.userId = userId;
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
