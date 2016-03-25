package com.etaofinance.core.enums;

public enum MemberStatusEnum {
	Invalid(0,"无效"),
	
	Valid(1,"有效");
	
	private int value = 0;
	private String desc;

	private MemberStatusEnum(int value, String desc) { // 必须是private的，否则编译错误
		this.value = value;
		this.desc = desc;
	}

	public int value() {
		return this.value;
	}

	public String desc() {
		return this.desc;
	}

	public static MemberStatusEnum getEnum(int index) {
		for (MemberStatusEnum c : MemberStatusEnum.values()) {
			if (c.value() == index) {
				return c;
			}
		}
		return null;
	}
}
