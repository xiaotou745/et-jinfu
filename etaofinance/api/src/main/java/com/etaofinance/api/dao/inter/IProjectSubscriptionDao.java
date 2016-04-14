package com.etaofinance.api.dao.inter;

import java.util.List;

import com.etaofinance.entity.ProjectSubscription;
import com.etaofinance.entity.common.PagedResponse;
import com.etaofinance.entity.domain.ProjectSubscriptionDM;
import com.etaofinance.entity.req.PagedProjectSubReq;
import com.etaofinance.entity.req.ProSubInvestReq;

public interface IProjectSubscriptionDao {
    int deleteByPrimaryKey(Long id);

    int insert(ProjectSubscription record);

    int insertSelective(ProjectSubscription record);

    ProjectSubscription selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ProjectSubscription record);

    int updateByPrimaryKey(ProjectSubscription record);
    
    List<ProjectSubscriptionDM> getListMore(ProSubInvestReq record);
    
    PagedResponse<ProjectSubscription> getProjectSubPageList(PagedProjectSubReq req);
}