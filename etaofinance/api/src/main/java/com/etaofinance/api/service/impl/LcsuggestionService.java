package com.etaofinance.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etaofinance.api.dao.inter.ILcSuggestionDao;
import com.etaofinance.api.service.inter.ILcSuggestionService;
import com.etaofinance.entity.LcSuggestion;

@Service
public class LcsuggestionService implements ILcSuggestionService {

	@Autowired
	ILcSuggestionDao lcSuggestionDao;
	
	@Override
	public int insert(LcSuggestion record) {
		return lcSuggestionDao.insert(record);
	}

}
