package com.etaofinance.api.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.etaofinance.api.common.DaoBase;
import com.etaofinance.api.dao.inter.IADVertDao;
import com.etaofinance.api.dao.inter.IBankDao;
import com.etaofinance.api.dao.inter.IRoleInfoDao;
import com.etaofinance.entity.ADVert;
import com.etaofinance.entity.Bank;
import com.etaofinance.entity.RoleInfo;
import com.etaofinance.entity.common.PagedResponse;
import com.etaofinance.entity.domain.MenuEntity;
import com.etaofinance.entity.req.PagedADVertReq;
@Repository
public class BankDao extends DaoBase implements IBankDao{

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(Bank record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSelective(Bank record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Bank selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(Bank record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(Bank record) {
		// TODO Auto-generated method stub
		return 0;
	}
   
	@Override
	public List<Bank> getBankList() {
		 List<Bank> list=null;
		 list=getReadOnlySqlSessionUtil()
				.selectList(
						"IBankDao.getBankList");
		 
		 return list;
	}

	

}
