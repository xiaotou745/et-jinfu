package com.etaofinance.api.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.etaofinance.api.common.DaoBase;
import com.etaofinance.api.dao.inter.IProjectImageDao;
import com.etaofinance.entity.ProjectImage;
import com.etaofinance.entity.domain.ModifyProjectImg;
@Repository
public class ProjectImageDao extends DaoBase implements IProjectImageDao{

	@Override
	public int deleteByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(ProjectImage record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSelective(ProjectImage record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ProjectImage selectByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(ProjectImage record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(ProjectImage record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertList(List<ProjectImage> projectImageList) {
		return getMasterSqlSessionUtil().insert(
				"IProjectImageDao.insertList", projectImageList); 
	}

	@Override
	public List<ProjectImage> getByProjectId(long projectId) {
		return getMasterSqlSessionUtil().selectList("IProjectImageDao.getByProjectId",projectId);
	}

	@Override
	public int updateDeleteById(List<ModifyProjectImg> updateImgs) {
		return getMasterSqlSessionUtil().update("IProjectImageDao.updateDeleteById",updateImgs);
	}

}
