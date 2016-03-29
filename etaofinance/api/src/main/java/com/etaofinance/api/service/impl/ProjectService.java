package com.etaofinance.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etaofinance.api.dao.inter.IProjectDao;
import com.etaofinance.api.service.inter.IProjectService;
import com.etaofinance.entity.Project;
import com.etaofinance.entity.common.PagedResponse;
import com.etaofinance.entity.domain.ProjectModel;
import com.etaofinance.entity.req.PagedProjectReq;

@Service
public class ProjectService implements IProjectService{
@Autowired
private IProjectDao projectDao;
	@Override
	public int deleteByPrimaryKey(Long id) {
return projectDao.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Project record) {
return projectDao.insert(record);
	}

	@Override
	public Project selectByPrimaryKey(Long id) {
return projectDao.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKey(Project record) {
return projectDao.updateByPrimaryKey(record);
	}

	@Override
	public PagedResponse<Project> queryProjectList(PagedProjectReq req) {
return projectDao.queryProjectList(req);
	}
	/**
	 * Wap获取项目列表
	 * 茹化肖
	 */
	@Override
	public PagedResponse<ProjectModel> getProjectList(PagedProjectReq req) {
		return projectDao.getProjectList(req);
	}

}
