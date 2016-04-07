package com.etaofinance.api.dao.inter;

import java.util.List;

import com.etaofinance.entity.ProjectImage;

public interface IProjectImageDao {
    int deleteByPrimaryKey(Long id);

    int insert(ProjectImage record);

    int insertSelective(ProjectImage record);

    ProjectImage selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ProjectImage record);

    int updateByPrimaryKey(ProjectImage record);

	int insertList(List<ProjectImage> projectImageList);
}