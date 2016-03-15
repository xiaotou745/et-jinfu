package com.etaofinance.core.enums;

public enum ProjectStatus {
    NotOnLine(0,"未上线"),

    Preheating (1,"预热中"),

    Financeing(2,"融资中"),

    Success(3,"融资成功"),

    Failure(4,"融资失败");
    
	private int value = 0;
	private String desc;
	private ProjectStatus(int value, String desc) { // 必须是private的，否则编译错误
		this.value = value;
		this.desc = desc;
	}
	public int value() {
		return this.value;
	}
	public String desc() {
		return this.desc;
	}

	public static ProjectStatus getEnum(int index) {
		for (ProjectStatus c : ProjectStatus.values()) {
			if (c.value() == index) {
				return c;
			}
		}
		return null;
	}
}
