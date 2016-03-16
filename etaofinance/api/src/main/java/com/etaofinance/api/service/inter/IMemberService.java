package com.etaofinance.api.service.inter;

import com.etaofinance.entity.Member;

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
}
