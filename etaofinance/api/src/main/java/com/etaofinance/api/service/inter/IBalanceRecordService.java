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
<<<<<<< HEAD
import com.etaofinance.entity.req.PagedWithdrawReq;
=======
import com.etaofinance.entity.req.PublicMemberReq;
>>>>>>> 6b07be8fa724d13c4c8756650c6f6e9618343dd2
import com.etaofinance.entity.req.UpdatePwdReq;
import com.etaofinance.entity.resp.*;


public interface IBalanceRecordService {
   

	List<BalanceRecordDM> getListMore(PublicMemberReq record);	
	
	HttpResultModel<BalanceRecordDM> selectBRDetail(PublicMemberReq record);

	PagedResponse<BalanceRecord> getPageList(PagedMemberBalanceRecordReq req);
	
	PagedResponse<BalanceRecord>  getBalanceRecordList(PagedBalancerecordReq req);
	
}
