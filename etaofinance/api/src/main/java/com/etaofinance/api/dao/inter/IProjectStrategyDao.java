package com.etaofinance.api.dao.inter;

import java.util.List;

import com.etaofinance.entity.ProjectStrategy;

public interface IProjectStrategyDao {
    int deleteByPrimaryKey(Long id);
    int deleteByProjectId(Long projectid);
    int insertList(List<ProjectStrategy> recordList);

    int updateByPrimaryKey(ProjectStrategy record);
    List<ProjectStrategy> getByProjectIds(List<Long> projectids);
    
	List<ProjectStrategy> getByProjectId(long projectid);
	
	int updateDeleteByProjectId(Long projectId);
}