package com.etaofinance.api.dao.inter;

import com.etaofinance.entity.AdminOptLog;

public interface IAdminOptLogDao {
    int deleteByPrimaryKey(Long id);

    int insert(AdminOptLog record);

    int insertSelective(AdminOptLog record);

    AdminOptLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AdminOptLog record);

    int updateByPrimaryKey(AdminOptLog record);
}