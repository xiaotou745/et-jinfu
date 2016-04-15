package com.etaofinance.api.service.inter;

import org.springframework.web.bind.annotation.RequestBody;

import com.etaofinance.entity.Member;
import com.etaofinance.entity.MemberOther;
import com.etaofinance.entity.QA;
import com.etaofinance.entity.req.CreatePayPwdReq;
import com.etaofinance.entity.req.ForgetPayPwdReq;
import com.etaofinance.entity.req.ForgetPwdOneReq;
import com.etaofinance.entity.req.ForgetPwdThreeReq;
import com.etaofinance.entity.req.ForgetPwdTwoReq;
import com.etaofinance.entity.req.ModifyPayPwdReq;
import com.etaofinance.entity.req.ModifypwdReq;
import com.etaofinance.entity.req.PagedMemberReq;
import com.etaofinance.entity.req.RegistReq;
import com.etaofinance.entity.req.SendCodeReq;
import com.etaofinance.entity.common.HttpResultModel;
import com.etaofinance.entity.resp.CreatePayPwdResp;
import com.etaofinance.entity.resp.ForgetPayPwdResp;
import com.etaofinance.entity.resp.ForgetPwdResp;
import com.etaofinance.entity.resp.MemberResp;
import com.etaofinance.entity.resp.ModifyPayPwdResp;
import com.etaofinance.entity.common.PagedResponse;
import com.etaofinance.entity.domain.MemberModel;
import com.etaofinance.entity.resp.SendCodeResp;

/**
 * 接口
 * @author 
 *
 */
public interface IMemberOtherService {  


	/**
	 * 创建支付密码 第1步
	 * @param 
	 * @author hulingbo
	 * @date 2016年4月13日16:45:37
	 * @return
	 */
	HttpResultModel<CreatePayPwdResp> createPayPwdOne(@RequestBody  CreatePayPwdReq req);
	
	/**
	 * 创建支付密码 第2步
	 * @param 
	 * @author hulingbo
	 * @date 2016年4月13日16:50:48
	 * @return
	 */
	HttpResultModel<CreatePayPwdResp> createPayPwdTwo(@RequestBody  CreatePayPwdReq req);	
	

	/**
	 * 修改支付密码 第1步
	 * @param 
	 * @author hulingbo
	 * @date 2016年4月13日20:07:05
	 * @return
	 */
	HttpResultModel<ModifyPayPwdResp> modifyPayPwdOne(@RequestBody  ModifyPayPwdReq req);
	
	/**
	 * 修改支付密码 第2步
	 * @param 
	 * @author hulingbo
	 * @date 2016年4月13日20:07:12
	 * @return
	 */
	HttpResultModel<ModifyPayPwdResp> modifyPayPwdTwo(@RequestBody  ModifyPayPwdReq req);    
 
	/**
	 * 找回支付密码 第1步
	 * @param 
	 * @author hulingbo
	 * @date 2016年4月13日20:36:37
	 * @return
	 */
	HttpResultModel<ForgetPayPwdResp> forgetPayPwdOne(@RequestBody  ForgetPayPwdReq req);
	
	/**
	 * 找回支付密码 第2步
	 * @param 
	 * @author hulingbo
	 * @date 2016年4月13日20:36:45
	 * @return
	 */
	HttpResultModel<ForgetPayPwdResp> forgetPayPwdTwo(@RequestBody  ForgetPayPwdReq req);	

    
    /**
     * 根据ID获取用户信息
     * @param id
     * @return
     */
    MemberOther getByMemberId(Long id);
    
    
    
    
//	/**
//	 * 创建支付密码
//	 * @param 
//	 * @author hulingbo
//	 * @date 2016年3月28日16:03:24
//	 * @return
//	 */
//	HttpResultModel<Object> createPayPwd(MemberOther req);
    
//    /**
//     *验证支付密码
//	 * @param 
//	 * @author hulingbo
//	 * @date 2016年3月28日16:04:00
//	 * @return
//     */
//    HttpResultModel<Object> verificationPayPwd(MemberOther req);
 }
