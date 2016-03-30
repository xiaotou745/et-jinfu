package com.etaofinance.api.dao.inter;

import java.util.List;

import com.etaofinance.entity.ProjectFavorite;
import com.etaofinance.entity.domain.ProjectFavoriteDM;
import com.etaofinance.entity.domain.ProjectSubscriptionDM;

public interface IProjectFavoriteDao {
    int deleteByPrimaryKey(Long id);

    int insert(ProjectFavorite record);

    int insertSelective(ProjectFavorite record);

    ProjectFavorite selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ProjectFavorite record);

    int updateByPrimaryKey(ProjectFavorite record);
    
    List<ProjectFavoriteDM> getListMore(ProjectFavoriteDM record);
}