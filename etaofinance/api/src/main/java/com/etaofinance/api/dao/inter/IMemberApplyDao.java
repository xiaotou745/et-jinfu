package com.etaofinance.api.dao.inter;

import com.etaofinance.entity.MemberApply;
import com.etaofinance.entity.common.PagedResponse;
import com.etaofinance.entity.domain.MemberApplyAuditModel;
import com.etaofinance.entity.domain.MemberApplyInvestModel;
import com.etaofinance.entity.req.MemberApplyAuditReq;
import com.etaofinance.entity.req.PagedMemberReq;

public interface IMemberApplyDao {
    int deleteByPrimaryKey(Long id);

    int insert(MemberApply record);

    int insertSelective(MemberApply record);

    MemberApply selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MemberApply record);

    int updateByPrimaryKey(MemberApply record);

	PagedResponse<MemberApplyInvestModel> getMemberApplyList(PagedMemberReq req);

	MemberApplyAuditModel getMemberApplyInfo(long memberApplyId);

	int auditConfirm(MemberApplyAuditReq req);
}