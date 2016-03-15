package com.etaofinance.api.dao.impl;

import org.springframework.stereotype.Repository;

import com.etaofinance.api.common.DaoBase;
import com.etaofinance.api.dao.inter.IZcSuggestionDao;
import com.etaofinance.entity.ZcSuggestion;

@Repository
public class ZcSuggestionDao extends DaoBase implements IZcSuggestionDao {

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(ZcSuggestion record) {
		// TODO Auto-generated method stub
//		ISuggestionDao.insert
		return getMasterSqlSessionUtil().insert("IZcSuggestionDao.insert", record);
	}

	@Override
	public int insertSelective(ZcSuggestion record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ZcSuggestion selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(ZcSuggestion record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(ZcSuggestion record) {
		// TODO Auto-generated method stub
		return 0;
	}

}
