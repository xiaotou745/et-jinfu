package com.etaofinance.entity.req;

/*
 * 项目融资状态修改  入口参数 包含日志信息参数 wangchao
 */
public class ProjectStatusReq {
	private long projectId;
	private String operater;
	private String remark;
	// 修改前项目融资状态:1未上线，2预热中，3融资中，4融资成功，5融资失败
	private int preProjectStatus;
	// 修改后项目融资状态
	private int aftProjectStatus;
	private String logRemark;

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public long getProjectId() {
		return projectId;
	}

	public void setProjectId(long projectId) {
		this.projectId = projectId;
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

	public String getLogRemark() {
		return logRemark;
	}

	public void setLogRemark(String logRemark) {
		this.logRemark = logRemark;
	}

	public String getOperater() {
		return operater;
	}

	public void setOperater(String operater) {
		this.operater = operater;
	}
}
