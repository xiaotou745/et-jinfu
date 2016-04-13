package com.etaofinance.entity.domain;

import java.sql.Date;

import com.etaofinance.entity.ProjectFavorite;

public class ProjectFavoriteDM  extends ProjectFavorite{

    private String projectName;
    private int projectStatus;
    private String projectImage;
    private Date projectBeginDate;

	public Date getProjectBeginDate() {
		return projectBeginDate;
	}

	public void setProjectBeginDate(Date projectBeginDate) {
		this.projectBeginDate = projectBeginDate;
	}

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