package com.etaofinance.api.dao.inter;

import java.util.List;

import com.etaofinance.entity.ProjectSubscription;
import com.etaofinance.entity.domain.ProjectSubscriptionDM;

public interface IProjectSubscriptionDao {
    int deleteByPrimaryKey(Long id);

    int insert(ProjectSubscription record);

    int insertSelective(ProjectSubscription record);

    ProjectSubscription selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ProjectSubscription record);

    int updateByPrimaryKey(ProjectSubscription record);
    
    List<ProjectSubscriptionDM> getListMore(ProjectSubscriptionDM record);
}