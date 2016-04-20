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
import com.etaofinance.core.enums.RechargeStatus;
import com.etaofinance.core.enums.WithdrawStatus;
import com.etaofinance.core.enums.WithdrawformEnum;
import com.etaofinance.core.enums.WithdrawformStatus;
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
import com.etaofinance.entity.MemberOther;
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
	
	@Autowired
	private IBankCardDao bankCardDao;
	
	@Override
	@Transactional(rollbackFor = Exception.class, timeout = 30)
	public HttpResultModel<Object> create(Withdrawform record) {	
		HttpResultModel<Object> resp = new HttpResultModel<Object>();	
		
		if(record.getMemberid() ==null && record.getMemberid().equals(""))
		{	
			resp.setCode(WithdrawformEnum.MemberIdIsNull.value());
			resp.setMsg(WithdrawformEnum.MemberIdIsNull.desc());
			return resp;			
		}		
		 List<BankCard> bankCardList= bankCardDao.getListByMemberId(record.getMemberid());
		 if(bankCardList==null ||bankCardList.size()<1)
		 {
				resp.setCode(WithdrawformEnum.BindCardIsNull.value());
				resp.setMsg(WithdrawformEnum.BindCardIsNull.desc());
				return resp;
		 }
		
		//写入提现表
		String uuid=OrderNoHelper.generatePrefixNoCode("TX", record.getMemberid().intValue());
		record.setWithwardno(uuid);
		record.setStatus((short) WithdrawformStatus.Status1.value());			 	
		record.setAccounttype("1");
		record.setAccountno(bankCardList.get(0).getCardno());
		record.setBankname(bankCardList.get(0).getBankname());	
		int rowWithdraw= withdrawformDao.insertSelective(record);		
		if(rowWithdraw<=0)
		{
			throw new TransactionalRuntimeException("提现表异常");	
		}
		//余额表
		MemberOther mbOther = new MemberOther();		
		mbOther.setMemberid(record.getMemberid());		
		mbOther.setBalanceprice(-record.getAmount());			
		int rowMemberOther = memberOtherDao.updateMemberOther(mbOther);
		if (rowMemberOther<=0) {
				throw new TransactionalRuntimeException("余额表异常");
		}
		
		//余额流水表
		BalanceRecord balance =new BalanceRecord();	
		balance.setAmount(-record.getAmount());	
		balance.setMemberid(record.getMemberid());
		balance.setWithwardid(record.getId());
		balance.setRemark("提现流水");
		balance.setTypeid((short) BalanceRecordType.Apply.value());
		balance.setRelationno(uuid);
		balance.setOptname(record.getCreatename());

		int rowBalanceRecord = balanceRecordDao.insertBalanceRecord(balance);		
		if (rowBalanceRecord<=0) {
			throw new TransactionalRuntimeException("流水表异常");
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
		Withdrawform withdraw = new Withdrawform();
		withdraw.setId(id);
		withdraw.setStatus(status);
		
		if(status == WithdrawStatus.Pass.value())
		{
			int rowUpdate= this.updateByPrimaryKeySelective(withdraw);
			if(rowUpdate<=0)
			{
				throw new TransactionalRuntimeException("通过失败");	
			}		
		}
		else
		{
			int rowUpdate= this.updateByPrimaryKeySelective(withdraw);
			if(rowUpdate<=0)
			{
				throw new TransactionalRuntimeException("拒绝失败");	
			}
			
			Withdrawform wfModel= withdrawformDao.selectByPrimaryKey(id);
			Long memberid=wfModel.getMemberid();	
			String createname=wfModel.getCreatename();
			//余额表
			MemberOther mbOther = new MemberOther();		
			mbOther.setMemberid(memberid);		
			mbOther.setBalanceprice(wfModel.getAmount());			
			int rowMemberOther = memberOtherDao.updateMemberOther(mbOther);
			if (rowMemberOther<=0) {
					throw new TransactionalRuntimeException("余额表异常");
			}
			
			//余额流水表
			BalanceRecord balance =new BalanceRecord();	
			balance.setAmount(wfModel.getAmount());	
			balance.setMemberid(memberid);
			balance.setWithwardid(id);
			balance.setRemark("提现审核拒绝退款");
			balance.setTypeid((short) BalanceRecordType.ApplyErr.value());
			balance.setRelationno(wfModel.getWithwardno());
			balance.setOptname(createname);

			int rowBalanceRecord = balanceRecordDao.insertBalanceRecord(balance);		
			if (rowBalanceRecord<=0) {
				throw new TransactionalRuntimeException("流水表异常");
			}
			
		}
	

		return 1;
	}

	private Withdrawform getWithdrawMdById(long id) {
		
		return withdrawformDao.selectByPrimaryKey(id);
		
	}
	
	public double  GetWithdrawPendingAmountByMbId(Long memberId)
	{
		return withdrawformDao.GetWithdrawPendingAmountByMbId(memberId);
	}

}
