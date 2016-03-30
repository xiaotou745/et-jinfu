package com.etaofinance.api.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.etaofinance.api.common.DaoBase;
import com.etaofinance.api.dao.inter.IADVertDao;
import com.etaofinance.api.dao.inter.IProjectSubscriptionDao;
import com.etaofinance.api.dao.inter.IRoleInfoDao;
import com.etaofinance.entity.ADVert;
import com.etaofinance.entity.BalanceRecord;
import com.etaofinance.entity.ProjectSubscription;
import com.etaofinance.entity.RoleInfo;
import com.etaofinance.entity.common.PagedResponse;
import com.etaofinance.entity.domain.BalanceRecordDM;
import com.etaofinance.entity.domain.ProjectSubscriptionDM;
import com.etaofinance.entity.req.PagedADVertReq;
@Repository
public class ProjectSubscriptionDao extends DaoBase implements IProjectSubscriptionDao{

	@Override
	public int deleteByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(ProjectSubscription record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSelective(ProjectSubscription record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ProjectSubscription selectByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(ProjectSubscription record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(ProjectSubscription record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<ProjectSubscriptionDM> getListMore(ProjectSubscriptionDM record) {
		 List<ProjectSubscriptionDM> list=null;
		 list=getReadOnlySqlSessionUtil()
				.selectList(
						"IProjectSubscriptionDao.getListMore",record);
		 
		 return list;
	}
	

}
