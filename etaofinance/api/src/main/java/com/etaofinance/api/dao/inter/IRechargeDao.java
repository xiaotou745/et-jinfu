package com.etaofinance.api.dao.inter;

import com.etaofinance.entity.Recharge;

public interface IRechargeDao {
    int deleteByPrimaryKey(Long id);

    int insert(Recharge record);

    int insertSelective(Recharge record);

    Recharge selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Recharge record);

    int updateByPrimaryKey(Recharge record);
}