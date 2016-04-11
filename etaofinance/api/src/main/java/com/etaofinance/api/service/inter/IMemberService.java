package com.etaofinance.api.service.inter;

import org.springframework.web.bind.annotation.RequestBody;

import com.etaofinance.entity.Member;
import com.etaofinance.entity.QA;
import com.etaofinance.entity.req.ForgetPwdOneReq;
import com.etaofinance.entity.req.ForgetPwdThreeReq;
import com.etaofinance.entity.req.ForgetPwdTwoReq;
import com.etaofinance.entity.req.ModifyMemberReq;
import com.etaofinance.entity.req.ModifypwdReq;
import com.etaofinance.entity.req.PagedMemberReq;
import com.etaofinance.entity.req.RegistReq;
import com.etaofinance.entity.req.SendCodeReq;
import com.etaofinance.entity.common.HttpResultModel;
import com.etaofinance.entity.resp.ForgetPwdResp;
import com.etaofinance.entity.resp.MemberResp;
import com.etaofinance.entity.common.PagedResponse;
import com.etaofinance.entity.domain.MemberDM;
import com.etaofinance.entity.domain.MemberModel;
import com.etaofinance.entity.resp.SendCodeResp;

/**
 * 接口
 * @author ofmyi_000
 *
 */
public interface IMemberService {
  
    
    HttpResultModel<Object> modify(Member record);
    
	 /**
     * 通过手机号获取会员信息
     * @param phoneno
     * @return
     */
    Member selectByPhoneNo(String  phoneno);   
    /**
     * 通过账号获取会员信息
     * @param username
     * @return
     */
    Member selectByUserName(String  username);  
    /**
     * 通过邮箱获取会员信息
     * @param email
     * @return
     */
    Member selectByEmail(String  email);  

    /**
     * 通过ID获取会员信息
     * @param 会员id
     * @return
     */
    Member  getById(Long  id);
    
    /**
     * 通过ID获取会员信息
     * @param 会员id
     * @return
     */
    MemberDM  getUserInfo(Long id);
    /**
     * 获取验证码
     * @param req
     * @return
     */
    HttpResultModel<Object> sendCode(SendCodeReq req);
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
	 * 实名认证
	 * @param 
	 * @author hulingbo
	 * @date 2016年3月24日18:05:14
	 * @return
	 */
    HttpResultModel<Object> Certification(Member record);
    /**
     * 忘记密码第一步
     * @param req
     * @return
     */
    HttpResultModel<ForgetPwdResp> forgetpwdsetpone(ForgetPwdOneReq req);
    /**
     * 忘记密码第二步
     * @param req
     * @return
     */
    HttpResultModel<ForgetPwdResp> forgetpwdsetptwo(ForgetPwdTwoReq req);
    /**
     * 忘记密码第三步
     * @param req
     * @return
     */
    HttpResultModel<ForgetPwdResp> forgetpwdsetpthree(ForgetPwdThreeReq req);
    
    /**
     *修改用户密码
     * @param req
     * @return
     */
    HttpResultModel<Object> modifypwd(ModifypwdReq req);

    /*
     * 后台用户 修改会员基本信息 wangchao
     */
	int modifyMember(ModifyMemberReq req);
 }
