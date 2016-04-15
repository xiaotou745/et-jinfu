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
import com.etaofinance.core.enums.ProjectEnrollEnum;
import com.etaofinance.core.enums.PublicEnum;
import com.etaofinance.entity.ADVert;
import com.etaofinance.entity.AccountAuth;
import com.etaofinance.entity.AccountInfo;
import com.etaofinance.entity.ProjectEnroll;
import com.etaofinance.entity.common.HttpResultModel;
import com.etaofinance.entity.common.PagedResponse;
import com.etaofinance.entity.common.ResponseBase;
import com.etaofinance.entity.req.PagedADVertReq;
import com.etaofinance.entity.req.PagedProjectEnrollReq;
import com.etaofinance.entity.resp.ADVertResp;


@Service
public class ProjectEnrollService implements IProjectEnrollService{


	@Autowired
	private IProjectEnrollDao projectEnrollDao;

	@Override
	public HttpResultModel<Object> create(ProjectEnroll record) {
		HttpResultModel<Object> resp = new HttpResultModel<Object>();
		if(record.getTitle() ==null || record.getTitle().equals(""))
		{	
			resp.setCode(ProjectEnrollEnum.TitleIsNull.value());
			resp.setMsg(ProjectEnrollEnum.TitleIsNull.desc());
			return resp;	
		}
		if(record.getTitle().length()>15)
		{	
			resp.setCode(ProjectEnrollEnum.TitleIsLong.value());
			resp.setMsg(ProjectEnrollEnum.TitleIsLong.desc());
			return resp;	
		}
		if(record.getOwnedindustry() ==null && record.getOwnedindustry().equals(""))
		{	
			resp.setCode(ProjectEnrollEnum.OwnedIndustryIsNull.value());
			resp.setMsg(ProjectEnrollEnum.OwnedIndustryIsNull.desc());
			return resp;	
		}		
		
		//未登陆
		if(record.getMemberid() ==null || record.getMemberid().equals(""))
		{	
			if(record.getContacts() ==null || record.getContacts().equals(""))
			{	
				resp.setCode(ProjectEnrollEnum.ContactsIsNull.value());
				resp.setMsg(ProjectEnrollEnum.ContactsIsNull.desc());
				return resp;	
			}
			if(record.getContacts().length()<2 ||record.getContacts().length()>10)
			{	
				resp.setCode(ProjectEnrollEnum.ContactsIsErr.value());
				resp.setMsg(ProjectEnrollEnum.ContactsIsErr.desc());
				return resp;	
			}			
			
			if(record.getPhoneno() ==null || record.getPhoneno().equals(""))
			{	
				resp.setCode(ProjectEnrollEnum.PhoneNoIsNull.value());
				resp.setMsg(ProjectEnrollEnum.PhoneNoIsNull.desc());
				return resp;	
			}
			if(record.getPhoneno().length()>12)
			{	
				resp.setCode(ProjectEnrollEnum.PhoneNoIsErr.value());
				resp.setMsg(ProjectEnrollEnum.PhoneNoIsErr.desc());
				return resp;	
			}
			
			if(record.getEmail() ==null || record.getEmail().equals(""))
			{	
				resp.setCode(ProjectEnrollEnum.EMailIsNull.value());
				resp.setMsg(ProjectEnrollEnum.EMailIsNull.desc());
				return resp;	
			}
			if(record.getEmail().length()>30)
			{	
				resp.setCode(ProjectEnrollEnum.EMailIsErr.value());
				resp.setMsg(ProjectEnrollEnum.EMailIsErr.desc());
				return resp;	
			}	
		}
		
		record.setCreatetime(new Date());
		record.setCreatename("admin");	 		
		int row= projectEnrollDao.insertSelective(record);		
		if(row<=0)
		{
			resp.setCode(ProjectEnrollEnum.Err.value());
			resp.setMsg(ProjectEnrollEnum.Err.desc());
			return resp;	
		}
		resp.setCode(ProjectEnrollEnum.Success.value());
		resp.setMsg(ProjectEnrollEnum.Success.desc());		
		return resp;
	}

	@Override
	public PagedResponse<ProjectEnroll> getProjectEnrollList(
			PagedProjectEnrollReq req) {
	
		
		PagedResponse<ProjectEnroll> projectEnrollRes = null;
		
		
		projectEnrollRes = projectEnrollDao.getProjectEnrollList(req);
		
		return projectEnrollRes;
	}


	
	
	
	

}
