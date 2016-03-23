package com.etaofinance.api.dao.inter;

import java.util.List;

import com.etaofinance.entity.Bank;

public interface IBankDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Bank record);

    int insertSelective(Bank record);

    Bank selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Bank record);

    int updateByPrimaryKey(Bank record);
    
    List<Bank> getBankList();
}