package com.etaofinance.api.service.inter;

import java.util.List;

import com.etaofinance.entity.ADVert;
import com.etaofinance.entity.AccountInfo;
import com.etaofinance.entity.BalanceRecord;
import com.etaofinance.entity.Bank;
import com.etaofinance.entity.Withdrawform;
import com.etaofinance.entity.common.HttpResultModel;
import com.etaofinance.entity.common.PagedResponse;
import com.etaofinance.entity.domain.BalanceRecordDM;
import com.etaofinance.entity.req.PagedBalancerecordReq;
import com.etaofinance.entity.req.PagedMemberBalanceRecordReq;
import com.etaofinance.entity.req.PagedADVertReq;
import com.etaofinance.entity.req.PagedAccountInfoReq;

import com.etaofinance.entity.req.PagedWithdrawReq;
import com.etaofinance.entity.req.PublicMemberReq;
import com.etaofinance.entity.req.UpdatePwdReq;
import com.etaofinance.entity.resp.*;


public interface IBalanceRecordService {
   

	List<BalanceRecordDM> getListMore(PublicMemberReq record);	
	
	HttpResultModel<BalanceRecordDM> selectBRDetail(PublicMemberReq record);

	PagedResponse<BalanceRecord> getPageList(PagedMemberBalanceRecordReq req);
	
	PagedResponse<BalanceRecord>  getBalanceRecordList(PagedBalancerecordReq req);
	
}
