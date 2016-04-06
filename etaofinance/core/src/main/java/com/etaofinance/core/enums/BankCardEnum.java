package com.etaofinance.core.enums;

public enum BankCardEnum {
	 
	/**
	 * 成功
	 */
	Success(1, "成功！"),
	
	/**
	 * 错误
	 */
	Err(-1,"错误"),
	
	/**
	 * 用户不存在
	 */
	MemberIdIsNull(-2,"用户不存在"),
	
	/**
	 * 银行为空
	 */
	BankIdIsNull(-3,"银行为空"),
	
	/**
	 * 银行名称为空
	 */
	BankNameIsNull(-4,"银行名称为空"),
	/**
	 * 卡号为空"
	 */
	CardNoIsNull(-5,"卡号为空"),
	
	/**
	 * 持卡人为空
	 */
	CardNameIsNull(-6,"持卡人为空"),
	
	/**
	 * 卡号校验失败
	 * 不能超过19位数字
	 */
	CardNoIsErr(-7,"卡号校验失败"),
	/**
	 * Id为空
	 */
	IdIsNull(-100,"Id为空");

	private int value = 0;
	private String desc;
	private BankCardEnum(int value, String desc) { // 必须是private的，否则编译错误
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
