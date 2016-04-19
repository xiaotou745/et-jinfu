package com.etaofinance.api.service.inter;

import java.util.List;

import com.etaofinance.entity.RoleInfo;



public interface IRoleInfoService {
    int insert(RoleInfo record);

    int update(RoleInfo record);
    List<RoleInfo> selectList();
    
    
    RoleInfo getRoleInfoByName(String roleName);
}
