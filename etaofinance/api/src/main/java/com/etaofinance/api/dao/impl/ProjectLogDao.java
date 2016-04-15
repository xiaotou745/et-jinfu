package com.etaofinance.api.dao.impl;

import org.springframework.stereotype.Repository;

import com.etaofinance.api.common.DaoBase;
import com.etaofinance.api.dao.inter.IProjectLogDao;
import com.etaofinance.entity.ProjectLogModel;
@Repository
public class ProjectLogDao extends DaoBase implements IProjectLogDao{
	@Override
	public int insert(ProjectLogModel req) {
		return getMasterSqlSessionUtil().insert("IProjectLogDao.insert", req);
	}
}
