package com.etaofinance.api.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.etaofinance.api.common.DaoBase;
import com.etaofinance.api.dao.inter.IProjectStrategyDao;
import com.etaofinance.entity.ProjectStrategy;

@Repository
public class ProjectStrategyDao extends DaoBase implements IProjectStrategyDao{
	@Override
	public List<ProjectStrategy> getByProjectIds(List<Long> projectids) {
return getMasterSqlSessionUtil().selectList("IProjectStrategyDao.getByProjectIds", projectids);
	}

	@Override
	public int deleteByPrimaryKey(Long id) {
return getMasterSqlSessionUtil().delete("IProjectStrategyDao.deleteByPrimaryKey", id);
	}

	@Override
	public int deleteByProjectId(Long projectid) {
		return getMasterSqlSessionUtil().delete("IProjectStrategyDao.deleteByProjectId", projectid);
	}

	@Override
	public int insertList(List<ProjectStrategy> recordList) {
return getMasterSqlSessionUtil().insert("IProjectStrategyDao.insertList", recordList);
	}

	@Override
	public int updateByPrimaryKey(ProjectStrategy record) {
return getMasterSqlSessionUtil().update("IProjectStrategyDao.updateByPrimaryKey", record);
	}

}
