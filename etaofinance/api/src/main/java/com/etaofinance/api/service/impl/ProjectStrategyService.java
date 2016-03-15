package com.etaofinance.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etaofinance.api.dao.inter.IProjectStrategyDao;
import com.etaofinance.api.service.inter.IProjectStrategyService;
import com.etaofinance.entity.ProjectStrategy;

@Service
public class ProjectStrategyService implements IProjectStrategyService{
	@Autowired
	private IProjectStrategyDao projectStrategyDao;

	@Override
	public int deleteByPrimaryKey(Long id) {
return projectStrategyDao.deleteByPrimaryKey(id);
	}

	@Override
	public int deleteByProjectId(Long projectid) {
return projectStrategyDao.deleteByProjectId(projectid);
	}

	@Override
	public int insertList(List<ProjectStrategy> recordList) {
return projectStrategyDao.insertList(recordList);
	}

	@Override
	public int updateByPrimaryKey(ProjectStrategy record) {
return projectStrategyDao.updateByPrimaryKey(record);
	}

	@Override
	public List<ProjectStrategy> getByProjectIds(List<Long> projectids) {
return projectStrategyDao.getByProjectIds(projectids);
	}

}
