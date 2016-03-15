package com.etaofinance.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etaofinance.api.dao.inter.IRoleInfoDao;
import com.etaofinance.api.dao.inter.ISuggestionDao;
import com.etaofinance.api.dao.inter.IZcSuggestionDao;
import com.etaofinance.api.service.inter.ISuggestionService;
import com.etaofinance.entity.Suggestion;
import com.etaofinance.entity.ZcSuggestion;

@Service
public class SuggestionService implements ISuggestionService {

	@Autowired
	ISuggestionDao suggestionDao;

	@Autowired
	IZcSuggestionDao zcSuggestionDao;

	@Override
	public int insert(Suggestion record) {
		return suggestionDao.insert(record);
	}

	@Override
	public int insertZcSuggestion(ZcSuggestion record) {
		return zcSuggestionDao.insert(record);
	}
}
