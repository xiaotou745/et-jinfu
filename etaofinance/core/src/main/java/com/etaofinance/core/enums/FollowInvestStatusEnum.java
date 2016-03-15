package com.etaofinance.core.enums;
/**
 * 跟投人申请列表审核状态
 * @author wangchao
 * @Date 20160314
 */
public enum FollowInvestStatusEnum {
	/**
	 * 待审核
	 */
	WaitAudit(1, "未审核"),
	
	AuditPass(3,"审核未通过");

	private int value = 0;
	private String desc;
	private FollowInvestStatusEnum(int value, String desc) { // 必须是private的，否则编译错误
		this.value = value;
		this.desc = desc;
	}
	public int value() {
		return this.value;
	}
	public String desc() {
		return this.desc;
	}

	public static FollowInvestStatusEnum getEnum(int index) {
		for (FollowInvestStatusEnum c : FollowInvestStatusEnum.values()) {
			if (c.value() == index) {
				return c;
			}
		}
		return null;
	}
}
