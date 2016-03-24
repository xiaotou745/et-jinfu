package com.etaofinance.api.service.inter;

import java.util.List;

import com.etaofinance.entity.ADVert;
import com.etaofinance.entity.AccountInfo;
import com.etaofinance.entity.Bank;
import com.etaofinance.entity.QA;
import com.etaofinance.entity.common.HttpResultModel;
import com.etaofinance.entity.common.PagedResponse;
import com.etaofinance.entity.req.PagedADVertReq;
import com.etaofinance.entity.req.PagedAccountInfoReq;
import com.etaofinance.entity.req.PagedQAReq;
import com.etaofinance.entity.req.UpdatePwdReq;
import com.etaofinance.entity.resp.*;


public interface IQAService {   

	HttpResultModel<QAResp> create(QA record);
	
	int remove(Integer id) ;

	HttpResultModel<QAResp> modify(QA record);
	
	QA getById(Integer id);
    
    
	PagedResponse<QA>  query(PagedQAReq req);	

	List<QA> getList();
	

}
