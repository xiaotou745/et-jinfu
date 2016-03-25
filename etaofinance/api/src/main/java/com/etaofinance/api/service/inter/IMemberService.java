package com.etaofinance.api.service.inter;

import com.etaofinance.entity.Member;
import com.etaofinance.entity.req.PagedMemberReq;
import com.etaofinance.entity.req.RegistReq;
import com.etaofinance.entity.req.SendCodeReq;
import com.etaofinance.entity.common.HttpResultModel;
import com.etaofinance.entity.resp.MemberResp;
import com.etaofinance.entity.common.PagedResponse;
import com.etaofinance.entity.domain.MemberModel;
import com.etaofinance.entity.resp.SendCodeResp;

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
    /**
     * 获取验证码
     * @param req
     * @return
     */
    SendCodeResp sendCode(SendCodeReq req);
    /**
     * 注册用户
     * @param req
     * @return
     */
    HttpResultModel<Member> regist(RegistReq req);
    /*
     * 获取会员信息
     * wangchao
     */
	PagedResponse<MemberModel> getMemberList(PagedMemberReq req);
	/**
     * 实名认证信息
     * @param req
     * @return
     */
    HttpResultModel<MemberResp> Certification(Member record);
 }
