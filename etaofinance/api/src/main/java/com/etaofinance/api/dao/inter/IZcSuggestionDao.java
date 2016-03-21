package com.etaofinance.api.dao.inter;

import com.etaofinance.entity.Project;
import com.etaofinance.entity.ZcSuggestion;
import com.etaofinance.entity.common.PagedResponse;
import com.etaofinance.entity.req.PagedProjectReq;
import com.etaofinance.entity.req.PagedZcSuggestionReq;

public interface IZcSuggestionDao {
    int deleteByPrimaryKey(Integer id);

    int insert(ZcSuggestion record);

    int insertSelective(ZcSuggestion record);

    ZcSuggestion selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ZcSuggestion record);

    int updateByPrimaryKey(ZcSuggestion record);
    
    PagedResponse<ZcSuggestion>  queryZcSuggestionList(PagedZcSuggestionReq req);
}