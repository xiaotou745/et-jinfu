package com.etaofinance.api.dao.inter;

import com.etaofinance.entity.ProjectEnroll;
import com.etaofinance.entity.common.PagedResponse;
import com.etaofinance.entity.req.PagedProjectEnrollReq;

public interface IProjectEnrollDao {
    int deleteByPrimaryKey(Long id);

    int insert(ProjectEnroll record);

    int insertSelective(ProjectEnroll record);

    ProjectEnroll selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ProjectEnroll record);

    int updateByPrimaryKey(ProjectEnroll record);

	PagedResponse<ProjectEnroll> getProjectEnrollList(PagedProjectEnrollReq req);
}