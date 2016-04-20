package com.etaofinance.api.dao.inter;

import java.util.List;

import com.etaofinance.entity.RoleInfo;



public interface IRoleInfoDao {
    int insert(RoleInfo record);

    int update(RoleInfo record);
    List<RoleInfo> selectList();
    RoleInfo getRoleInfoByName(String roleName);
}