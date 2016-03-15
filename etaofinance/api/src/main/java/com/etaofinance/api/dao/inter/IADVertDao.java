package com.etaofinance.api.dao.inter;

import com.etaofinance.entity.ADVert;

public interface IADVertDao {
    int deleteByPrimaryKey(Integer id);

    int insert(ADVert record);

    int insertSelective(ADVert record);

    ADVert selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ADVert record);

    int updateByPrimaryKey(ADVert record);
}