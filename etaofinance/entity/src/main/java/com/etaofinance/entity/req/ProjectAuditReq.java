package com.etaofinance.entity.req;

public class ProjectAuditReq {
	
	private long projectId;
	
	private int auditStatus;
	private String auditName;
	private String remark;	
	private String onlinePreheatDate;
	
	private String openFinancingDate;
	
	private String endFinancingDate;
	//修改前项目融资状态:1未上线，2预热中，3融资中，4融资成功，5融资失败
	private int preProjectStatus;
	//修改后项目融资状态
	private int aftProjectStatus;
	
	//修改前项目审核状态
	private int preAuditStatus;
	//修改后项目审核状态
	private int aftAuditStatus;
	
	private String logRemark;
	
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

	public int getPreProjectStatus() {
		return preProjectStatus;
	}

	public void setPreProjectStatus(int preProjectStatus) {
		this.preProjectStatus = preProjectStatus;
	}

	public int getAftProjectStatus() {
		return aftProjectStatus;
	}

	public void setAftProjectStatus(int aftProjectStatus) {
		this.aftProjectStatus = aftProjectStatus;
	}

	public int getPreAuditStatus() {
		return preAuditStatus;
	}

	public void setPreAuditStatus(int preAuditStatus) {
		this.preAuditStatus = preAuditStatus;
	}

	public int getAftAuditStatus() {
		return aftAuditStatus;
	}

	public void setAftAuditStatus(int aftAuditStatus) {
		this.aftAuditStatus = aftAuditStatus;
	}

	public String getLogRemark() {
		return logRemark;
	}

	public void setLogRemark(String logRemark) {
		this.logRemark = logRemark;
	}
	
	
	

}
