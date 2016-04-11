package com.etaofinance.api.service.inter;
import java.util.List;

import com.etaofinance.entity.Project;
import com.etaofinance.entity.common.HttpResultModel;
import com.etaofinance.entity.common.PagedResponse;
import com.etaofinance.entity.domain.ProjectModel;
import com.etaofinance.entity.domain.PublishProjectReq;
import com.etaofinance.entity.req.PagedProjectReq;
import com.etaofinance.entity.req.ProLaunchReq;
import com.etaofinance.entity.req.ProjectAuditReq;
import com.etaofinance.entity.req.SubProjectReq;

public interface IProjectService {
    int deleteByPrimaryKey(Long id);

    long insert(Project record);

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
    
	/**
	 * 我发起的项目
	 * @param 
	 * @author hulingbo
	 * @date time2016年3月31日11:46:29
	 * @return
	 */	
	List<Project> getListMore(ProLaunchReq record);
	/*
	 * 后台发布项目 wangchao
	 */
	int publishProject(PublishProjectReq req);
	/*
	 * 后台审核项目 wangchao
	 */
	int audit(ProjectAuditReq req);
	/**
	 * 获取新手专享项目列表
	 * @return
	 */
	List<ProjectModel>getNoviceProject();
}
