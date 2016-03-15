package com.etaofinance.api.dao.inter;

import com.etaofinance.entity.MemberApply;

public interface IMemberApplyDao {
    int deleteByPrimaryKey(Long id);

    int insert(MemberApply record);

    int insertSelective(MemberApply record);

    MemberApply selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MemberApply record);

    int updateByPrimaryKey(MemberApply record);
}