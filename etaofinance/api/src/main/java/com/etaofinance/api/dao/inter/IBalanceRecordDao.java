package com.etaofinance.api.dao.inter;

import java.util.List;

import com.etaofinance.entity.BalanceRecord;
import com.etaofinance.entity.domain.BalanceRecordDM;

public interface IBalanceRecordDao {
    int deleteByPrimaryKey(Long id);

    int insert(BalanceRecord record);

    int insertSelective(BalanceRecord record);

    BalanceRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BalanceRecord record);

    int updateByPrimaryKey(BalanceRecord record);
    
    List<BalanceRecordDM> getListMore(BalanceRecord record);
    
    BalanceRecordDM selectDMByPrimaryKey(Long id);
}