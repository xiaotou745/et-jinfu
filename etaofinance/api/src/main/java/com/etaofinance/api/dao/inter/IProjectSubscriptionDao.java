package com.etaofinance.api.dao.inter;

import com.etaofinance.entity.ProjectSubscription;

public interface IProjectSubscriptionDao {
    int deleteByPrimaryKey(Long id);

    int insert(ProjectSubscription record);

    int insertSelective(ProjectSubscription record);

    ProjectSubscription selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ProjectSubscription record);

    int updateByPrimaryKey(ProjectSubscription record);
}