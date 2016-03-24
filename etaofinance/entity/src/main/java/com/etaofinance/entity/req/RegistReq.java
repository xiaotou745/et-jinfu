package com.etaofinance.entity.req;

public class RegistReq {

	private String phoneNo;
	private String pwd;
	private String veriCode;
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getVeriCode() {
		return veriCode;
	}
	public void setVeriCode(String veriCode) {
		this.veriCode = veriCode;
	}
}
