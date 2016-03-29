package com.etaofinance.api.dao.inter;

import com.etaofinance.entity.ProjectFavorite;

public interface IProjectFavoriteDao {
    int deleteByPrimaryKey(Long id);

    int insert(ProjectFavorite record);

    int insertSelective(ProjectFavorite record);

    ProjectFavorite selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ProjectFavorite record);

    int updateByPrimaryKey(ProjectFavorite record);
}