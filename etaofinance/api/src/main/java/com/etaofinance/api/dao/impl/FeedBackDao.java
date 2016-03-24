package com.etaofinance.api.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.etaofinance.api.common.DaoBase;
import com.etaofinance.api.dao.inter.IADVertDao;
import com.etaofinance.api.dao.inter.IBankDao;
import com.etaofinance.api.dao.inter.IFeedBackDao;
import com.etaofinance.api.dao.inter.IRoleInfoDao;
import com.etaofinance.entity.ADVert;
import com.etaofinance.entity.Bank;
import com.etaofinance.entity.FeedBack;
import com.etaofinance.entity.RoleInfo;
import com.etaofinance.entity.common.PagedResponse;
import com.etaofinance.entity.domain.MenuEntity;
import com.etaofinance.entity.req.PagedADVertReq;
@Repository
public class FeedBackDao extends DaoBase implements IFeedBackDao{

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(FeedBack record) {
		return getMasterSqlSessionUtil().insert(
				"IFeedBackDao.insert", record);
	}

	@Override
	public int insertSelective(FeedBack record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public FeedBack selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(FeedBack record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(FeedBack record) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	

}
