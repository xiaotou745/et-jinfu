package com.etaofinance.api.service.inter;

import java.util.List;

import com.etaofinance.entity.AccountAuth;
import com.etaofinance.entity.RoleAuth;

public interface IAccountAuthService {
	List<Integer> getMenuIdsByAccountId(Integer id);
	/**
	 * 修改给定用户的权限列表
	 * @author hailongzhao
	 * @date 20150901
	 * @return
	 */
	public boolean modifyAuthList(List<AccountAuth> deleteList,List<AccountAuth> insertList) ;
}
