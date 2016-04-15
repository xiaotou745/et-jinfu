package com.etaofinance.api.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.etaofinance.api.common.TransactionalRuntimeException;
import com.etaofinance.api.dao.inter.IADVertDao;
import com.etaofinance.api.dao.inter.IAccountAuthDao;
import com.etaofinance.api.dao.inter.IBalanceRecordDao;
import com.etaofinance.api.dao.inter.IBankCardDao;
import com.etaofinance.api.dao.inter.IBankDao;
import com.etaofinance.api.dao.inter.IMemberOtherDao;
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
import com.etaofinance.core.enums.BalanceRecordType;
import com.etaofinance.core.enums.BankCardEnum;
import com.etaofinance.core.enums.MemberEnum;
import com.etaofinance.core.enums.WithdrawStatus;
import com.etaofinance.core.enums.WithdrawformEnum;
import com.etaofinance.core.enums.returnenums.HttpReturnRnums;
import com.etaofinance.core.util.OrderNoHelper;
import com.etaofinance.core.util.PropertyUtils;
import com.etaofinance.entity.ADVert;
import com.etaofinance.entity.AccountAuth;
import com.etaofinance.entity.AccountInfo;
import com.etaofinance.entity.BalanceRecord;
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
import com.etaofinance.entity.req.PagedWithdrawReq;
import com.etaofinance.entity.req.PublicMemberReq;
import com.etaofinance.entity.resp.ADVertResp;
import com.etaofinance.entity.resp.MemberResp;


@Service
public class WithdrawformService implements IWithdrawformService{


	@Autowired
	private IWithdrawformDao withdrawformDao;

	@Autowired
	private IBalanceRecordDao balanceRecordDao;
	
	@Autowired
	private IMemberOtherDao memberOtherDao;
	
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
		record.setAccounttype("1");
		//临时
		record.setAccountno("x000000001");		
		int row= withdrawformDao.insertSelective(record);		
		if(row<=0)
		{
			resp.setCode(WithdrawformEnum.Err.value());
			resp.setMsg(WithdrawformEnum.Err.desc());
			return resp;	
		}
		
		resp.setCode(WithdrawformEnum.Success.value());
		resp.setMsg(WithdrawformEnum.Success.desc());	
		String basePath = PropertyUtils.getProperty("java.wap.url");
		basePath+="/me/accountblance";	
		resp.setUrl(basePath);
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

	@Override
	public PagedResponse<Withdrawform> getWithdrawList(PagedWithdrawReq req) {
		return withdrawformDao.getWithdrawList(req);
	}

	@Override
	public int updateByPrimaryKeySelective(Withdrawform record) {
		
		return withdrawformDao.updateByPrimaryKeySelective(record);
	}

	/**
	 *  提现审核
	 */
	@Override
	@Transactional(rollbackFor = Exception.class, timeout = 30)
	public int Audit(long id, short status) {
		
		// 1 拒绝
		// 1.1 update withdrawform	
		int updateWithdrawRes =0;
		
		Withdrawform withdraw = new Withdrawform();
		withdraw.setId(id);
		withdraw.setStatus(status);
		
		updateWithdrawRes = this.updateByPrimaryKeySelective(withdraw);
		
		if(0==updateWithdrawRes && status == WithdrawStatus.Refuse.value()){

			throw new TransactionalRuntimeException("拒绝失败");
		}
		else if(1==updateWithdrawRes && status == WithdrawStatus.Refuse.value()){
			
			return 1;	
		}
		
		// 2 通过
		// 2.1 update withdrawform
	
		if(0==updateWithdrawRes){
			throw new TransactionalRuntimeException("通过失败");
		}
		// 2.2 insert balancerecord
		 // 获取 withdraw
		Withdrawform withdrawMd = this.getWithdrawMdById(id);
		
		BalanceRecord balance =new BalanceRecord();

		balance.setId(null);
		balance.setAmount(-withdrawMd.getAmount());
		balance.setAfteramount(- withdrawMd.getAmount());
		balance.setMemberid(withdrawMd.getMemberid());
		balance.setRemark("提现审核");
		balance.setTypeid((short) BalanceRecordType.Apply.value());
		balance.setRelationno(withdrawMd.getWithwardno());
		balance.setOptname("optName");
		
		int insertBalanceRes = balanceRecordDao.insertSelective(balance);
		if (0 == insertBalanceRes) {

			throw new TransactionalRuntimeException("流水表异常");
		}

		// 2.3 update memberother
		int updateMemberOterRes = memberOtherDao.updateMemberOther(withdrawMd.getMemberid(),
				-withdrawMd.getAmount(),withdrawMd.getAmount());
		if (0 == updateMemberOterRes) {

			throw new TransactionalRuntimeException("余额表异常");
		}
		
		return 1;
	}

	private Withdrawform getWithdrawMdById(long id) {
		
		return withdrawformDao.selectByPrimaryKey(id);
		
	}

}
