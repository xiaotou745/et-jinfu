package com.etaofinance.entity.domain;

import java.util.Date;

import com.etaofinance.core.util.ParseHelper;

public class ProjectMember {

	private String memberName;
	private Float amount;
	private String headImg;
	private int isLead;
	private Date createDate;
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getAmount() {
		return ParseHelper.digitsNum(amount, 2);
	}
	public void setAmount(Float amount) {
		this.amount = amount;
	}
	public String getHeadImg() {
		return headImg;
	}
	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}
	public int getIsLead() {
		return isLead;
	}
	public void setIsLead(int isLead) {
		this.isLead = isLead;
	}
	public String getCreateDate() {
		return ParseHelper.ToDateString(createDate);
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
}
