package com.etaofinance.api.service.inter;

import java.util.List;

import com.etaofinance.entity.ProjectStrategy;

public interface IProjectStrategyService {
	int deleteByPrimaryKey(Long id);
    int deleteByProjectId(Long projectid);
    int insertList(List<ProjectStrategy> recordList);

    int updateByPrimaryKey(ProjectStrategy record);
    List<ProjectStrategy> getByProjectIds(List<Long> projectids);
    
    List<ProjectStrategy> getByProjectId(long projectid);
    
}
