package com.etaofinance.entity.domain;

import com.etaofinance.core.enums.ProjectType;
import com.etaofinance.core.util.Convert;
import com.etaofinance.core.util.ParseHelper;
import com.etaofinance.entity.Project;
import com.etaofinance.core.enums.ProjectStatus;;

public class ProjectModel extends Project{

	private Float aValue;
	private Float bValue;
	private String projectStatusStr;
	public String getProjectStatusStr() {
		return ProjectStatus.getEnum(ParseHelper.ToInt(getProjectstatus())).desc();
	}

	public void setProjectStatusStr(String projectStatusStr) {
		this.projectStatusStr = projectStatusStr;
	}

	public Float getAValue() {
		return aValue;
	}

	public void setAValue(Float aValue) {
		this.aValue = aValue;
	}

	public Float getBValue() {
		return bValue;
	}

	public void setBValue(Float bValue) {
		this.bValue = bValue;
	}

	public String getTypeidStr() {
		return ProjectType.getEnum((int)getTypeid()).desc();
	}

	public void setTypeidStr(String typeidStr) {
	}

	public String getAmountStr() {
		return Convert.toMoneyString(getAmount());
	}

	public void setAmountStr(String amountStr) {
	}

	public String getInComeStr() {
		String string="";
		if(getTypeid()==2)//稳健型
			string+="预计";
		if(getBValue()==0)
		{
		 string+=(getAValue()+"%");
		}
		else
		{
			string+=(getAValue()+"%-"+getBValue()+"%");
		}
		return string;
	}
	public String getUnitpriceStr() {
		return Convert.toMoneyString(getUnitprice());
	}


	public void setInComeStr(String inComeStr) {
	}
}
