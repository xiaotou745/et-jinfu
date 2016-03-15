package com.etaofinance.api.dao.inter;

import com.etaofinance.entity.ZcSuggestion;

public interface IZcSuggestionDao {
    int deleteByPrimaryKey(Integer id);

    int insert(ZcSuggestion record);

    int insertSelective(ZcSuggestion record);

    ZcSuggestion selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ZcSuggestion record);

    int updateByPrimaryKey(ZcSuggestion record);
}