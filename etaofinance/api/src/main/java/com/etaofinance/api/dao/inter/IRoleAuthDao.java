package com.etaofinance.api.dao.inter;

import java.util.List;

import com.etaofinance.entity.RoleAuth;



public interface IRoleAuthDao {
	public boolean deleteAuthList(List<RoleAuth> authList) ;
	public boolean insertAuthList(List<RoleAuth> authList) ;
}