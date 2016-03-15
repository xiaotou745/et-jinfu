package com.etaofinance.apihttp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etaofinance.api.service.impl.*;
import com.etaofinance.api.service.inter.IRoleInfoService;
import com.etaofinance.apihttp.service.inter.IAccountService;
import com.etaofinance.entity.RoleInfo;
import com.etaofinance.entity.req.*;
import com.etaofinance.entity.common.HttpResultModel;
/*
 * 账号相关
 * 
 * 2016年3月9日10:34:21
 * */
@Service
public class AccountService implements IAccountService {
	@Autowired
	private IRoleInfoService roleInfoService;

	@Override
	public RoleInfo selectByPrimaryKey(RoleInfo req) {
		return null;
	}

	





}
