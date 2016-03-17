package com.etaofinance.entity.req;

import java.util.List;

import com.etaofinance.entity.Project;
import com.etaofinance.entity.ProjectImage;
import com.etaofinance.entity.ProjectStrategy;

/**
 * 保存项目实体类
 * @author ofmyi_000
 *
 */
public class SaveProject {

	private Project project;
	private List<ProjectStrategy> strategylist;
	private  List<ProjectImage> imglist;
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	public List<ProjectStrategy> getStrategylist() {
		return strategylist;
	}
	public void setStrategylist(List<ProjectStrategy> strategylist) {
		this.strategylist = strategylist;
	}
	public List<ProjectImage> getImglist() {
		return imglist;
	}
	public void setImglist(List<ProjectImage> imglist) {
		this.imglist = imglist;
	}
	
}
