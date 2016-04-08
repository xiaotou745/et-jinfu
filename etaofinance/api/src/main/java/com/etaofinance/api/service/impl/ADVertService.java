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
import com.etaofinance.core.util.JsonUtil;
import com.etaofinance.entity.ADVert;
import com.etaofinance.entity.AccountAuth;
import com.etaofinance.entity.AccountInfo;
import com.etaofinance.entity.common.HttpResultModel;
import com.etaofinance.entity.common.PagedResponse;
import com.etaofinance.entity.req.PagedADVertReq;
import com.etaofinance.entity.resp.ADVertResp;


@Service
public class ADVertService implements IADVertService{


	private static final int ArrayList = 0;
	private static final int ADVert = 0;
	@Autowired
	private IADVertDao aDVertDao;
	@Autowired
	RedisService redisservice;

	
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		return aDVertDao.deleteByPrimaryKey(id);
	}


	@Override
	public ADVert selectByPrimaryKey(Integer id) {
		return aDVertDao.selectByPrimaryKey(id);
	}


	@Override
	public PagedResponse<ADVert> query(PagedADVertReq req) {
		// TODO Auto-generated method stub
		return aDVertDao.query(req);
	}

	@Override
	public HttpResultModel<ADVertResp> create(ADVert record) {
		HttpResultModel<ADVertResp> resp = new HttpResultModel<ADVertResp>();		
		aDVertDao.insertSelective(record);
		resp.setCode(ADVertEnum.Success.value());
		resp.setMsg(ADVertEnum.Success.desc());		
		return resp;
	}
	
	@Override
	public  HttpResultModel<ADVertResp> modify(ADVert record)
	{
		HttpResultModel<ADVertResp> resp = new HttpResultModel<ADVertResp>();

		
		aDVertDao.updateByPrimaryKeySelective(record);
		resp.setCode(ADVertEnum.Success.value());
		resp.setMsg(ADVertEnum.Success.desc());		
		return resp;
	}

	/**
	 * 获取轮播图
	 */
	@Override
	public List<ADVert> getListForWap() {
		String json=redisservice.get(RedissCacheKey.JF_ADvertList, String.class);
		List<ADVert> list=null;
		if(json==null||json.equals(""))
		{
			list=aDVertDao.getListForWap();
			json=JsonUtil.obj2string(list);
			redisservice.set(RedissCacheKey.JF_ADvertList, json,60*60*24*3);
		}
		else
		{
			list=JsonUtil.str2list(json, ADVert.class);
		}
		return list;
	}
	

}
