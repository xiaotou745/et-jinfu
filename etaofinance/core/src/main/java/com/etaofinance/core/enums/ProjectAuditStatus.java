package com.etaofinance.core.enums;

public enum ProjectAuditStatus {
    WaitAudit (1,"待审核"),

    AuditPass(2,"审核通过"),

    AuditRefuse(3,"审核拒绝");
    
	private int value = 0;
	private String desc;
	private ProjectAuditStatus(int value, String desc) { // 必须是private的，否则编译错误
		this.value = value;
		this.desc = desc;
	}
	public int value() {
		return this.value;
	}
	public String desc() {
		return this.desc;
	}

	public static ProjectAuditStatus getEnum(int index) {
		for (ProjectAuditStatus c : ProjectAuditStatus.values()) {
			if (c.value() == index) {
				return c;
			}
		}
		return null;
	}
}
