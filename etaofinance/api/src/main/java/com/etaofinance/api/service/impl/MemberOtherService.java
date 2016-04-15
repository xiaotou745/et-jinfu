package com.etaofinance.api.service.impl;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

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
import com.etaofinance.core.util.PropertyUtils;
import com.etaofinance.core.util.RandomCodeStrGenerator;
import com.etaofinance.core.util.RegexHelper;
import com.etaofinance.core.util.SmsUtils;
import com.etaofinance.entity.Member;
import com.etaofinance.entity.MemberOther;
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
import com.etaofinance.entity.common.PagedResponse;
import com.etaofinance.entity.domain.MemberModel;
import com.etaofinance.entity.resp.CreatePayPwdResp;
import com.etaofinance.entity.resp.ForgetPayPwdResp;
import com.etaofinance.entity.resp.ForgetPwdResp;
import com.etaofinance.entity.resp.MemberResp;
import com.etaofinance.entity.resp.ModifyPayPwdResp;
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
	 * 创建支付密码	 第1步
	 * @param 
	 * @author hulingbo
	 * @date 2016年3月28日16:59:18
	 * @return
	 */
	@Override
	public HttpResultModel<CreatePayPwdResp> createPayPwdOne(@RequestBody  CreatePayPwdReq req) {
		HttpResultModel<CreatePayPwdResp> resultModel=new HttpResultModel<CreatePayPwdResp>();
	
		if(req.getPhoneNo()==null ||req.getPhoneNo().equals(""))
		{
			resultModel.setCode(-1);
			resultModel.setMsg("手机号不能为空!");
			return resultModel;
		}		
		//查询会员是否存在	
		Member member=null;
		if(RegexHelper.IsPhone(req.getPhoneNo()))
		{
			member=memberDao.selectByPhoneNo(req.getPhoneNo());			
			if(member==null)
			{
				resultModel.setCode(-1);
				resultModel.setMsg("会员不存在!");
				return resultModel;
			}
		}		
		
		//手机验证码
		String phoneKey= String.format(RedissCacheKey.JF_Member_SetPayPassWord ,req.getPhoneNo());
		String phoneValue=redisService.get(phoneKey, String.class);
		if(req.getVeriCode()==null || req.getVeriCode().equals("")|| 
				phoneValue==null ||phoneValue.equals("")|| 		    
		    !req.getVeriCode().equals(phoneValue) )
		{
			resultModel.setCode(-1);
			resultModel.setMsg("验证码错误,请重试!");
			return resultModel;
		}		
		
		//给缓存设置一个UUID 5分钟 第二步进来的时候验证用
		String UUIDvalue=UUID.randomUUID().toString();
		String UUIDkey=String.format(RedissCacheKey.JF_Member_SetPayPassWordOne,UUIDvalue);
		redisService.set(UUIDkey, UUIDvalue,60*5,TimeUnit.SECONDS);
		//验证码错误
		resultModel.setCode(1);
		resultModel.setMsg("验证通过!");
		CreatePayPwdResp resp=new CreatePayPwdResp();		
		resp.setCheckKey(UUIDvalue);		
		String basePath = PropertyUtils.getProperty("java.wap.url");
		basePath+="/pay/setpaypasswordstep2?";
		basePath+="checkKey="+UUIDvalue;		
		resultModel.setUrl(basePath);
		resultModel.setData(resp);
		return resultModel;
	}
	
	/**
	 * 创建支付密码	 第2步
	 * @param 
	 * @author hulingbo
	 * @date 2016年3月28日16:59:18
	 * @return
	 */
	@Override
	public HttpResultModel<CreatePayPwdResp> createPayPwdTwo(CreatePayPwdReq req) {
		HttpResultModel<CreatePayPwdResp> resultModel=new HttpResultModel<CreatePayPwdResp>();	
	
		//第一步给返回的UUID
		String keyOne=String.format(RedissCacheKey.JF_Member_SetPayPassWordOne,req.getCheckKey());
		String valueOne=redisService.get(keyOne, String.class);
		if(req.getCheckKey()==null || req.getCheckKey().equals("")||
			valueOne==null || valueOne.equals("")||
			!req.getCheckKey().equals(valueOne))		
		{
			resultModel.setCode(-1);
			resultModel.setMsg("验证码错误,请重试!");
			return resultModel;
		}
		
		//删除第一次给的UUID,防止被重复使用
		redisService.remove(keyOne);		

		MemberOther memberOther=new MemberOther();
		memberOther.setMemberid(req.getUserId());
		String opwd=MD5Util.MD5(req.getPassWord());
		memberOther.setPaypassword(opwd);		
		int row=memberOtherDao.updateByMemberIdSelective(memberOther);
				
		if(row<=0)
		{
			resultModel.setCode(MemberOtherCreatePayPwdEnum.Err.value());
			resultModel.setMsg(MemberOtherCreatePayPwdEnum.Err.desc());
			return resultModel;	
		}	
		//跳转我的账户余额
		String basePath = PropertyUtils.getProperty("java.wap.url");
		resultModel.setUrl(basePath + "/me/accountblance");	
		resultModel.setCode(MemberOtherCreatePayPwdEnum.Success.value());
		resultModel.setMsg(MemberOtherCreatePayPwdEnum.Success.desc());
		return resultModel;
	}	
	
	/**
	 * 修改支付密码 第1步
	 * @param 
	 * @author hulingbo
	 * @date 2016年4月13日20:07:05
	 * @return
	 */
	public HttpResultModel<ModifyPayPwdResp> modifyPayPwdOne(@RequestBody  ModifyPayPwdReq req)
	{
		HttpResultModel<ModifyPayPwdResp> resultModel=new HttpResultModel<ModifyPayPwdResp>();
		MemberOther oldMemberOther=memberOtherDao.selectByMemberId(req.getUserId());
		if(oldMemberOther==null)
		{
			resultModel.setCode(MemberOtherVerificationPayPwdEnum.Err.value());
			resultModel.setMsg(MemberOtherVerificationPayPwdEnum.Err.desc());
			return resultModel;
		}
		String opwd=MD5Util.MD5(req.getPassWord());	
		if(!oldMemberOther.getPaypassword().equals(opwd))
		{
			String key = String.format(RedissCacheKey.JF_Member_InputPayPassword, req.getUserId());
			String excuteCount=redisService.get(key, String.class);        
	        if (excuteCount!=null &&Integer.parseInt(excuteCount)>=5 )
	         {
	        	resultModel.setCode(-1);
				resultModel.setMsg("录入支付密码错误5次!");
				return resultModel; 			
	         }
	        redisService.set(key, excuteCount + 1,60*5,TimeUnit.SECONDS);
	        
			resultModel.setCode(MemberOtherVerificationPayPwdEnum.PayPassWordErr.value());
			resultModel.setMsg(MemberOtherVerificationPayPwdEnum.PayPassWordErr.desc());
			return resultModel;	
		}			

		//给缓存设置一个UUID 5分钟 第二步进来的时候验证用
		String UUIDvalue=UUID.randomUUID().toString();
		String UUIDkey=String.format(RedissCacheKey.JF_Member_ModifyPayPassWordOne,UUIDvalue);
		redisService.set(UUIDkey, UUIDvalue,60*5,TimeUnit.SECONDS);
		
		
		resultModel.setCode(MemberOtherVerificationPayPwdEnum.Success.value());
		resultModel.setMsg(MemberOtherVerificationPayPwdEnum.Success.desc());
		
		ModifyPayPwdResp resp=new ModifyPayPwdResp();	
		resp.setCheckKey(UUIDvalue);
		String basePath = PropertyUtils.getProperty("java.wap.url");
		basePath+="/pay/modifypaypasswordstep2?";
		basePath+="checkKey="+UUIDvalue;	
		resultModel.setUrl(basePath);
		resultModel.setData(resp);
		return resultModel;
	}
	
	/**
	 * 修改支付密码 第2步
	 * @param 
	 * @author hulingbo
	 * @date 2016年4月13日20:07:12
	 * @return
	 */
	public HttpResultModel<ModifyPayPwdResp> modifyPayPwdTwo(@RequestBody  ModifyPayPwdReq req)
	{
		HttpResultModel<ModifyPayPwdResp> resultModel=new HttpResultModel<ModifyPayPwdResp>();	
		
		//第一步给返回的UUID
		String keyOne=String.format(RedissCacheKey.JF_Member_ModifyPayPassWordOne,req.getCheckKey());
		String valueOne=redisService.get(keyOne, String.class);
		if(req.getCheckKey()==null || req.getCheckKey().equals("")||
			valueOne==null || valueOne.equals("")||
			!req.getCheckKey().equals(valueOne))		
		{
			resultModel.setCode(-1);
			resultModel.setMsg("验证码错误,请重试!");
			return resultModel;
		}
		
		//删除第一次给的UUID,防止被重复使用
		redisService.remove(keyOne);		

		MemberOther memberOther=new MemberOther();
		memberOther.setMemberid(req.getUserId());
		String opwd=MD5Util.MD5(req.getPassWord());
		memberOther.setPaypassword(opwd);		
		int row=memberOtherDao.updateByMemberIdSelective(memberOther);
				
		if(row<=0)
		{
			resultModel.setCode(MemberOtherCreatePayPwdEnum.Err.value());
			resultModel.setMsg(MemberOtherCreatePayPwdEnum.Err.desc());
			return resultModel;	
		}
		//跳转我的账户余额
		String basePath = PropertyUtils.getProperty("java.wap.url");
		resultModel.setUrl(basePath + "/me/accountblance");	
		resultModel.setCode(MemberOtherCreatePayPwdEnum.Success.value());
		resultModel.setMsg(MemberOtherCreatePayPwdEnum.Success.desc());
		return resultModel;
	}
	
	/**
	 * 找回支付密码 第1步
	 * @param 
	 * @author hulingbo
	 * @date 2016年4月13日20:36:37
	 * @return
	 */
	public HttpResultModel<ForgetPayPwdResp> forgetPayPwdOne(@RequestBody  ForgetPayPwdReq req)
	{
		HttpResultModel<ForgetPayPwdResp> resultModel = new HttpResultModel<ForgetPayPwdResp>();
		// 手机验证码
		String key = String.format(RedissCacheKey.JF_Member_FindPayPassWord,
				req.getPhoneNo());
		String value = redisService.get(key, String.class);

		if (req.getPhoneNo() == null || req.getVeriCode() == null|| value == null
				|| req.getPhoneNo().equals("") || req.getVeriCode().equals("")|| value.equals("") 
				||  !req.getVeriCode().equals(value))
	     {
			resultModel.setCode(-1);
			resultModel.setMsg("验证码错误,请重试!");
			return resultModel;
		}

		// 查询会员是否存在
		Member member = memberDao.selectByPhoneNo(req.getPhoneNo());
		if (member == null) {
			// 验证码错误
			resultModel.setCode(-1);
			resultModel.setMsg("用户不存在!");
			return resultModel;
		}
		// 给缓存设置一个UUID 5分钟 第二步进来的时候验证用
		String UUIDvalue = UUID.randomUUID().toString();
		String UUIDkey = String.format(
				RedissCacheKey.JF_Member_FindPayPassWordOne, UUIDvalue);
		redisService.set(UUIDkey, UUIDvalue, 60 * 5, TimeUnit.SECONDS);
		resultModel.setCode(1);
		resultModel.setMsg("验证通过!");
		ForgetPayPwdResp resp = new ForgetPayPwdResp();
		resp.setUserID(member.getId());
		resp.setCheckKey(UUIDvalue);
		resultModel.setData(resp);
		return resultModel;
	}
	
	/**
	 * 找回支付密码 第2步
	 * @param 
	 * @author hulingbo
	 * @date 2016年4月13日20:36:45
	 * @return
	 */
	public HttpResultModel<ForgetPayPwdResp> forgetPayPwdTwo(@RequestBody  ForgetPayPwdReq req)
	{
		HttpResultModel<ForgetPayPwdResp> resultModel=new HttpResultModel<ForgetPayPwdResp>();	
		
		//第一步给返回的UUID
		String keyOne=String.format(RedissCacheKey.JF_Member_FindPayPassWordOne,req.getCheckKey());
		String valueOne=redisService.get(keyOne, String.class);
		if(req.getCheckKey()==null || req.getCheckKey().equals("")||
			valueOne==null || valueOne.equals("")||
			!req.getCheckKey().equals(valueOne))		
		{
			resultModel.setCode(-1);
			resultModel.setMsg("验证码错误,请重试!");
			return resultModel;
		}
		
		//删除第一次给的UUID,防止被重复使用
		redisService.remove(keyOne);		

		MemberOther memberOther=new MemberOther();
		memberOther.setMemberid(req.getUserId());
		String opwd=MD5Util.MD5(req.getPassWord());
		memberOther.setPaypassword(opwd);		
		int row=memberOtherDao.updateByMemberIdSelective(memberOther);
				
		if(row<=0)
		{
			resultModel.setCode(MemberOtherCreatePayPwdEnum.Err.value());
			resultModel.setMsg(MemberOtherCreatePayPwdEnum.Err.desc());
			return resultModel;	
		}
		resultModel.setCode(MemberOtherCreatePayPwdEnum.Success.value());
		resultModel.setMsg(MemberOtherCreatePayPwdEnum.Success.desc());
		return resultModel;
	}

	/**
	 * 获取用户信息
	 */
	@Override
	public MemberOther getByMemberId(Long id) {
		return memberOtherDao.selectByMemberId(id);
	}	
	
	
