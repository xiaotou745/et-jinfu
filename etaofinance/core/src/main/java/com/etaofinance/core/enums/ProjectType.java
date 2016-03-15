package com.etaofinance.core.enums;

public enum ProjectType {
	RobustType (1,"稳健型"),

	RiskSharingType(2,"风险共担型");
    
	private int value = 0;
	private String desc;
	private ProjectType(int value, String desc) { // 必须是private的，否则编译错误
		this.value = value;
		this.desc = desc;
	}
	public int value() {
		return this.value;
	}
	public String desc() {
		return this.desc;
	}

	public static ProjectType getEnum(int index) {
		for (ProjectType c : ProjectType.values()) {
			if (c.value() == index) {
				return c;
			}
		}
		return null;
	}
}
