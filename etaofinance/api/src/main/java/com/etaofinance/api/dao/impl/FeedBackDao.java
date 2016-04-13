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
import com.etaofinance.entity.req.PagedFeedBackReq;
@Repository
public class FeedBackDao extends DaoBase implements IFeedBackDao{

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return getMasterSqlSessionUtil().delete("IFeedBackDao.deleteByPrimaryKey", id);
	}

	@Override
	public int insert(FeedBack record) {
		return getMasterSqlSessionUtil().insert(
				"IFeedBackDao.insert", record);
	}

	@Override
	public int insertSelective(FeedBack record) {
		return getMasterSqlSessionUtil().insert(
				"IFeedBackDao.insertSelective", record);
	}

	@Override
	public FeedBack selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(FeedBack record) {
		return getMasterSqlSessionUtil().update(
				"IFeedBackDao.updateByPrimaryKeySelective", record);
	}

	@Override
	public int updateByPrimaryKey(FeedBack record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public PagedResponse<FeedBack> getFeedBackList(PagedFeedBackReq req) {
		return getReadOnlySqlSessionUtil().selectPageList("IFeedBackDao.getFeedBackList",req);
	}

	
	

}
