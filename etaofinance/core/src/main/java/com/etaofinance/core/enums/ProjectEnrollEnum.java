package com.etaofinance.core.enums;

public enum ProjectEnrollEnum {
	 
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
	 * 标题不能为空
	 */
	TitleIsNull(-3,"标题不能为空"),
	
	/**
	 * 标题不能超过15个字符
	 */
	TitleIsLong(-4,"标题不能超过15个字符"),
	
	/**
	 * 行业不能为空
	 */
	OwnedIndustryIsNull(-5,"行业不能为空"),
	/**
	 * 联系人不能为空
	 */
	ContactsIsNull(-6,"联系人不能为空"),
	
	/**
	 * 联系人长度必须在2-10个字符
	 */
	ContactsIsErr(-7,"联系人长度必须在2-10个字符"),
	
	/**
	 * 电话不能为空
	 */
	PhoneNoIsNull(-8,"电话不能为空"),
	
	/**
	 * 电话不能超过12个字符
	 */
	PhoneNoIsErr(-9,"电话不能超过12个字符"),
	
	/**
	 * 邮箱不能为空	
	 */
	EMailIsNull(-10,"邮箱不能为空"),
	
	/**
	 * 邮箱不能超过30个字符	
	 */
	EMailIsErr(-11,"邮箱不能超过30个字符");

	private int value = 0;
	private String desc;
	private ProjectEnrollEnum(int value, String desc) { // 必须是private的，否则编译错误
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