//	/**
//	 * 创建支付密码
//	 * 修改，找回
//	 * @param 
//	 * @author hulingbo
//	 * @date 2016年3月28日16:59:18
//	 * @return
//	 */
//	@Override
//	public HttpResultModel<Object> createPayPwd(MemberOther record) {
//		HttpResultModel<Object> resp=new HttpResultModel<Object>();
//		if(record.getMemberid() ==null || record.getMemberid().equals(""))
//		{	
//			resp.setCode(MemberOtherCreatePayPwdEnum.MemberIdIsNull.value());
//			resp.setMsg(MemberOtherCreatePayPwdEnum.MemberIdIsNull.desc());
//			return resp;			
//		}
//		
//		Member member=memberDao.selectByPrimaryKey(record.getMemberid());	
//		if(member.getLevel() ==null || member.getLevel().equals("0"))
//		{	
//			resp.setCode(MemberOtherCreatePayPwdEnum.LevelIsErr.value());
//			resp.setMsg(MemberOtherCreatePayPwdEnum.LevelIsErr.desc());
//			return resp;			
//		}
//		
//		if(record.getPaypassword() ==null || record.getPaypassword().equals(""))
//		{	
//			resp.setCode(MemberOtherCreatePayPwdEnum.PayPassWordIsNull.value());
//			resp.setMsg(MemberOtherCreatePayPwdEnum.PayPassWordIsNull.desc());
//			return resp;			
//		}
//		String opwd=MD5Util.MD5(record.getPaypassword());
//		record.setPaypassword(opwd);		
//		int row=memberOtherDao.updateByMemberIdSelective(record);
//				
//		if(row<=0)
//		{
//			resp.setCode(MemberOtherCreatePayPwdEnum.Err.value());
//			resp.setMsg(MemberOtherCreatePayPwdEnum.Err.desc());
//			return resp;	
//		}
//		resp.setCode(MemberOtherCreatePayPwdEnum.Success.value());
//		resp.setMsg(MemberOtherCreatePayPwdEnum.Success.desc());
//		return resp;
//	}	
	
