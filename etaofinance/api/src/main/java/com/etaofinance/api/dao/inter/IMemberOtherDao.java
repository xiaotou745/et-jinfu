package com.etaofinance.api.dao.inter;

import java.util.List;

import com.etaofinance.entity.MemberOther;

public interface IMemberOtherDao {
    int deleteByPrimaryKey(Long id);

    int insert(MemberOther record);

    int insertSelective(MemberOther record);

    MemberOther selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MemberOther record);

    int updateByPrimaryKey(MemberOther record);
    
    
    int updateByMemberIdSelective(MemberOther record);
}