package com.etaofinance.entity.resp;
/**
 * 修改支付密码
 * 第1，2步返回值
 * @author ofmyi_000
 *
 */
public class ModifyPayPwdResp {
private Long userID;
private String checkKey;
public Long getUserID() {
	return userID;
}
public void setUserID(Long userID) {
	this.userID = userID;
}
public String getCheckKey() {
	return checkKey;
}
public void setCheckKey(String checkKey) {
	this.checkKey = checkKey;
}
}
