package com.etaofinance.entity.domain;

import com.etaofinance.core.enums.RecordType;
import com.etaofinance.entity.BalanceRecord;


public class BalanceRecordDM extends BalanceRecord{
	private String projectName; 
	
	public String getTypeName() {
		return RecordType.getEnum(getTypeid()).desc();
	}
	public void setTypeName(String typeName) {
	
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	

}