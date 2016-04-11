package com.etaofinance.api.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.etaofinance.api.dao.inter.IADVertDao;
import com.etaofinance.api.dao.inter.IAccountAuthDao;
import com.etaofinance.api.dao.inter.IBankDao;
import com.etaofinance.api.dao.inter.IFeedBackDao;
import com.etaofinance.api.redis.RedisService;
import com.etaofinance.api.service.inter.IADVertService;
import com.etaofinance.api.service.inter.IAccountAuthService;
import com.etaofinance.api.service.inter.IBankService;
import com.etaofinance.api.service.inter.IFeedBackService;
import com.etaofinance.core.consts.RedissCacheKey;
import com.etaofinance.core.enums.ADVertEnum;
import com.etaofinance.core.enums.FeedBackEnum;
import com.etaofinance.core.enums.ProjectEnrollEnum;
import com.etaofinance.core.enums.PublicEnum;
import com.etaofinance.core.enums.QAEnum;
import com.etaofinance.entity.ADVert;
import com.etaofinance.entity.AccountAuth;
import com.etaofinance.entity.AccountInfo;
import com.etaofinance.entity.Bank;
import com.etaofinance.entity.FeedBack;
import com.etaofinance.entity.common.HttpResultModel;
import com.etaofinance.entity.common.PagedResponse;
import com.etaofinance.entity.req.PagedADVertReq;
import com.etaofinance.entity.req.PagedFeedBackReq;
import com.etaofinance.entity.resp.ADVertResp;
import com.etaofinance.entity.resp.FeedBackResp;
import com.etaofinance.entity.resp.QAResp;


@Service
public class FeedBackService implements IFeedBackService{


	@Autowired
	private IFeedBackDao feedBackDao;

	@Override
	public HttpResultModel<Object> create(FeedBack record) {	

		HttpResultModel<Object> resp = new HttpResultModel<Object>();		
		if(record.getDescription() ==null || record.getDescription().equals(""))
		{	
			resp.setCode(FeedBackEnum.DescriptionIsNull.value());
			resp.setMsg(FeedBackEnum.DescriptionIsNull.desc());
			return resp;	
		}
		if(record.getDescription().length()>500)
		{	
			resp.setCode(FeedBackEnum.DescriptionIsErr.value());
			resp.setMsg(FeedBackEnum.DescriptionIsErr.desc());
			return resp;	
		}
		if(record.getContacts() !=null &&  record.getContacts().length()>10)
		{	
			resp.setCode(FeedBackEnum.ContactsIsErr.value());
			resp.setMsg(FeedBackEnum.ContactsIsErr.desc());
			return resp;	
		}
		if(record.getPhoneno() !=null &&  record.getPhoneno().length()>12)
		{	
			resp.setCode(FeedBackEnum.PhoneNoIsErr.value());
			resp.setMsg(FeedBackEnum.PhoneNoIsErr.desc());
			return resp;	
		}
		if(record.getEmail() !=null &&  record.getEmail().length()>30)
		{	
			resp.setCode(FeedBackEnum.EMailIsErr.value());
			resp.setMsg(FeedBackEnum.EMailIsErr.desc());
			return resp;	
		}
		
		record.setCreatetime(new Date());
		record.setIsdel(false);	      
		record.setCreatename("admin");	 
		int row= feedBackDao.insertSelective(record);		
		if(row<=0)
		{
			resp.setCode(FeedBackEnum.Err.value());
			resp.setMsg(FeedBackEnum.Err.desc());
			return resp;	
		}		
		resp.setCode(FeedBackEnum.Success.value());
		resp.setMsg(FeedBackEnum.Success.desc());		
		return resp;
	}

	@Override
	public PagedResponse<FeedBack> getFeedBackList(PagedFeedBackReq req) {
		return feedBackDao.getFeedBackList(req);
	}

	@Override
	public int remove(Integer id) {
		FeedBack record = new FeedBack();
		record.setId(id);
		record.setIsdel(true);
		return feedBackDao.updateByPrimaryKeySelective(record);
	}



}
