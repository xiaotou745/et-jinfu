package com.etaofinance.api.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.etaofinance.api.common.DaoBase;
import com.etaofinance.api.dao.inter.ICustomerSqlDao;

@Repository
public class CustomerSqlDao extends DaoBase implements ICustomerSqlDao{

	@Override
	public int delete(String sql) {
return getMasterSqlSessionUtil().delete("ICustomerSqlDao.delete",sql);
	}

	@Override
	public int insert(String sql) {
		return getMasterSqlSessionUtil().insert("ICustomerSqlDao.insert",sql);
	}

	@Override
	public List<Map<String, String>> select(String sql) {
return getReadOnlySqlSessionUtil().selectList("ICustomerSqlDao.select",sql);
	}

	@Override
	public int update(String sql) {
		return getMasterSqlSessionUtil().update("ICustomerSqlDao.update",sql);
	}

}
