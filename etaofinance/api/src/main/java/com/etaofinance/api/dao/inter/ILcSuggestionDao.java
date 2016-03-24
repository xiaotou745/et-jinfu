package com.etaofinance.api.dao.inter;

import com.etaofinance.entity.LcSuggestion;

public interface ILcSuggestionDao {
    int deleteByPrimaryKey(Integer id);

    int insert(LcSuggestion record);

    int insertSelective(LcSuggestion record);

    LcSuggestion selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LcSuggestion record);

    int updateByPrimaryKey(LcSuggestion record);
}