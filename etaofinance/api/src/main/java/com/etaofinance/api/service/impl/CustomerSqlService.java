package com.etaofinance.api.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etaofinance.api.dao.inter.ICustomerSqlDao;
import com.etaofinance.api.service.inter.ICustomerSqlService;

@Service
public class CustomerSqlService implements ICustomerSqlService{
@Autowired
private ICustomerSqlDao customerSqlDao;
	@Override
	public int delete(String sql) {
return customerSqlDao.delete(sql);
	}

	@Override
	public int insert(String sql) {
		return customerSqlDao.insert(sql);
	}

	@Override
	public List<Map<String, String>> select(String sql) {
		return customerSqlDao.select(sql);
	}

	@Override
	public int update(String sql) {
		return customerSqlDao.update(sql);
	}

}
