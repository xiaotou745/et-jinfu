package com.etaofinance.api.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.etaofinance.api.common.DaoBase;
import com.etaofinance.api.dao.inter.IADVertDao;
import com.etaofinance.api.dao.inter.IAdminOptLogDao;
import com.etaofinance.api.dao.inter.IRoleInfoDao;
import com.etaofinance.entity.ADVert;
import com.etaofinance.entity.AdminOptLog;
import com.etaofinance.entity.RoleInfo;
import com.etaofinance.entity.common.PagedResponse;
import com.etaofinance.entity.req.PagedADVertReq;
@Repository
public class AdminOptLogDao extends DaoBase implements IAdminOptLogDao{

	

	@Override
	public int insertSelective(AdminOptLog record) {
		return getMasterSqlSessionUtil().insert(
				"IAdminOptLogDao.insertSelective", record);
	}

	@Override
	public int deleteByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(AdminOptLog record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public AdminOptLog selectByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(AdminOptLog record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(AdminOptLog record) {
		// TODO Auto-generated method stub
		return 0;
	}

	

}
