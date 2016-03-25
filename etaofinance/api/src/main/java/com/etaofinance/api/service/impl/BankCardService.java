package com.etaofinance.api.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.etaofinance.api.dao.inter.IADVertDao;
import com.etaofinance.api.dao.inter.IAccountAuthDao;
import com.etaofinance.api.dao.inter.IBankCardDao;
import com.etaofinance.api.dao.inter.IBankDao;
import com.etaofinance.api.redis.RedisService;
import com.etaofinance.api.service.inter.IADVertService;
import com.etaofinance.api.service.inter.IAccountAuthService;
import com.etaofinance.api.service.inter.IBankCardService;
import com.etaofinance.api.service.inter.IBankService;
import com.etaofinance.core.consts.RedissCacheKey;
import com.etaofinance.core.enums.ADVertEnum;
import com.etaofinance.core.enums.BankCardEnum;
import com.etaofinance.core.enums.MemberEnum;
import com.etaofinance.entity.ADVert;
import com.etaofinance.entity.AccountAuth;
import com.etaofinance.entity.AccountInfo;
import com.etaofinance.entity.Bank;
import com.etaofinance.entity.BankCard;
import com.etaofinance.entity.Member;
import com.etaofinance.entity.common.HttpResultModel;
import com.etaofinance.entity.common.PagedResponse;
import com.etaofinance.entity.common.ResponseBase;
import com.etaofinance.entity.req.PagedADVertReq;
import com.etaofinance.entity.resp.ADVertResp;
import com.etaofinance.entity.resp.MemberResp;


@Service
public class BankCardService implements IBankCardService{


	@Autowired
	private IBankCardDao bankCardDao;

	@Override
	public HttpResultModel<ResponseBase> create(BankCard record) {
		//return bankCardDao.insert(record);
		HttpResultModel<ResponseBase> resp = new HttpResultModel<ResponseBase>();		
	
		if(record.getMemberid() ==null && record.getMemberid().equals(""))
		{	
			resp.setCode(BankCardEnum.MemberIdIsNull.value());
			resp.setMsg(BankCardEnum.MemberIdIsNull.desc());
			return resp;			
		}
		
		if(record.getBankid() ==null && record.getBankid().equals(""))
		{	
			resp.setCode(BankCardEnum.BankIdIsNull.value());
			resp.setMsg(BankCardEnum.BankIdIsNull.desc());
			return resp;			
		}
		
		if(record.getCardno() ==null && record.getCardno().equals(""))
		{	
			resp.setCode(BankCardEnum.CardNoIsNull.value());
			resp.setMsg(BankCardEnum.CardNoIsNull.desc());
			return resp;			
		}
		if(record.getCardname() ==null && record.getCardname().equals(""))
		{	
			resp.setCode(BankCardEnum.CardNameIsNull.value());
			resp.setMsg(BankCardEnum.CardNameIsNull.desc());
			return resp;			
		}	
		
		record.setCreatetime(new Date());
		record.setCreatename("admin");
		record.setIsdel(false);	  		
		int row= bankCardDao.insert(record);		
		if(row<=0)
		{
			resp.setCode(BankCardEnum.Err.value());
			resp.setMsg(BankCardEnum.Err.desc());
			return resp;	
		}
		resp.setCode(BankCardEnum.Success.value());
		resp.setMsg(BankCardEnum.Success.desc());		
		return resp;
	}

	@Override
	public int remove(Integer id) {
		return bankCardDao.deleteByPrimaryKey(id);
	}

	@Override
	public List<BankCard> getListByMemberId(Long memberId) {
		return bankCardDao.getListByMemberId(memberId);
	}


}
