package com.etaofinance.api.service.inter;

import java.util.List;

import com.etaofinance.entity.ADVert;
import com.etaofinance.entity.AccountInfo;
import com.etaofinance.entity.Bank;
import com.etaofinance.entity.ProjectSubscription;
import com.etaofinance.entity.common.HttpResultModel;
import com.etaofinance.entity.common.PagedResponse;
import com.etaofinance.entity.domain.ProjectFavoriteInvestModel;
import com.etaofinance.entity.domain.ProjectSubscriptionDM;
import com.etaofinance.entity.req.PagedADVertReq;
import com.etaofinance.entity.req.PagedAccountInfoReq;
import com.etaofinance.entity.req.PagedProjectFavReq;
import com.etaofinance.entity.req.PagedProjectSubReq;
import com.etaofinance.entity.req.ProSubInvestReq;
import com.etaofinance.entity.req.UpdatePwdReq;
import com.etaofinance.entity.resp.*;


public interface IProjectSubscriptionService {
   

	List<ProjectSubscriptionDM> getListMore(ProSubInvestReq record);

	PagedResponse<ProjectSubscription> getProjectSubPageList(PagedProjectSubReq req);
}
