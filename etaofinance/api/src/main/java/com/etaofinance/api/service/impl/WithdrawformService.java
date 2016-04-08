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
import com.etaofinance.api.dao.inter.IWithdrawformDao;
import com.etaofinance.api.redis.RedisService;
import com.etaofinance.api.service.inter.IADVertService;
import com.etaofinance.api.service.inter.IAccountAuthService;
import com.etaofinance.api.service.inter.IBankCardService;
import com.etaofinance.api.service.inter.IBankService;
import com.etaofinance.api.service.inter.IWithdrawformService;
import com.etaofinance.core.consts.RedissCacheKey;
import com.etaofinance.core.enums.ADVertEnum;
import com.etaofinance.core.enums.BalanceRecordEnum;
import com.etaofinance.core.enums.BankCardEnum;
import com.etaofinance.core.enums.MemberEnum;
import com.etaofinance.core.enums.WithdrawformEnum;
import com.etaofinance.core.util.OrderNoHelper;
import com.etaofinance.entity.ADVert;
import com.etaofinance.entity.AccountAuth;
import com.etaofinance.entity.AccountInfo;
import com.etaofinance.entity.Bank;
import com.etaofinance.entity.BankCard;
import com.etaofinance.entity.Member;
import com.etaofinance.entity.Withdrawform;
import com.etaofinance.entity.common.HttpResultModel;
import com.etaofinance.entity.common.PagedResponse;
import com.etaofinance.entity.common.ResponseBase;
import com.etaofinance.entity.domain.BalanceRecordDM;
import com.etaofinance.entity.domain.WithdrawformDM;
import com.etaofinance.entity.req.PagedADVertReq;
import com.etaofinance.entity.req.PublicMemberReq;
import com.etaofinance.entity.resp.ADVertResp;
import com.etaofinance.entity.resp.MemberResp;


@Service
public class WithdrawformService implements IWithdrawformService{


	@Autowired
	private IWithdrawformDao withdrawformDao;

	@Override
	public HttpResultModel<Object> create(Withdrawform record) {
		//问题，对接银行， 提现验证，提现后金额
		
		HttpResultModel<Object> resp = new HttpResultModel<Object>();		
		
		if(record.getMemberid() ==null && record.getMemberid().equals(""))
		{	
			resp.setCode(WithdrawformEnum.MemberIdIsNull.value());
			resp.setMsg(WithdrawformEnum.MemberIdIsNull.desc());
			return resp;			
		}		
		record.setWithwardno(OrderNoHelper.generateOrderCode(record.getMemberid().intValue()));
		record.setStatus((short)1);
		record.setCreatetime(new Date());
		record.setCreatename("admin");	 	
		record.setAfteramount(0.0F);
		int row= withdrawformDao.insertSelective(record);		
		if(row<=0)
		{
			resp.setCode(WithdrawformEnum.Err.value());
			resp.setMsg(WithdrawformEnum.Err.desc());
			return resp;	
		}
		resp.setCode(WithdrawformEnum.Success.value());
		resp.setMsg(WithdrawformEnum.Success.desc());		
		return resp;
	}

	@Override
	public List<WithdrawformDM> getListMore(PublicMemberReq record) {
		return withdrawformDao.getListMore(record);
	}

	@Override
	public  HttpResultModel<WithdrawformDM>  selectWFDetail(PublicMemberReq record) {
		
		HttpResultModel<WithdrawformDM> resp = new HttpResultModel<WithdrawformDM>();		
		
		if(record.getMemberId() ==null || record.getMemberId().equals(""))
		{	
			resp.setCode(WithdrawformEnum.MemberIdIsNull.value());
			resp.setMsg(WithdrawformEnum.MemberIdIsNull.desc());
			return resp;			
		}		
		
		WithdrawformDM WFModel= withdrawformDao.selectDMByPrimaryKey(record.getCurrId());		
		if(WFModel==null)
		{
			resp.setCode(WithdrawformEnum.Err.value());
			resp.setMsg(WithdrawformEnum.Err.desc());
			return resp;	
		}
		resp.setData(WFModel);
		resp.setCode(WithdrawformEnum.Success.value());
		resp.setMsg(WithdrawformEnum.Success.desc());		
		return resp;				

	}	


}
