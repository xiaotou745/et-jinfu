package com.etaofinance.api.dao.impl;

import org.springframework.stereotype.Repository;

import com.etaofinance.api.common.DaoBase;
import com.etaofinance.api.dao.inter.IZcSuggestionDao;
import com.etaofinance.entity.ZcSuggestion;
import com.etaofinance.entity.common.PagedResponse;
import com.etaofinance.entity.req.PagedZcSuggestionReq;

@Repository
public class ZcSuggestionDao extends DaoBase implements IZcSuggestionDao {

	@Override
	public PagedResponse<ZcSuggestion> queryZcSuggestionList(PagedZcSuggestionReq req) {
		return getReadOnlySqlSessionUtil().selectPageList("IZcSuggestionDao.querySuggestionList", req);
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(ZcSuggestion record) {
		// TODO Auto-generated method stub
		return getMasterSqlSessionUtil().insert("IZcSuggestionDao.insert",
				record);
	}

	@Override
	public int insertSelective(ZcSuggestion record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ZcSuggestion selectByPrimaryKey(Integer id) {
		return getReadOnlySqlSessionUtil().selectOne("IZcSuggestionDao.selectByPrimaryKey",id);
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
