package com.etaofinance.entity.req;

public class SendCodeReq {
	private String phoneNo;//账号
	private int type;//类型 1注册 2修改密码 3忘记密码
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
}
