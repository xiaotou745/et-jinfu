package com.etaofinance.api.service.inter;

import com.etaofinance.entity.Project;
import com.etaofinance.entity.common.PagedResponse;
import com.etaofinance.entity.req.PagedProjectReq;

public interface IProjectService {
    int deleteByPrimaryKey(Long id);

    int insert(Project record);

    Project selectByPrimaryKey(Long id);

    int updateByPrimaryKey(Project record);
    PagedResponse<Project>  queryProjectList(PagedProjectReq req);
}
