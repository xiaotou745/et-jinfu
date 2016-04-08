package com.etaofinance.api.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.etaofinance.api.common.DaoBase;
import com.etaofinance.api.dao.inter.IADVertDao;
import com.etaofinance.api.dao.inter.IRoleInfoDao;
import com.etaofinance.api.dao.inter.IWithdrawformDao;
import com.etaofinance.entity.ADVert;
import com.etaofinance.entity.RoleInfo;
import com.etaofinance.entity.Withdrawform;
import com.etaofinance.entity.common.PagedResponse;
import com.etaofinance.entity.domain.BalanceRecordDM;
import com.etaofinance.entity.domain.WithdrawformDM;
import com.etaofinance.entity.req.PagedADVertReq;
import com.etaofinance.entity.req.PagedWithdrawReq;
@Repository
public class WithdrawformDao extends DaoBase implements IWithdrawformDao{

	@Override
	public int deleteByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(Withdrawform record) {
		return getMasterSqlSessionUtil().insert(
				"IWithdrawformDao.insert", record);
	}

	@Override
	public int insertSelective(Withdrawform record) {
		return getMasterSqlSessionUtil().insert(
				"IWithdrawformDao.insertSelective", record);
	}

	@Override
	public Withdrawform selectByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(Withdrawform record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(Withdrawform record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<WithdrawformDM> getListMore(Withdrawform record) {	
		 return getReadOnlySqlSessionUtil()
				.selectList(
						"IWithdrawformDao.getListMore",record.getMemberid());		 

	}

	@Override
	public WithdrawformDM selectDMByPrimaryKey(Long id) {
		return getReadOnlySqlSessionUtil().selectOne("IWithdrawformDao.selectDMByPrimaryKey",id);
	}

	@Override
	public PagedResponse<Withdrawform> getWithdrawList(PagedWithdrawReq req) {
		return getReadOnlySqlSessionUtil().selectPageList("IWithdrawformDao.getWithdrawList", req);
	}



}
