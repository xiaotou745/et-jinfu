package com.etaofinance.api.service.inter;

import java.util.List;

import com.etaofinance.entity.ADVert;
import com.etaofinance.entity.AccountInfo;
import com.etaofinance.entity.Bank;
import com.etaofinance.entity.FeedBack;
import com.etaofinance.entity.QA;
import com.etaofinance.entity.common.HttpResultModel;
import com.etaofinance.entity.common.PagedResponse;
import com.etaofinance.entity.domain.MemberModel;
import com.etaofinance.entity.req.PagedADVertReq;
import com.etaofinance.entity.req.PagedAccountInfoReq;
import com.etaofinance.entity.req.PagedFeedBackReq;
import com.etaofinance.entity.req.PagedMemberReq;
import com.etaofinance.entity.req.PagedQAReq;
import com.etaofinance.entity.req.UpdatePwdReq;
import com.etaofinance.entity.resp.*;


public interface IFeedBackService {   

	HttpResultModel<Object> create(FeedBack record);
	
	PagedResponse<FeedBack> getFeedBackList(PagedFeedBackReq req);
	
	int remove(Integer id) ;

}
