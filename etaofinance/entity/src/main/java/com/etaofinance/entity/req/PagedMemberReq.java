package com.etaofinance.entity.req;

import com.etaofinance.entity.common.PagedRequestBase;

public class PagedMemberReq extends PagedRequestBase{
	private int applyId;
	
	private String memberName;
	
	private String phoneNo;
	private String mail;
	
	private int memberType;
	
	private int auditStatus;

	private String applyStartDate;
	
	private String applyEndDate;
	
	private String registerStartDate;
	
	private String registerEndDate;
 

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

	public int getMemberType() {
		return memberType;
	}

	public void setMemberType(int memberType) {
		this.memberType = memberType;
	}

	public int getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(int auditStatus) {
		this.auditStatus = auditStatus;
	} 
	public String getApplyStartDate() {
		return applyStartDate;
	}

	public void setApplyStartDate(String applyStartDate) {
		this.applyStartDate = applyStartDate;
	}
	public String getApplyEndDate() {
		return applyEndDate;
	}
 
	public void setRegisterStartDate(String registerStartDate) {
		this.registerStartDate = registerStartDate;
	}

	public String getRegisterEndDate() {
		return registerEndDate;
	}

	public void setRegisterEndDate(String registerEndDate) {
		this.registerEndDate = registerEndDate;
	}

	public int getApplyId() {
		return applyId;
	}

	public void setApplyId(int applyId) {
		this.applyId = applyId;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
}
