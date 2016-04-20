package com.etaofinance.core.enums;

public enum MemberTypeEnum {
	 
	CommonUser(0,"普通用户"),
	
	LeadInvestUser(3,"领投人"),
	
	FollowInvestUser(2,"跟投人"),
	
	CertificationUser(1,"实名认证用户");
	
	private int value = 0;
	private String desc;

	private MemberTypeEnum(int value, String desc) { // 必须是private的，否则编译错误
		this.value = value;
		this.desc = desc;
	}

	public int value() {
		return this.value;
	}

	public String desc() {
		return this.desc;
	}

	public static MemberTypeEnum getEnum(int index) {
		for (MemberTypeEnum c : MemberTypeEnum.values()) {
			if (c.value() == index) {
				return c;
			}
		}
		return null;
	}
}