//	/**
//	 * 验证原支付密码
//	 * @param 
//	 * @author hulingbo
//	 * @date 2016年3月28日16:59:35
//	 * @return
//	 */
//	@Override
//	public HttpResultModel<Object> verificationPayPwd(MemberOther record) {
//		HttpResultModel<Object> resp=new HttpResultModel<Object>();
//		if(record.getMemberid() ==null || record.getMemberid().equals(""))
//		{	
//			resp.setCode(MemberOtherVerificationPayPwdEnum.MemberIdIsNull.value());
//			resp.setMsg(MemberOtherVerificationPayPwdEnum.MemberIdIsNull.desc());
//			return resp;			
//		}
//		
//		MemberOther oldMemberOther=memberOtherDao.selectByPrimaryKey(record.getMemberid());
//		if(oldMemberOther==null)
//		{
//			resp.setCode(MemberOtherVerificationPayPwdEnum.Err.value());
//			resp.setMsg(MemberOtherVerificationPayPwdEnum.Err.desc());
//			return resp;
//		}
//		String opwd=MD5Util.MD5(record.getPaypassword());	
//		if(!oldMemberOther.getPaypassword().equals(opwd))
//		{
//			resp.setCode(MemberOtherVerificationPayPwdEnum.PayPassWordErr.value());
//			resp.setMsg(MemberOtherVerificationPayPwdEnum.PayPassWordErr.desc());
//			return resp;	
//		}
//
//		resp.setCode(MemberOtherVerificationPayPwdEnum.Success.value());
//		resp.setMsg(MemberOtherVerificationPayPwdEnum.Success.desc());
//		return resp;
//	}
}
