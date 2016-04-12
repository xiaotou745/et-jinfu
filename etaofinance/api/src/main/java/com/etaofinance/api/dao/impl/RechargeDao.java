package com.etaofinance.api.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.etaofinance.api.common.DaoBase;
import com.etaofinance.api.dao.inter.IADVertDao;
import com.etaofinance.api.dao.inter.IMessageDao;
import com.etaofinance.api.dao.inter.IRechargeDao;
import com.etaofinance.api.dao.inter.IRoleInfoDao;
import com.etaofinance.entity.ADVert;
import com.etaofinance.entity.Bank;
import com.etaofinance.entity.Message;
import com.etaofinance.entity.Recharge;
import com.etaofinance.entity.RoleInfo;
import com.etaofinance.entity.common.PagedResponse;
import com.etaofinance.entity.req.PagedADVertReq;
import com.etaofinance.entity.req.PagedRechargeReq;
@Repository
public class RechargeDao extends DaoBase implements IRechargeDao{

	@Override
	public int deleteByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(Recharge record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSelective(Recharge record) {
	
		
		int insertRes =0;
		
		insertRes = this.getMasterSqlSessionUtil().insert("IRechargeDao.insertSelective",record);
		
		return insertRes;
	}

	@Override
	public Recharge selectByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(Recharge record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(Recharge record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public PagedResponse<Recharge> getRechargeList(PagedRechargeReq req) {
		return getReadOnlySqlSessionUtil().selectPageList("IRechargeDao.getRechargeList", req);
	}

}
