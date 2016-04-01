package com.etaofinance.api.dao.inter;

import com.etaofinance.entity.ProjectEnroll;

public interface IProjectEnrollDao {
    int deleteByPrimaryKey(Long id);

    int insert(ProjectEnroll record);

    int insertSelective(ProjectEnroll record);

    ProjectEnroll selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ProjectEnroll record);

    int updateByPrimaryKey(ProjectEnroll record);
}