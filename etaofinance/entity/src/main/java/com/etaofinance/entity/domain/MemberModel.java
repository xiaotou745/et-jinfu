package com.etaofinance.entity.domain;

import java.io.Serializable;

import com.etaofinance.core.enums.MemberStatusEnum;
import com.etaofinance.core.enums.MemberTypeEnum;

public class MemberModel implements Serializable{
	private int id;
	
	private String memberName;
	
	private String phoneNo;
	
	private String mail;
	
	private Double balance;
	
	private String lastLoginTime;
	
	private int memberType;
	
	private int memberStatus;
	
	private String applyDate; 
	
	private int projectCount;
	
	private int investCount;
	
	private int attentionCount;
	
	
	
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
		if(mail==null || mail=="")return "";
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
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

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public String getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(String lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public int getMemberType() {
		return memberType;
	}
	public String getMemberTypeString(){
		if(MemberTypeEnum.getEnum(memberType) !=null){
			return MemberTypeEnum.getEnum(memberType).desc();
		}else {
			return "";
		}
	}
	public void setMemberType(int memberType) {
		this.memberType = memberType;
	}

	public int getMemberStatus() {
		return memberStatus;
	}
	
	public String getMemberStatusString(){
		if(MemberStatusEnum.getEnum(memberStatus)!=null){
			return MemberStatusEnum.getEnum(memberStatus).desc();
		}else {
			return "";
		}
	}

	public void setMemberStatus(int memberStatus) {
		this.memberStatus = memberStatus;
	}

	public int getProjectCount() {
		return projectCount;
	}

	public void setProjectCount(int projectCount) {
		this.projectCount = projectCount;
	}

	public int getInvestCount() {
		return investCount;
	}

	public void setInvestCount(int investCount) {
		this.investCount = investCount;
	}

	public int getAttentionCount() {
		return attentionCount;
	}

	public void setAttentionCount(int attentionCount) {
		this.attentionCount = attentionCount;
	}
}
