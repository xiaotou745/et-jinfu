package com.etaofinance.core.enums;

public enum MemberEnum {
	 
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
	 * 获取用户信息失败
	 */
	GetUserErr(-3,"获取用户信息失败"),
	
	/**
	 * 用户名已存在
	 */
	UserNameIsExist(-4,"此用户名已存在");

	private int value = 0;
	private String desc;
	private MemberEnum(int value, String desc) { // 必须是private的，否则编译错误
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
