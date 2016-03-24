package com.etaofinance.api.service.inter;

import com.etaofinance.entity.Suggestion;
import com.etaofinance.entity.ZcSuggestion;
import com.etaofinance.entity.common.HttpResultModel;
import com.etaofinance.entity.common.PagedResponse;
import com.etaofinance.entity.req.PagedZcSuggestionReq;

public interface ISuggestionService {
	int insert(Suggestion record);

	int insertZcSuggestion(ZcSuggestion record);
	
	PagedResponse<ZcSuggestion> queryZcSuggestionList(PagedZcSuggestionReq req);
	
	HttpResultModel<ZcSuggestion> showview(int id);
}
