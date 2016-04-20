package com.etaofinance.api.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.etaofinance.api.common.DaoBase;
import com.etaofinance.api.dao.inter.IADVertDao;
import com.etaofinance.api.dao.inter.IProjectEnrollDao;
import com.etaofinance.api.dao.inter.IRoleInfoDao;
import com.etaofinance.entity.ADVert;
import com.etaofinance.entity.ProjectEnroll;
import com.etaofinance.entity.RoleInfo;
import com.etaofinance.entity.common.PagedResponse;
import com.etaofinance.entity.req.PagedADVertReq;
import com.etaofinance.entity.req.PagedProjectEnrollReq;
@Repository
public class ProjectEnrollDao extends DaoBase implements IProjectEnrollDao{

	@Override
	public int deleteByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(ProjectEnroll record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSelective(ProjectEnroll record) {
		return getMasterSqlSessionUtil().insert(
				"IProjectEnrollDao.insertSelective", record);
	}

	@Override
	public ProjectEnroll selectByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(ProjectEnroll record) {
		int res = 0;

		res = this.getMasterSqlSessionUtil().update(
				"IProjectEnrollDao.updateByPrimaryKeySelective", record);
		
		return res;
	}

	@Override
	public int updateByPrimaryKey(ProjectEnroll record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public PagedResponse<ProjectEnroll> getProjectEnrollList(
			PagedProjectEnrollReq req) {
	
		PagedResponse<ProjectEnroll> res = null;
		
		res= getReadOnlySqlSessionUtil().selectPageList("IProjectEnrollDao.getProjectEnrollList", req);
		
		return res;
		
	}

	
}
