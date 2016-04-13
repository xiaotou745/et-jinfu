package com.etaofinance.entity.domain;

import java.util.Date;

import com.etaofinance.entity.Project;
import com.etaofinance.entity.ProjectSubscription;

public class ProjectSubscriptionDM  extends ProjectSubscription{

    private String projectName;
    private int projectStatus;	
    private String projectImage;


	public String getProjectImage() {
		return projectImage;
	}

	public void setProjectImage(String projectImage) {
		this.projectImage = projectImage;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public int getProjectStatus() {
		return projectStatus;
	}

	public void setProjectStatus(int projectStatus) {
		this.projectStatus = projectStatus;
	}

}