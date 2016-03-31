package com.etaofinance.api.service.inter;
import com.etaofinance.entity.Project;
import com.etaofinance.entity.common.HttpResultModel;
import com.etaofinance.entity.common.PagedResponse;
import com.etaofinance.entity.domain.ProjectModel;
import com.etaofinance.entity.req.PagedProjectReq;
import com.etaofinance.entity.req.SubProjectReq;

public interface IProjectService {
    int deleteByPrimaryKey(Long id);

    int insert(Project record);

    Project selectByPrimaryKey(Long id);

    int updateByPrimaryKey(Project record);
    PagedResponse<Project>  queryProjectList(PagedProjectReq req);
    /**
     * WAp获取项目列表
     * @param req
     * @return
     */
    PagedResponse<ProjectModel>  getProjectList(PagedProjectReq req);
    /**
     * 认购项目
     * @param req
     * @return
     */
    HttpResultModel<Object>  subproject(SubProjectReq req);
}
