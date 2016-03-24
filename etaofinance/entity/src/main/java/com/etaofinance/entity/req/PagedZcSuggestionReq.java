package com.etaofinance.entity.req;

import com.etaofinance.entity.common.PagedRequestBase;

public class PagedZcSuggestionReq extends PagedRequestBase{
	private int id;
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	private String projectName;

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	} 
}
