package com.etaofinance.entity.domain;

import java.util.Date;

import com.etaofinance.entity.BalanceRecord;
import com.etaofinance.entity.MenuInfo;

public class BalanceRecordDM extends BalanceRecord{
	private String typeName;
	private String projectName; 
	
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	

}