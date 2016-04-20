package com.etaofinance.core.enums;

public enum WithdrawformEnum {
	 
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
	 * 未绑定银行卡
	 */
	BindCardIsNull(-3,"未绑定银行卡"),	
	/**
	 * Id为空
	 */
	IdIsNull(-100,"Id为空");

	private int value = 0;
	private String desc;
	private WithdrawformEnum(int value, String desc) { // 必须是private的，否则编译错误
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
