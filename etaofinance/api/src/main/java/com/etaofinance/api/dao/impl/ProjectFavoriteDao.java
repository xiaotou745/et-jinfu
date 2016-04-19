package com.etaofinance.api.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.etaofinance.entity.domain.ProjectFavoriteInvestModel;
import com.etaofinance.entity.domain.ProjectSubscriptionDM;
import com.etaofinance.entity.req.PagedADVertReq;
import com.etaofinance.entity.req.PagedProjectFavReq;
import com.etaofinance.entity.req.ProFavoriteReq;
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
		
		insertRes= getMasterSqlSessionUtil().insert("IProjectFavoriteDao.insertSelective",profavorite);
		 
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
	/**
	 * 我关注的项目列表
	 */
	@Override
	public List<ProjectFavoriteDM> getListMore(ProFavoriteReq record) {
		 List<ProjectFavoriteDM> list=null;
		 list=getReadOnlySqlSessionUtil()
				.selectList("IProjectFavoriteDao.getListMore",record);
		 
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

	@Override
	public PagedResponse<ProjectFavoriteInvestModel> getFavoritePageList(
			PagedProjectFavReq req) {
		return getReadOnlySqlSessionUtil().selectPageList("IProjectFavoriteDao.getProjectFavList",req);
	}
	/**
	 * 该项目是否关注
	 */
	@Override
	public int isMyFavorite(Long uid, Long pid) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("uid", uid);
		map.put("pid", pid);
		return getReadOnlySqlSessionUtil().selectOne("IProjectFavoriteDao.isMyFavorite",map);
	}
	/**
	 * 取消关注
	 */
	@Override
	public int cancelFavorite(Long uid, Long pid) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("uid", uid);
		map.put("pid", pid);
		return getMasterSqlSessionUtil().update("IProjectFavoriteDao.cancelFavorite",map);
	}
	


}
