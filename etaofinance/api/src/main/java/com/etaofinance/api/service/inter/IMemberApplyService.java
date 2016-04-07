package com.etaofinance.api.service.inter;
import java.util.List;

import com.etaofinance.entity.BankCard;
import com.etaofinance.entity.MemberApply;
import com.etaofinance.entity.common.HttpResultModel;
import com.etaofinance.entity.common.PagedResponse;
import com.etaofinance.entity.common.ResponseBase;
import com.etaofinance.entity.domain.MemberApplyAuditModel;
import com.etaofinance.entity.domain.MemberApplyInvestModel;
import com.etaofinance.entity.req.MemberApplyAuditReq;
import com.etaofinance.entity.req.PagedMemberReq;

public interface IMemberApplyService {

	PagedResponse<MemberApplyInvestModel> getMemberApplyList(PagedMemberReq req);

	MemberApplyAuditModel getMemberApplyInfo(long memberApplyId);

	int auditConfirm(MemberApplyAuditReq req);

	List<MemberApply> getMemberApplyInfoByMemberId(long memberId);

	HttpResultModel<Object> create(MemberApply record);
}
