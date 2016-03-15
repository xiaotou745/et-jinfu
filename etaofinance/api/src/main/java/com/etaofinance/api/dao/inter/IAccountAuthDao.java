package com.etaofinance.api.dao.inter;

import java.util.List;

import com.etaofinance.entity.AccountAuth;
import com.etaofinance.entity.RoleAuth;

public interface IAccountAuthDao {
	List<Integer> getMenuIdsByAccountId(Integer id);
	public boolean deleteAuthList(List<AccountAuth> authList) ;
	public boolean insertAuthList(List<AccountAuth> authList) ;
}