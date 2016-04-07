package com.etaofinance.entity.req;

public class ProjectAuditReq {
	private long projectId;
	
	private int auditStatus;
	private String auditName;
	private String remark;
	
	private String onlinePreheatDate;
	
	private String openFinancingDate;
	
	private String endFinancingDate;

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(int auditStatus) {
		this.auditStatus = auditStatus;
	}

	public long getProjectId() {
		return projectId;
	}

	public void setProjectId(long projectId) {
		this.projectId = projectId;
	}

	public String getOnlinePreheatDate() {
		return onlinePreheatDate;
	}

	public void setOnlinePreheatDate(String onlinePreheatDate) {
		this.onlinePreheatDate = onlinePreheatDate;
	}

	public String getOpenFinancingDate() {
		return openFinancingDate;
	}

	public void setOpenFinancingDate(String openFinancingDate) {
		this.openFinancingDate = openFinancingDate;
	}

	public String getEndFinancingDate() {
		return endFinancingDate;
	}

	public void setEndFinancingDate(String endFinancingDate) {
		this.endFinancingDate = endFinancingDate;
	}

	public String getAuditName() {
		return auditName;
	}

	public void setAuditName(String auditName) {
		this.auditName = auditName;
	}
	
}
