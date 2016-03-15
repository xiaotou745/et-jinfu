package com.etaofinance.api.dao.inter;

import com.etaofinance.entity.Suggestion;

public interface ISuggestionDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Suggestion record);

    int insertSelective(Suggestion record);

    Suggestion selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Suggestion record);

    int updateByPrimaryKey(Suggestion record);
}