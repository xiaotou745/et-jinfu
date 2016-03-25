package com.etaofinance.entity.domain;

import java.io.Serializable;

public class LeadInvestModel implements Serializable{
	private int id;
	
	private String memberName;
	
	private String phoneNo;
	
	private String mail;
	
	private int auditStatus;
	
	private String applyDate; 
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public int getAuditStatus() {
		return auditStatus;
	}
	
//	public String getAuditStatusString(){
//		return Aduit
//	}

	public void setAuditStatus(int auditStatus) {
		this.auditStatus = auditStatus;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(String applyDate) {
		this.applyDate = applyDate;
	}
}
