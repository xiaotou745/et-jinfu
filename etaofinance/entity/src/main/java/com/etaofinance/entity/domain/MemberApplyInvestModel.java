package com.etaofinance.entity.domain;

import java.io.Serializable;

import com.etaofinance.core.enums.MemberApplyInvestStatusEnum;

public class MemberApplyInvestModel implements Serializable{
	private int id;
	
	private String memberName;
	
	private String phoneNo;
	
	private String eMail;
	
	private int auditStatus;
	
	private String applyDate; 
	
	private String refuseReasion;
	
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
 
	public int getAuditStatus() {
		return auditStatus;
	}
	
	public String getAuditStatusString(){
		if(MemberApplyInvestStatusEnum.getEnum(auditStatus)!=null)
		{
			return MemberApplyInvestStatusEnum.getEnum(auditStatus).desc();
		}
		else {
			return "";
		}
	}

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

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public String getRefuseReasion() {
		return refuseReasion;
	}

	public void setRefuseReasion(String refuseReasion) {
		this.refuseReasion = refuseReasion;
	}
}
