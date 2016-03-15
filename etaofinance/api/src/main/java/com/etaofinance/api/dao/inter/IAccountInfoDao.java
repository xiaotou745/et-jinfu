package com.etaofinance.api.dao.inter;

import java.util.List;

import com.etaofinance.entity.AccountInfo;
import com.etaofinance.entity.common.PagedResponse;
import com.etaofinance.entity.req.PagedAccountInfoReq;
import com.etaofinance.entity.req.UpdatePwdReq;



public interface IAccountInfoDao {
	public  PagedResponse<AccountInfo>  queryAccount(PagedAccountInfoReq req);
	AccountInfo login(String username,String password);
	AccountInfo getByID(int userID);
	int updateRoleID(int userID,int newRoleID);
	List<AccountInfo> getByRoleID(int roleID);
	int insert(AccountInfo account);
	int update(AccountInfo account);
	int updatePwd(UpdatePwdReq req);
}