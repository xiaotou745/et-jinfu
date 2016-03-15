package com.etaofinance.api.dao.inter;

import com.etaofinance.entity.BalanceRecord;

public interface IBalanceRecordDao {
    int deleteByPrimaryKey(Long id);

    int insert(BalanceRecord record);

    int insertSelective(BalanceRecord record);

    BalanceRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BalanceRecord record);

    int updateByPrimaryKey(BalanceRecord record);
}