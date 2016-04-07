package com.etaofinance.entity.domain;

import java.util.Date;

import com.etaofinance.entity.Project;
import com.etaofinance.entity.ProjectFavorite;
import com.etaofinance.entity.ProjectSubscription;

public class ProjectFavoriteDM  extends ProjectFavorite{

    private String projectName;
    private String projectStatus;

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectStatus() {
		return projectStatus;
	}

	public void setProjectStatus(String projectStatus) {
		this.projectStatus = projectStatus;
	}

}