package com.etaofinance.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.etaofinance.api.dao.inter.IADVertDao;
import com.etaofinance.api.dao.inter.IAccountAuthDao;
import com.etaofinance.api.redis.RedisService;
import com.etaofinance.api.service.inter.IADVertService;
import com.etaofinance.api.service.inter.IAccountAuthService;
import com.etaofinance.core.consts.RedissCacheKey;
import com.etaofinance.core.enums.ADVertEnum;
import com.etaofinance.entity.ADVert;
import com.etaofinance.entity.AccountAuth;
import com.etaofinance.entity.AccountInfo;
import com.etaofinance.entity.common.HttpResultModel;
import com.etaofinance.entity.common.PagedResponse;
import com.etaofinance.entity.req.PagedADVertReq;
import com.etaofinance.entity.resp.ADVertResp;


@Service
public class ADVertService implements IADVertService{


	@Autowired
	private IADVertDao aDVertDao;


	@Override
	public ADVert selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public PagedResponse<ADVert> query(PagedADVertReq req) {
		// TODO Auto-generated method stub
		return aDVertDao.query(req);
	}

	@Override
	public HttpResultModel<ADVertResp> add(ADVert record) {
		HttpResultModel<ADVertResp> resp = new HttpResultModel<ADVertResp>();		
		aDVertDao.insertSelective(record);
		resp.setCode(ADVertEnum.Success.value());
		resp.setMsg(ADVertEnum.Success.desc());		
		return resp;
	}
	

}
