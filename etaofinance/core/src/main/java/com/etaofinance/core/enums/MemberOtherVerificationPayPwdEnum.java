package com.etaofinance.core.enums;

public enum MemberOtherVerificationPayPwdEnum {
	 
	/**
	 * 成功
	 */
	Success(1, "成功！"),
	
	/**
	 * 错误
	 */
	Err(-1,"错误"),
	
	/**
	 * 会员Id不存在
	 */
	MemberIdIsNull(-2,"会员Id不存在"),
	
	/**
	 * 支付密码错误,请重试
	 */
	PayPassWordErr(-3,"支付密码错误,请重试");

	private int value = 0;
	private String desc;
	private MemberOtherVerificationPayPwdEnum(int value, String desc) { // 必须是private的，否则编译错误
		this.value = value;
		this.desc = desc;
	}
	public int value() {
		return this.value;
	}
	public String desc() {
		return this.desc;
	}

	public static QAEnum getEnum(int index) {
		for (QAEnum c : QAEnum.values()) {
			if (c.value() == index) {
				return c;
			}
		}
		return null;
	}
}
