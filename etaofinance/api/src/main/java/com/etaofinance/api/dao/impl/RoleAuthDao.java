package com.etaofinance.api.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.etaofinance.api.common.DaoBase;
import com.etaofinance.api.dao.inter.IRoleAuthDao;
import com.etaofinance.entity.RoleAuth;
import com.etaofinance.entity.RoleInfo;

@Repository
public class RoleAuthDao extends DaoBase implements
		IRoleAuthDao {

	@Override
	public boolean deleteAuthList(List<RoleAuth> authList) {
		return getMasterSqlSessionUtil()
				.delete("IRoleAuthDao.deleteAuthList",
						authList) > 0;
	}

	@Override
	public boolean insertAuthList(List<RoleAuth> authList) {
		return getMasterSqlSessionUtil()
				.insert("IRoleAuthDao.insertAuthList",
						authList) > 0;
	}




}
