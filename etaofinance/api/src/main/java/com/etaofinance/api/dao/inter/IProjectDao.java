package com.etaofinance.api.dao.inter;

import java.util.List;

import com.etaofinance.entity.Bank;
import com.etaofinance.entity.Project;
import com.etaofinance.entity.common.PagedResponse;
import com.etaofinance.entity.domain.ProjectModel;
import com.etaofinance.entity.req.PagedProjectReq;

public interface IProjectDao {
    int deleteByPrimaryKey(Long id);

    int insert(Project record);

    int insertSelective(Project record);

    Project selectByPrimaryKey(Long id);
    /**
     * 获取预热和进行中的项目
     * @param id
     * @return
     */
    Project getFinceingProject(Long id);
    int updateByPrimaryKeySelective(Project record);

    int updateByPrimaryKey(Project record);
    
    PagedResponse<Project>  queryProjectList(PagedProjectReq req);
    /**
     * WWap获取项目列表
     * @param req
     * @return
     */
    PagedResponse<ProjectModel> getProjectList(PagedProjectReq req);    
    
   
}