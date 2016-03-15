package com.etaofinance.api.service.inter;

import java.util.List;

import com.etaofinance.entity.RoleAuth;



public interface IRoleAuthService {
	/**
	 * 修改给定角色的权限列表
	 * @author hailongzhao
	 * @date 20150902
	 * @return
	 */
	public boolean modifyAuthList(List<RoleAuth> deleteList,List<RoleAuth> insertList) ;
}
