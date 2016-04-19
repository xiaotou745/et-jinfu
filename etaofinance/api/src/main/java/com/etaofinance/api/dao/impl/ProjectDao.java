package com.etaofinance.api.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.etaofinance.api.common.DaoBase;
import com.etaofinance.api.dao.inter.IProjectDao;
import com.etaofinance.entity.Project;
import com.etaofinance.entity.common.PagedResponse;
import com.etaofinance.entity.domain.DataStatistics;
import com.etaofinance.entity.domain.ProjectFavoriteDM;
import com.etaofinance.entity.domain.ProjectModel;
import com.etaofinance.entity.req.PagedProjectReq;
import com.etaofinance.entity.req.ProLaunchReq;
import com.etaofinance.entity.req.ProjectAuditReq;
import com.etaofinance.entity.req.ProjectStatusReq;
@Repository
public class ProjectDao extends DaoBase implements IProjectDao{

	@Override
	public int deleteByPrimaryKey(Long id) {
		return getMasterSqlSessionUtil().delete("IProjectDao.deleteByPrimaryKey", id);
	}

	@Override
	public long insert(Project record) {
		return getMasterSqlSessionUtil().insert("IProjectDao.insert", record);
	}

	@Override
	public int insertSelective(Project record) {
		return getMasterSqlSessionUtil().insert("IProjectDao.insertSelective", record);
	}

	@Override
	public Project selectByPrimaryKey(Long id) {
		return getMasterSqlSessionUtil().selectOne("IProjectDao.selectByPrimaryKey", id);
	}

	@Override
	public int updateByPrimaryKeySelective(Project record) {
		return getMasterSqlSessionUtil().update("IProjectDao.updateByPrimaryKeySelective", record);
	}

	@Override
	public int updateByPrimaryKey(Project record) {
return getMasterSqlSessionUtil().update("IProjectDao.updateByPrimaryKey", record);
	}

	@Override
	public PagedResponse<Project> queryProjectList(PagedProjectReq req) {
		
		PagedResponse<Project> res = null;
		
		res= getReadOnlySqlSessionUtil().selectPageList("IProjectDao.queryProjectList", req);
		
		return res;
	}
	/**
	 * wap获取项目列表
	 */
	@Override
	public PagedResponse<ProjectModel> getProjectList(PagedProjectReq req) {
		return getReadOnlySqlSessionUtil().selectPageList("IProjectDao.getProjectList", req);
	}
	/**
	 * 获取预热或进行中的项目
	 */
	@Override
	public Project getFinceingProject(Long id) {
		return getMasterSqlSessionUtil().selectOne("IProjectDao.getFinceingProject", id);
	}
	
	/**
	 * 我发起的项目
	 * @param 
	 * @author hulingbo
	 * @date time2016年3月31日11:46:29
	 * @return
	 */	
	@Override
	public List<Project> getListMore(ProLaunchReq record) {
		 List<Project> list=null;
		 list=getReadOnlySqlSessionUtil()
				.selectList(
						"IProjectDao.getListMore",record);
		 
		 return list;
	}

	@Override
	public int audit(ProjectAuditReq req) {
		return getMasterSqlSessionUtil().update("IProjectDao.audit",req);
	}
	/**
	 * 获取新手专享列表
	 */
	@Override
	public List<ProjectModel> getNoviceProject() {
		return getReadOnlySqlSessionUtil().selectList("IProjectDao.getNoviceProject");
	}
	/**
	 * 获取项目详情
	 */
	@Override
	public ProjectModel getProjectDetail(Long projectid) {
		return getReadOnlySqlSessionUtil().selectOne("IProjectDao.getProjectDetail",projectid);
	}

	@Override
	public int modifyProjectStatus(ProjectStatusReq req) {
		return getMasterSqlSessionUtil().update("IProjectDao.modifyProjectStatus",req);
	}

	@Override
	public int isShelf(ProjectStatusReq req) {
		return getMasterSqlSessionUtil().update("IProjectDao.isShelf",req);
	}

	@Override
	public List<DataStatistics> getDataStatistics() {
		return getReadOnlySqlSessionUtil()
				.selectList(
						"IProjectDao.getDataStatices");
	}
	
}
