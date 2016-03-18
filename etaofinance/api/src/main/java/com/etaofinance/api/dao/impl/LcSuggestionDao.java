package com.etaofinance.api.dao.impl;

import org.springframework.stereotype.Repository;

import com.etaofinance.api.common.DaoBase;
import com.etaofinance.api.dao.inter.ILcSuggestionDao;
import com.etaofinance.entity.LcSuggestion;

@Repository
public class LcSuggestionDao extends DaoBase implements ILcSuggestionDao {

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(LcSuggestion record) {
		return getMasterSqlSessionUtil().insert("ILcSuggestionDao.insert",
				record);
	}

	@Override
	public int insertSelective(LcSuggestion record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public LcSuggestion selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(LcSuggestion record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(LcSuggestion record) {
		// TODO Auto-generated method stub
		return 0;
	}

}
