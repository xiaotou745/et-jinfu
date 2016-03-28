package com.etaofinance.entity.req;

public class MemberApplyAuditReq {
	private String memberApplyId;
	
	private int auditStatus;
	
	private String refuseReason;

	private String auditName;
	public String getMemberApplyId() {
		return memberApplyId;
	}

	public void setMemberApplyId(String memberApplyId) {
		this.memberApplyId = memberApplyId;
	}

	public int getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(int auditStatus) {
		this.auditStatus = auditStatus;
	}

	public String getRefuseReason() {
		return refuseReason;
	}

	public void setRefuseReason(String refuseReason) {
		this.refuseReason = refuseReason;
	}

	public String getAuditName() {
		return auditName;
	}

	public void setAuditName(String auditName) {
		this.auditName = auditName;
	}
}
