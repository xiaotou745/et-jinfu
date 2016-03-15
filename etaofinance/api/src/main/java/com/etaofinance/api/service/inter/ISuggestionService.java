package com.etaofinance.api.service.inter;

import com.etaofinance.entity.Suggestion;
import com.etaofinance.entity.ZcSuggestion;

public interface ISuggestionService {
	int insert(Suggestion record);

	int insertZcSuggestion(ZcSuggestion record);
}
