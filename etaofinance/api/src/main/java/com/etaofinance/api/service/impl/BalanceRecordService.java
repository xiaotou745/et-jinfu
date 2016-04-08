package com.etaofinance.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.etaofinance.api.dao.inter.IADVertDao;
import com.etaofinance.api.dao.inter.IAccountAuthDao;
import com.etaofinance.api.dao.inter.IBalanceRecordDao;
import com.etaofinance.api.dao.inter.IBankDao;
import com.etaofinance.api.redis.RedisService;
import com.etaofinance.api.service.inter.IADVertService;
import com.etaofinance.api.service.inter.IAccountAuthService;
import com.etaofinance.api.service.inter.IBalanceRecordService;
import com.etaofinance.api.service.inter.IBankService;
import com.etaofinance.core.consts.RedissCacheKey;
import com.etaofinance.core.enums.ADVertEnum;
import com.etaofinance.core.enums.BalanceRecordEnum;
import com.etaofinance.core.enums.BankCardEnum;
import com.etaofinance.entity.ADVert;
import com.etaofinance.entity.AccountAuth;
import com.etaofinance.entity.AccountInfo;
import com.etaofinance.entity.BalanceRecord;
import com.etaofinance.entity.Bank;
import com.etaofinance.entity.common.HttpResultModel;
import com.etaofinance.entity.common.PagedResponse;
import com.etaofinance.entity.domain.BalanceRecordDM;
import com.etaofinance.entity.req.PagedBalancerecordReq;
import com.etaofinance.entity.req.PagedMemberBalanceRecordReq;
import com.etaofinance.entity.req.PagedADVertReq;
import com.etaofinance.entity.req.PublicMemberReq;
import com.etaofinance.entity.resp.ADVertResp;


@Service
public class BalanceRecordService implements IBalanceRecordService{


	@Autowired
	private IBalanceRecordDao balanceRecordDao;

	@Override
	public List<BalanceRecordDM> getListMore(PublicMemberReq record) {
		return balanceRecordDao.getListMore(record);
	}

	@Override
	public HttpResultModel<BalanceRecordDM> selectBRDetail(PublicMemberReq record)
	{
		HttpResultModel<BalanceRecordDM> resp = new HttpResultModel<BalanceRecordDM>();		
		
		if(record.getMemberId() ==null || record.getMemberId().equals(""))
		{	
			resp.setCode(BalanceRecordEnum.MemberIdIsNull.value());
			resp.setMsg(BalanceRecordEnum.MemberIdIsNull.desc());
			return resp;			
		}		
		
		BalanceRecordDM BRModel= balanceRecordDao.selectDMByPrimaryKey(record.getCurrId());		
		if(BRModel==null)
		{
			resp.setCode(BalanceRecordEnum.Err.value());
			resp.setMsg(BalanceRecordEnum.Err.desc());
			return resp;	
		}
		resp.setData(BRModel);
		resp.setCode(BalanceRecordEnum.Success.value());
		resp.setMsg(BalanceRecordEnum.Success.desc());		
		return resp;		
		
	}

	@Override
	public PagedResponse<BalanceRecord> getPageList(PagedMemberBalanceRecordReq req) {
		return balanceRecordDao.getPageList(req);
	}

	@Override
	public PagedResponse<BalanceRecord> getBalanceRecordList(
			PagedBalancerecordReq req) {
		return balanceRecordDao.getBalanceRecordList(req);
	}
	

}
