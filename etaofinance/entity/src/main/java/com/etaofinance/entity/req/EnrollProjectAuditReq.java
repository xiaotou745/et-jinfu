package com.etaofinance.entity.req;

public class EnrollProjectAuditReq {
private long id;
	
	private int status;
	
	private String refuseRemark;	
	
	public String getRefuseRemark() {
		return refuseRemark;
	}

	public void setRefuseRemark(String refuseRemark) {
		this.refuseRemark = refuseRemark;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public long getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
