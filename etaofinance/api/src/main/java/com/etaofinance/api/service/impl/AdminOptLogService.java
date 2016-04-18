package com.etaofinance.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.etaofinance.api.dao.inter.IADVertDao;
import com.etaofinance.api.dao.inter.IAccountAuthDao;
import com.etaofinance.api.dao.inter.IAdminOptLogDao;
import com.etaofinance.api.redis.RedisService;
import com.etaofinance.api.service.inter.IADVertService;
import com.etaofinance.api.service.inter.IAccountAuthService;
import com.etaofinance.api.service.inter.IAdminOptLogService;
import com.etaofinance.core.consts.RedissCacheKey;
import com.etaofinance.core.enums.ADVertEnum;
import com.etaofinance.core.util.JsonUtil;
import com.etaofinance.entity.ADVert;
import com.etaofinance.entity.AccountAuth;
import com.etaofinance.entity.AccountInfo;
import com.etaofinance.entity.AdminOptLog;
import com.etaofinance.entity.common.HttpResultModel;
import com.etaofinance.entity.common.PagedResponse;
import com.etaofinance.entity.req.PagedADVertReq;
import com.etaofinance.entity.req.PagedAdminoptLogReq;
import com.etaofinance.entity.resp.ADVertResp;


@Service
public class AdminOptLogService implements IAdminOptLogService{


@Autowired
private IAdminOptLogDao adminOptLogDao;

@Override
public int insertSelective(AdminOptLog record) {

	return  adminOptLogDao.insertSelective(record);
	
	
}

@Override
public PagedResponse<AdminOptLog> getAdminOptLogList(PagedAdminoptLogReq req) {
	return adminOptLogDao.getAdminOptLogList(req);
}



}
