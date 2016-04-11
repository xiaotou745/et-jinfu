package com.etaofinance.core.enums;

public enum ProjectImageTypeEnum {
	
	Unknow(0,"未知"),
	
	ProjectBasicDesPC(11,"项目概况pc图"),
	
	ProjectBasicDesWap(12,"项目概况wap图"),
	
	ProjectRtnDesPC(21,"项目概况回报说明pc图"),
	
	ProjectRtnDesWap(22,"项目概况回报说明wap图");
	
	private int value = 0;
	private String desc;

	private ProjectImageTypeEnum(int value, String desc) { // 必须是private的，否则编译错误
		this.value = value;
		this.desc = desc;
	}

	public int value() {
		return this.value;
	}

	public String desc() {
		return this.desc;
	}

	public static ProjectImageTypeEnum getEnum(int index) {
		for (ProjectImageTypeEnum c : ProjectImageTypeEnum.values()) {
			if (c.value() == index) {
				return c;
			}
		}
		return null;
	}
}
