package com.etaofinance.api.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.etaofinance.api.dao.inter.IADVertDao;
import com.etaofinance.api.dao.inter.IAccountAuthDao;
import com.etaofinance.api.dao.inter.IProjectEnrollDao;
import com.etaofinance.api.redis.RedisService;
import com.etaofinance.api.service.inter.IADVertService;
import com.etaofinance.api.service.inter.IAccountAuthService;
import com.etaofinance.api.service.inter.IProjectEnrollService;
import com.etaofinance.core.consts.RedissCacheKey;
import com.etaofinance.core.enums.ADVertEnum;
import com.etaofinance.core.enums.BankCardEnum;
import com.etaofinance.core.enums.PublicEnum;
import com.etaofinance.entity.ADVert;
import com.etaofinance.entity.AccountAuth;
import com.etaofinance.entity.AccountInfo;
import com.etaofinance.entity.ProjectEnroll;
import com.etaofinance.entity.common.HttpResultModel;
import com.etaofinance.entity.common.PagedResponse;
import com.etaofinance.entity.common.ResponseBase;
import com.etaofinance.entity.req.PagedADVertReq;
import com.etaofinance.entity.resp.ADVertResp;


@Service
public class ProjectEnrollService implements IProjectEnrollService{


	@Autowired
	private IProjectEnrollDao projectEnrollDao;

	@Override
	public HttpResultModel<ResponseBase> create(ProjectEnroll record) {
		HttpResultModel<ResponseBase> resp = new HttpResultModel<ResponseBase>();
		
		record.setCreatetime(new Date());
		record.setCreatename("admin");	 		
		int row= projectEnrollDao.insertSelective(record);		
		if(row<=0)
		{
			resp.setCode(PublicEnum.Err.value());
			resp.setMsg(PublicEnum.Err.desc());
			return resp;	
		}
		resp.setCode(PublicEnum.Success.value());
		resp.setMsg(PublicEnum.Success.desc());		
		return resp;
	}


	
	
	
	

}
