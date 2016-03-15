package com.etaofinance.api.dao.inter;

import com.etaofinance.entity.QA;

public interface IQADao {
    int deleteByPrimaryKey(Integer id);

    int insert(QA record);

    int insertSelective(QA record);

    QA selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(QA record);

    int updateByPrimaryKey(QA record);
}