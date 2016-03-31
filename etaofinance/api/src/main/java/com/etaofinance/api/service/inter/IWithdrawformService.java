package com.etaofinance.api.service.inter;

import java.util.List;

import com.etaofinance.entity.ADVert;
import com.etaofinance.entity.AccountInfo;
import com.etaofinance.entity.BalanceRecord;
import com.etaofinance.entity.Withdrawform;
import com.etaofinance.entity.common.HttpResultModel;
import com.etaofinance.entity.common.PagedResponse;
import com.etaofinance.entity.common.ResponseBase;
import com.etaofinance.entity.domain.BalanceRecordDM;
import com.etaofinance.entity.domain.WithdrawformDM;
import com.etaofinance.entity.req.PagedADVertReq;
import com.etaofinance.entity.req.PagedAccountInfoReq;
import com.etaofinance.entity.req.UpdatePwdReq;
import com.etaofinance.entity.resp.*;


public interface IWithdrawformService { 

	
	HttpResultModel<ResponseBase> create(Withdrawform record);

	List<WithdrawformDM> getListMore(Withdrawform record);	
	
	WithdrawformDM selectDMByPrimaryKey(Long id);
}