package com.etaofinance.core.enums;

public enum MemberCertificationEnum {
	 
	/**
	 * 成功
	 */
	Success(1, "成功！"),
	
	/**
	 * 错误
	 */
	Err(-1,"错误"), 
	/**
	 * 用户账号为空值
	 */
	IdIsNULL(-2,"用户账号为空"), 
	/**
	 * 真实姓名为空
	 */
	TrueNameIsNULL(-3,"真实姓名为空"), 
	/**
	 * 身份证号为空
	 */
	IdCardIsNULL(-4,"身份证号为空")	;

	private int value = 0;
	private String desc;
	private MemberCertificationEnum(int value, String desc) { // 必须是private的，否则编译错误
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
