package com.etaofinance.api.dao.inter;

import java.util.List;

import com.etaofinance.entity.Bank;
import com.etaofinance.entity.Project;
import com.etaofinance.entity.common.PagedResponse;
import com.etaofinance.entity.domain.DataStatistics;
import com.etaofinance.entity.domain.ProjectFavoriteDM;
import com.etaofinance.entity.domain.ProjectModel;
import com.etaofinance.entity.domain.ToDoDataStatistics;
import com.etaofinance.entity.req.PagedProjectReq;
import com.etaofinance.entity.req.ProLaunchReq;
import com.etaofinance.entity.req.ProjectAuditReq;
import com.etaofinance.entity.req.ProjectStatusReq;

public interface IProjectDao {
    int deleteByPrimaryKey(Long id);

    long insert(Project record);

    int insertSelective(Project record);

    Project selectByPrimaryKey(Long id);
    /**
     * 获取项目详情
     * @param projectid
     * @return
     */
    ProjectModel getProjectDetail(Long projectid);
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

	/**
	 * 我发起的项目
	 * @param 
	 * @author hulingbo
	 * @date time2016年3月31日11:46:29
	 * @return
	 */
    List<Project> getListMore(ProLaunchReq record);

	int audit(ProjectAuditReq req);
    /**
     * 获取新手专享项目列表
     * @return
     */
	List<ProjectModel> getNoviceProject();

	int modifyProjectStatus(ProjectStatusReq req);

	int isShelf(ProjectStatusReq req);
	
	/*
	 * 获取数据统计汇总
	 */
	List<DataStatistics> getDataStatistics();
	/*
	 * 待办理数据统计汇总
	 */
	List<ToDoDataStatistics> getToDoDataStatices();
	/**
	 * 修改数量
	 * @param poid
	 * @param num
	 * @return
	 */
	int changeFlowNum(Long poid,int num);
	/**
	 * 定时服务
	 * @return
	 */
	int QuartzServie();
}