package com.etaofinance.api.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.etaofinance.api.common.DaoBase;
import com.etaofinance.api.dao.inter.IADVertDao;
import com.etaofinance.api.dao.inter.IProjectFavoriteDao;
import com.etaofinance.api.dao.inter.IProjectSubscriptionDao;
import com.etaofinance.api.dao.inter.IRoleInfoDao;
import com.etaofinance.entity.ADVert;
import com.etaofinance.entity.BalanceRecord;
import com.etaofinance.entity.ProjectFavorite;
import com.etaofinance.entity.ProjectSubscription;
import com.etaofinance.entity.RoleInfo;
import com.etaofinance.entity.common.PagedResponse;
import com.etaofinance.entity.domain.BalanceRecordDM;
import com.etaofinance.entity.domain.ProjectFavoriteDM;
import com.etaofinance.entity.domain.ProjectSubscriptionDM;
import com.etaofinance.entity.req.PagedADVertReq;
@Repository
public class ProjectFavoriteDao extends DaoBase implements IProjectFavoriteDao{

	@Override
	public int deleteByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(ProjectFavorite profavorite) {
		
		int insertRes=0;
		
		insertRes= getMasterSqlSessionUtil()
				.insert(
						"IProjectFavoriteDao.insert",profavorite);
		 
		return insertRes;
	}

	
	
	
	@Override
	public int insertSelective(ProjectFavorite profavorite) {

		int insertRes=0;
		
		insertRes= getMasterSqlSessionUtil()
				.insert(
						"IProjectFavoriteDao.insertSelective",profavorite);
		 
		return insertRes;
	}

	@Override
	public ProjectFavorite selectByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(ProjectFavorite profavorite) {

		int updateRes=0;
		
		updateRes= getMasterSqlSessionUtil()
				.update(
						"IProjectFavoriteDao.updateByPrimaryKeySelective",profavorite);
		 
		return updateRes;
	}

	@Override
	public int updateByPrimaryKey(ProjectFavorite profavorite) {
	

		int updateRes=0;
		
		updateRes= getMasterSqlSessionUtil()
				.update(
						"IProjectFavoriteDao.updateByPrimaryKey",profavorite);
		 
		return updateRes;
	}

	@Override
	public List<ProjectFavoriteDM> getListMore(ProjectFavoriteDM record) {
		 List<ProjectFavoriteDM> list=null;
		 list=getReadOnlySqlSessionUtil()
				.selectList(
						"IProjectFavoriteDao.getListMore",record);
		 
		 return list;
	}

	@Override
	public int getFavoriteCntByProId(Long proId) {
		
		int getCntRes=0;
		
		getCntRes= getReadOnlySqlSessionUtil()
				.selectOne(
						"IProjectFavoriteDao.getFavoriteCntByProId",proId);
		 
		return getCntRes;
	}
	


}
