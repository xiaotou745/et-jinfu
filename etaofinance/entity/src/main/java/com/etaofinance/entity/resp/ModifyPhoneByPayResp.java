package com.etaofinance.entity.resp;
/**
 * 修改手机号
 * 第1，2步返回值
 * @author ofmyi_000
 *
 */
public class ModifyPhoneByPayResp {
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
