package com.etaofinance.core.enums;

public enum SendCodeType {
	/**
	 * 注册
	 */
	Register(1, "注册"),
	/**
	 * 忘记密码
	 */
	ForgetPassword(2, "忘记密码"),
	/**
	 * 设置支付密码
	 */
	SetPayPassWord(3, "设置支付密码"),
	/**
	 * 找回支付密码
	 */
	FindPayPassWord(4, "找回支付密码"),
	/**
	 * 更换手机号
	 */
	ChangePhone(5, "找回支付密码"),
	/**
	 * 绑定新手机
	 */
	BindNewPhone(6, "绑定新手机");
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
