package com.etaofinance.api.dao.inter;

import java.util.List;

import com.etaofinance.entity.BankCard;


public interface IBankCardDao {
    int deleteByPrimaryKey(Integer id);

    int insert(BankCard record);

    int insertSelective(BankCard record);

    BankCard selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BankCard record);

    int updateByPrimaryKey(BankCard record);    

    List<BankCard> getListByMemberId(Long memberId);
	
}