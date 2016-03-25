package com.etaofinance.api.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.etaofinance.api.common.DaoBase;
import com.etaofinance.api.dao.inter.IADVertDao;
import com.etaofinance.api.dao.inter.IBankCardDao;
import com.etaofinance.api.dao.inter.IBankDao;
import com.etaofinance.api.dao.inter.IRoleInfoDao;
import com.etaofinance.entity.ADVert;
import com.etaofinance.entity.Bank;
import com.etaofinance.entity.BankCard;
import com.etaofinance.entity.QA;
import com.etaofinance.entity.RoleInfo;
import com.etaofinance.entity.common.PagedResponse;
import com.etaofinance.entity.domain.MenuEntity;
import com.etaofinance.entity.req.PagedADVertReq;
@Repository
public class BankCardDao extends DaoBase implements IBankCardDao{

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return getMasterSqlSessionUtil().delete(
				"IBankCardDao.deleteByPrimaryKey", id);
	}

	@Override
	public int insert(BankCard record) {
		return getMasterSqlSessionUtil().insert(
				"IBankCardDao.insert", record);
	}

	@Override
	public int insertSelective(BankCard record) {
		return getMasterSqlSessionUtil().insert(
				"IBankCardDao.insertSelective", record);
	}

	@Override
	public BankCard selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(BankCard record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(BankCard record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<BankCard> getListByMemberId(Long memberId) {		
		return getReadOnlySqlSessionUtil()
				.selectList(
						"IBankCardDao.getListByMemberId",memberId);	 
	}
	

}
