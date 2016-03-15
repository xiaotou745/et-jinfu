package com.etaofinance.api.dao.impl;

import org.springframework.stereotype.Repository;

import com.etaofinance.api.common.DaoBase;
import com.etaofinance.api.dao.inter.IProjectDao;
import com.etaofinance.entity.Project;
import com.etaofinance.entity.common.PagedResponse;
import com.etaofinance.entity.req.PagedProjectReq;
@Repository
public class ProjectDao extends DaoBase implements IProjectDao{

	@Override
	public int deleteByPrimaryKey(Long id) {
		return getMasterSqlSessionUtil().delete("IProjectDao.deleteByPrimaryKey", id);
	}

	@Override
	public int insert(Project record) {
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
return getReadOnlySqlSessionUtil().selectPageList("IProjectDao.queryProjectList", req);
	}

}
