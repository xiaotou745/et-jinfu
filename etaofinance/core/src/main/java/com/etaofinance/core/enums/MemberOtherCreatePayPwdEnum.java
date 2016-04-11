package com.etaofinance.core.enums;

public enum MemberOtherCreatePayPwdEnum {
	 
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
	 * 支付密码为空
	 */
	PayPassWordIsNull(-3,"支付密码为空"),	
	/**
	 * 实名认证后才可能设置支付密码
	 */
	LevelIsErr(-4,"实名认证后才可能设置支付密码"),;

	private int value = 0;
	private String desc;
	private MemberOtherCreatePayPwdEnum(int value, String desc) { // 必须是private的，否则编译错误
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
