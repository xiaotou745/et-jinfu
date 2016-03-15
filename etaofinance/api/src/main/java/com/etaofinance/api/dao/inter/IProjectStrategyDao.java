package com.etaofinance.api.dao.inter;

import com.etaofinance.entity.ProjectStrategy;

public interface IProjectStrategyDao {
    int deleteByPrimaryKey(Long id);

    int insert(ProjectStrategy record);

    int insertSelective(ProjectStrategy record);

    ProjectStrategy selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ProjectStrategy record);

    int updateByPrimaryKey(ProjectStrategy record);
}