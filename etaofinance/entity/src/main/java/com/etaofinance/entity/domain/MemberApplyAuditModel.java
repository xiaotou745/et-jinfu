package com.etaofinance.entity.domain;

public class MemberApplyAuditModel {
	private int id;
	
	private String trueName;
	
	private String idCard;
	
	private String applyInfo;
	
	private String companyName;
	
	private String companyTitle;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTrueName() {
		return trueName;
	}

	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getApplyInfo() {
		return applyInfo;
	}

	public void setApplyInfo(String applyInfo) {
		this.applyInfo = applyInfo;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyTitle() {
		return companyTitle;
	}

	public void setCompanyTitle(String companyTitle) {
		this.companyTitle = companyTitle;
	}
}
