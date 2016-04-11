package com.etaofinance.api.service.impl;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etaofinance.api.common.LoginHelper;
import com.etaofinance.api.dao.inter.IMemberDao;
import com.etaofinance.api.dao.inter.IMemberOtherDao;
import com.etaofinance.api.redis.RedisService;
import com.etaofinance.api.service.inter.IMemberOtherService;
import com.etaofinance.api.service.inter.IMemberService;
import com.etaofinance.core.consts.RedissCacheKey;
import com.etaofinance.core.enums.BankCardEnum;
import com.etaofinance.core.enums.MemberCertificationEnum;
import com.etaofinance.core.enums.MemberEnum;
import com.etaofinance.core.enums.MemberOtherCreatePayPwdEnum;
import com.etaofinance.core.enums.MemberOtherVerificationPayPwdEnum;
import com.etaofinance.core.enums.PublicEnum;
import com.etaofinance.core.enums.QAEnum;
import com.etaofinance.core.enums.SendCodeType;
import com.etaofinance.core.security.MD5Util;
import com.etaofinance.core.util.RandomCodeStrGenerator;
import com.etaofinance.core.util.SmsUtils;
import com.etaofinance.entity.Member;
import com.etaofinance.entity.MemberOther;
import com.etaofinance.entity.req.ForgetPwdOneReq;
import com.etaofinance.entity.req.ForgetPwdThreeReq;
import com.etaofinance.entity.req.ForgetPwdTwoReq;
import com.etaofinance.entity.req.ModifypwdReq;
import com.etaofinance.entity.req.PagedMemberReq;
import com.etaofinance.entity.req.RegistReq;
import com.etaofinance.entity.req.SendCodeReq;
import com.etaofinance.entity.common.HttpResultModel;
import com.etaofinance.entity.common.PagedResponse;
import com.etaofinance.entity.domain.MemberModel;
import com.etaofinance.entity.resp.ForgetPwdResp;
import com.etaofinance.entity.resp.MemberResp;
import com.etaofinance.entity.resp.SendCodeResp;

@Service
public class MemberOtherService implements IMemberOtherService{
	@Autowired
	private IMemberOtherDao memberOtherDao;
	
	@Autowired
	private IMemberDao memberDao;
	
	@Autowired
	private RedisService redisService;

	/**
	 * 创建支付密码
	 * 修改，找回
	 * @param 
	 * @author hulingbo
	 * @date 2016年3月28日16:59:18
	 * @return
	 */
	@Override
	public HttpResultModel<Object> createPayPwd(MemberOther record) {
		HttpResultModel<Object> resp=new HttpResultModel<Object>();
		if(record.getMemberid() ==null || record.getMemberid().equals(""))
		{	
			resp.setCode(MemberOtherCreatePayPwdEnum.MemberIdIsNull.value());
			resp.setMsg(MemberOtherCreatePayPwdEnum.MemberIdIsNull.desc());
			return resp;			
		}
		
		Member member=memberDao.selectByPrimaryKey(record.getMemberid());	
		if(member.getLevel() ==null || member.getLevel().equals("0"))
		{	
			resp.setCode(MemberOtherCreatePayPwdEnum.LevelIsErr.value());
			resp.setMsg(MemberOtherCreatePayPwdEnum.LevelIsErr.desc());
			return resp;			
		}
		
		if(record.getPaypassword() ==null || record.getPaypassword().equals(""))
		{	
			resp.setCode(MemberOtherCreatePayPwdEnum.PayPassWordIsNull.value());
			resp.setMsg(MemberOtherCreatePayPwdEnum.PayPassWordIsNull.desc());
			return resp;			
		}
		String opwd=MD5Util.MD5(record.getPaypassword());
		record.setPaypassword(opwd);		
		int row=memberOtherDao.updateByMemberIdSelective(record);
				
		if(row<=0)
		{
			resp.setCode(MemberOtherCreatePayPwdEnum.Err.value());
			resp.setMsg(MemberOtherCreatePayPwdEnum.Err.desc());
			return resp;	
		}
		resp.setCode(MemberOtherCreatePayPwdEnum.Success.value());
		resp.setMsg(MemberOtherCreatePayPwdEnum.Success.desc());
		return resp;
	}

	/**
	 * 验证原支付密码
	 * @param 
	 * @author hulingbo
	 * @date 2016年3月28日16:59:35
	 * @return
	 */
	@Override
	public HttpResultModel<Object> verificationPayPwd(MemberOther record) {
		HttpResultModel<Object> resp=new HttpResultModel<Object>();
		if(record.getMemberid() ==null || record.getMemberid().equals(""))
		{	
			resp.setCode(MemberOtherVerificationPayPwdEnum.MemberIdIsNull.value());
			resp.setMsg(MemberOtherVerificationPayPwdEnum.MemberIdIsNull.desc());
			return resp;			
		}
		
		MemberOther oldMemberOther=memberOtherDao.selectByPrimaryKey(record.getMemberid());
		if(oldMemberOther==null)
		{
			resp.setCode(MemberOtherVerificationPayPwdEnum.Err.value());
			resp.setMsg(MemberOtherVerificationPayPwdEnum.Err.desc());
			return resp;
		}
		String opwd=MD5Util.MD5(record.getPaypassword());	
		if(!oldMemberOther.getPaypassword().equals(opwd))
		{
			resp.setCode(MemberOtherVerificationPayPwdEnum.PayPassWordErr.value());
			resp.setMsg(MemberOtherVerificationPayPwdEnum.PayPassWordErr.desc());
			return resp;	
		}

		resp.setCode(MemberOtherVerificationPayPwdEnum.Success.value());
		resp.setMsg(MemberOtherVerificationPayPwdEnum.Success.desc());
		return resp;
	}	
	
	
	


}
