package com.etaofinance.api.service.inter;

import com.etaofinance.entity.Member;
import com.etaofinance.entity.common.PagedResponse;
import com.etaofinance.entity.domain.MemberModel;
import com.etaofinance.entity.req.PagedMemberReq;

/**
 * 接口
 * @author ofmyi_000
 *
 */
public interface IMemberService {

	 /**
     * 通过手机号获取会员信息
     * @param phoneno
     * @return
     */
    Member selectByPhoneNo(String  phoneno);

	PagedResponse<MemberModel> getMemberList(PagedMemberReq req);
}
