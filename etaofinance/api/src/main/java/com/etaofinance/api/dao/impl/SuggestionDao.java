package com.etaofinance.api.dao.impl;

import org.springframework.stereotype.Repository;

import com.etaofinance.api.common.DaoBase;
import com.etaofinance.api.dao.inter.ISuggestionDao;
import com.etaofinance.entity.Suggestion;

@Repository
public class SuggestionDao extends DaoBase implements ISuggestionDao {

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(Suggestion record) {
		return getMasterSqlSessionUtil()
				.insert("ISuggestionDao.insert", record);
	}

	@Override
	public int insertSelective(Suggestion record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Suggestion selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(Suggestion record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(Suggestion record) {
		// TODO Auto-generated method stub
		return 0;
	}

}
