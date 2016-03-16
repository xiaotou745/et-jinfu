package com.etaofinance.api.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.etaofinance.api.common.DaoBase;
import com.etaofinance.api.dao.inter.IADVertDao;
import com.etaofinance.api.dao.inter.IQADao;
import com.etaofinance.api.dao.inter.IRoleInfoDao;
import com.etaofinance.entity.ADVert;
import com.etaofinance.entity.QA;
import com.etaofinance.entity.RoleInfo;
import com.etaofinance.entity.common.PagedResponse;
import com.etaofinance.entity.req.PagedADVertReq;
import com.etaofinance.entity.req.PagedQAReq;
@Repository
public class QADao extends DaoBase implements IQADao{

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return getMasterSqlSessionUtil().delete("IQADao.deleteByPrimaryKey", id);
	}

	@Override
	public int insert(QA record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSelective( QA record) {
		return getMasterSqlSessionUtil().insert(
				"IQADao.insertSelective", record);
	}

	@Override
	public  QA selectByPrimaryKey(Integer id) {
		return getReadOnlySqlSessionUtil().selectOne("IQADao.selectByPrimaryKey",id);
	}

	@Override
	public int updateByPrimaryKeySelective(QA record) {
		return getMasterSqlSessionUtil().update("IQADao.updateByPrimaryKeySelective",record);
	}

	@Override
	public int updateByPrimaryKey(QA record) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public PagedResponse<QA>  query(PagedQAReq req)
	{
		return getReadOnlySqlSessionUtil().selectPageList(
				"IQADao.query", req);
	}

}
