package com.etaofinance.api.dao.inter;

import com.etaofinance.entity.Withdrawform;

public interface IWithdrawformDao {
    int deleteByPrimaryKey(Long id);

    int insert(Withdrawform record);

    int insertSelective(Withdrawform record);

    Withdrawform selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Withdrawform record);

    int updateByPrimaryKey(Withdrawform record);
}