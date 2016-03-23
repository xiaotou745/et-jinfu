package com.etaofinance.core.enums;

public enum SendCodeType {
	/**
	 * 注册
	 */
	Register(1, "注册"),
	/**
	 * 修改密码
	 */
	UpdatePasswrd(2, "修改密码"),
	/**
	 * 忘记密码
	 */
	ForgetPassword(3, "忘记密码");
	private int value = 0;
	private String desc;
	private SendCodeType(int value, String desc) { 
		this.value = value;
		this.desc = desc;
	}
	public int value() {
		return this.value;
	}
	public String desc() {
		return this.desc;
	}

	public static SendCodeType getEnum(int index) {
		for (SendCodeType c : SendCodeType.values()) {
			if (c.value() == index) {
				return c;
			}
		}
		return null;
	}
}
