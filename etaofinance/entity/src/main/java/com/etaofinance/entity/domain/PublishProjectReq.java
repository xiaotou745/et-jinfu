package com.etaofinance.entity.domain;

import java.util.List;

import com.etaofinance.entity.Project;
import com.etaofinance.entity.ProjectImage;
import com.etaofinance.entity.ProjectStrategy;

public class PublishProjectReq {
	private Project project;

	private List<ProjectStrategy> projectStrategyList;
	
	private List<ProjectImage> projectImageList;
	
	private String publishName;
	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	 

	public String getPublishName() {
		return publishName;
	}

	public void setPublishName(String publishName) {
		this.publishName = publishName;
	}

	public List<ProjectStrategy> getProjectStrategyList() {
		return projectStrategyList;
	}

	public void setProjectStrategyList(List<ProjectStrategy> projectStrategyList) {
		this.projectStrategyList = projectStrategyList;
	}

	public List<ProjectImage> getProjectImageList() {
		return projectImageList;
	}

	public void setProjectImageList(List<ProjectImage> projectImageList) {
		this.projectImageList = projectImageList;
	}
	
}
