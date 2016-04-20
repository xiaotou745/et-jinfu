package com.etaofinance.api.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.etaofinance.api.common.DaoBase;
import com.etaofinance.api.dao.inter.IRoleInfoDao;
import com.etaofinance.entity.RoleInfo;
@Repository
public class RoleInfoDao extends DaoBase implements IRoleInfoDao{

	@Override
	public int insert(RoleInfo record) {
		return getMasterSqlSessionUtil().insert("IRoleInfoDao.insert", record);
	}

	@Override
	public int update(RoleInfo record) {
	    return getMasterSqlSessionUtil().insert("IRoleInfoDao.update", record);
	}

	@Override
	public List<RoleInfo> selectList() {
		 return getReadOnlySqlSessionUtil().selectList("IRoleInfoDao.selectList");
	}

	@Override
	public RoleInfo getRoleInfoByName(String roleName) {
	
		RoleInfo role = null;
		
		role = getReadOnlySqlSessionUtil().selectOne("IRoleInfoDao.getRoleInfoByName", roleName);
		
		return role;
	}

}
