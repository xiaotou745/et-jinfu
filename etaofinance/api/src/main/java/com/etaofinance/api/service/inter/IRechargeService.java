package com.etaofinance.api.service.inter;

import com.etaofinance.entity.Recharge;

public interface IRechargeService {
    int deleteByPrimaryKey(Long id);

    int insert(Recharge record);

    int insertSelective(Recharge record);

    Recharge selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Recharge record);

    int updateByPrimaryKey(Recharge record);
}