package com.etaofinance.api.dao.inter;

import java.util.List;

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

    MemberApply selectPending(long memberId);
    
	PagedResponse<MemberApplyInvestModel> getMemberApplyList(PagedMemberReq req);

	MemberApplyAuditModel getMemberApplyInfo(long memberApplyId);

	int auditConfirm(MemberApplyAuditReq req);

	List<MemberApply> getMemberApplyInfoByMemberId(long memberId);
	/**
	 * 是否存在未审核的申请
	 * @param uid
	 * @return
	 */
	boolean IsHasApply(Long uid);
	
	/**
	 * 是否存在投资人未审核的申请
	 * @param uid
	 * @return
	 */
	boolean IsHasTZApply(Long uid);
	
	/**
	 * 是否存在领投人未审核的申请
	 * @param uid
	 * @return
	 */
	boolean IsHasLTApply(Long uid);
}