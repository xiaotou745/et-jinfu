package com.etaofinance.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etaofinance.api.dao.inter.IRoleInfoDao;
import com.etaofinance.api.dao.inter.ISuggestionDao;
import com.etaofinance.api.dao.inter.IZcSuggestionDao;
import com.etaofinance.api.service.inter.ISuggestionService;
import com.etaofinance.core.enums.ZcSuggestionEnum;
import com.etaofinance.entity.Suggestion;
import com.etaofinance.entity.ZcSuggestion;
import com.etaofinance.entity.common.HttpResultModel;
import com.etaofinance.entity.common.PagedResponse;
import com.etaofinance.entity.req.PagedZcSuggestionReq;

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

	@Override
	public PagedResponse<ZcSuggestion> queryZcSuggestionList(
			PagedZcSuggestionReq req) {
		return zcSuggestionDao.queryZcSuggestionList(req);
	}
	
	@Override
	public HttpResultModel<ZcSuggestion> showview(int id) {
		HttpResultModel<ZcSuggestion> resp = new HttpResultModel<ZcSuggestion>();
		resp.setData(zcSuggestionDao.selectByPrimaryKey(id)); 
		resp.setCode(ZcSuggestionEnum.Success.value());
		resp.setMsg(ZcSuggestionEnum.Success.desc());		
		return resp;
	}

}
