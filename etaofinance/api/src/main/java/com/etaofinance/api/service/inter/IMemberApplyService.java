package com.etaofinance.api.service.inter;

import com.etaofinance.entity.common.PagedResponse;
import com.etaofinance.entity.domain.MemberApplyInvestModel;
import com.etaofinance.entity.req.PagedMemberReq;

public interface IMemberApplyService {

	PagedResponse<MemberApplyInvestModel> getMemberApplyList(PagedMemberReq req);

}
