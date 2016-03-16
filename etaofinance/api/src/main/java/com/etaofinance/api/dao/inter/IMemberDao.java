package com.etaofinance.api.dao.inter;

import com.etaofinance.entity.Member;

public interface IMemberDao {
    int deleteByPrimaryKey(Long id);

    int insert(Member record);

    int insertSelective(Member record);

    Member selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Member record);

    int updateByPrimaryKey(Member record);
    
    /**
     * 通过手机号获取会员信息
     * @param phoneno
     * @return
     */
    Member selectByPhoneNo(String  phoneno);
}