package com.etaofinance.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etaofinance.api.dao.inter.IMemberDao;
import com.etaofinance.api.redis.RedisService;
import com.etaofinance.api.service.inter.IMemberService;
import com.etaofinance.core.consts.RedissCacheKey;
import com.etaofinance.core.enums.MemberCertificationEnum;
import com.etaofinance.core.enums.MemberEnum;
import com.etaofinance.core.enums.PublicEnum;
import com.etaofinance.core.enums.QAEnum;
import com.etaofinance.core.enums.SendCodeType;
import com.etaofinance.core.security.MD5Util;
import com.etaofinance.core.util.RandomCodeStrGenerator;
import com.etaofinance.core.util.SmsUtils;
import com.etaofinance.entity.Member;
import com.etaofinance.entity.QA;
import com.etaofinance.entity.req.PagedMemberReq;
import com.etaofinance.entity.req.RegistReq;
import com.etaofinance.entity.req.SendCodeReq;
import com.etaofinance.entity.common.HttpResultModel;
import com.etaofinance.entity.common.PagedResponse;
import com.etaofinance.entity.domain.MemberModel;
import com.etaofinance.entity.resp.MemberResp;
import com.etaofinance.entity.resp.QAResp;
import com.etaofinance.entity.resp.SendCodeResp;
import com.sun.org.apache.bcel.internal.generic.RETURN;

@Service
public class MemberService implements IMemberService{
	@Autowired
	private IMemberDao memberDao;
	
	@Autowired
	private RedisService redisService;	
	
	@Override
	public HttpResultModel<MemberResp> modify(Member record)
	{
		HttpResultModel<MemberResp> resp = new HttpResultModel<MemberResp>();
		if(record.getUsername()!=null && !record.getUsername().equals(""))
		{
			//验证用户名是否已存在		
			Member memberModel=memberDao.selectByUserName(record.getUsername());
			if(memberModel!=null && memberModel.getId()!=record.getId())
			{			
				resp.setCode(MemberEnum.UserNameIsExist.value());
				resp.setMsg(MemberEnum.UserNameIsExist.desc());
				return resp;			
			}
		}
		int row= memberDao.updateByPrimaryKeySelective(record);		
		if(row<=0)
		{
			resp.setCode(MemberEnum.Err.value());
			resp.setMsg(MemberEnum.Err.desc());
			return resp;	
		}
		resp.setCode(MemberEnum.Success.value());
		resp.setMsg(MemberEnum.Success.desc());		
		return resp;
	}
	
 	@Override
	public Member getById(Long id) {
 		return memberDao.selectByPrimaryKey(id);
	}
 	
	@Override
	public Member selectByPhoneNo(String phoneno) {
		return memberDao.selectByPhoneNo(phoneno);
	}
 	/**
 	 * 发送验证码
 	 */
	@Override
	public SendCodeResp sendCode(SendCodeReq req) {
		String key = "";
		String phoneNo = req.getPhoneNo();
		String content = "";// "欢迎您的使用，验证码：#验证码#，请妥善保管相关信息。若非您本人操作，请忽略。";
		//获取验证码类型
		SendCodeType codeType=SendCodeType.getEnum(req.getType());
		SendCodeResp resp=new SendCodeResp();
		if(codeType==null)//类型不存在
		{
			resp.setCode(-1);
			resp.setMessage("不存在的类型!");
			return resp;
		}
		boolean phoneIsExist	=memberDao.selectByPhoneNo(phoneNo)!=null;//不为空 存在.否则不存在
		switch (codeType) {
		case Register:{//注册
			if(phoneIsExist)//手机号已经存在
			{
				resp.setCode(-1);
				resp.setMessage("该手机号已经存在,不能注册!");
			}
			key = String.format(RedissCacheKey.JF_Member_Register ,phoneNo);
			content = "验证码#验证码#，您正在注册易淘众筹，请勿向他人泄露短信验证码。";
		}break;
		case ForgetPassword:{//忘记密码
			if(!phoneIsExist)//手机号不存在
			{
				resp.setCode(-1);
				resp.setMessage("该手机号不存在,不能找回密码!");
			}
			key = String.format(RedissCacheKey.JF_Member_ForgetPassword ,phoneNo);
			content = "验证码#验证码#，您正在找回易淘众筹登录密码，请勿向他人泄露短信验证码。";
			
		}break;
		case SetPayPassWord:{//设置支付密码
			if(!phoneIsExist)//手机号不存在
			{
				resp.setCode(-1);
				resp.setMessage("该手机号不存在,不能设置支付密码!");
			}
			key = String.format(RedissCacheKey.JF_Member_SetPayPassWord ,phoneNo);
			content = "验证码#验证码#，您正在设置易淘众筹支付密码，请勿向他人泄露短信验证码。";
			
		}break;
		case FindPayPassWord:{//找回支付密码
			if(!phoneIsExist)//手机号不存在
			{
				resp.setCode(-1);
				resp.setMessage("该手机号不存在,不能找回支付密码!");
			}
			key = String.format(RedissCacheKey.JF_Member_FindPayPassWord ,phoneNo);
			content = "验证码#验证码#，您正在找回易淘众筹支付密码，请勿向他人泄露短信验证码";
			
		}break;
		case ChangePhone:{//修改手机绑定
			if(!phoneIsExist)//手机号不存在
			{
				resp.setCode(-1);
				resp.setMessage("该手机号不存在,不能修改手机绑定!");
			}
			key = String.format(RedissCacheKey.JF_Member_ChangePhone ,phoneNo);
			content = "验证码#验证码#，您正在找回易淘众筹支付密码，请勿向他人泄露短信验证码";
			
		}break;
		case BindNewPhone:{//绑定新手机
			if(phoneIsExist)//手机号存在
			{
				resp.setCode(-1);
				resp.setMessage("该手机号存在,不能绑定!");
			}
			key = String.format(RedissCacheKey.JF_Member_BindNewPhone ,phoneNo);
			content = "验证码#验证码#，您正在绑定此手机号，请勿向他人泄露短信验证码。";
			
		}break;
		default:
			break;
		}
		String code = RandomCodeStrGenerator.generateCodeNum(6);// 获取随机数
		content = content.replace("#验证码#", code);
		redisService.set(key, code, 60 * 5);
		try {
			//发送验证码
			long resultValue = SmsUtils.sendSMS(phoneNo, content);
			if (resultValue <= 0) {
				resp.setCode(-1);
				resp.setMessage("验证码发送失败!");
				return resp;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		resp.setCode(1);
		resp.setMessage("验证码发送成功!");
		return resp;
	}
	/**
	 * 注册用户
	 */
	@Override
	public  HttpResultModel<Member> regist(RegistReq req) {
		HttpResultModel<Member>resultModel=new HttpResultModel<Member>();
		String phoneNo = req.getPhoneNo();
		boolean phoneIsExist	=memberDao.selectByPhoneNo(phoneNo)!=null;//不为空 存在.否则不存在
		if(phoneIsExist){//手机号已经存在
			resultModel.setCode(-1);
			resultModel.setMsg("该手机号已经存在,不能注册!");
			return resultModel;
		}
		String 	key = String.format(RedissCacheKey.JF_Member_Register ,phoneNo);
		String RedisCode=redisService.get(key,String.class);
		// 缓存验证码为空		参数验证码为空					验证码不匹配
		if(RedisCode==null||req.getVeriCode()==null||!req.getVeriCode().equals(RedisCode))
		{
			resultModel.setCode(-1);
			resultModel.setMsg("验证码错误,请重新输入");
			return resultModel;
		}
		//注册
		Member register=new Member();
		register.setPhoneno(req.getPhoneNo());
		register.setLoginpwd(MD5Util.MD5(req.getPwd()));
		register.setUsername("etao_"+req.getPhoneNo());
		memberDao.insertSelective(register);
		resultModel.setCode(1);
		resultModel.setData(register);
		resultModel.setMsg("注册成功!");
		return resultModel;
	}	
	@Override
	public PagedResponse<MemberModel> getMemberList(PagedMemberReq req) {
		return memberDao.getMemberList(req);
	}	

	/**
	 * 实名认证
	 * @param 
	 * @author hulingbo
	 * @date 2016年3月25日10:34:40
	 * @return
	 */
	@Override
	public HttpResultModel<MemberResp> Certification(Member record) {
		HttpResultModel<MemberResp> resp = new HttpResultModel<MemberResp>();
	
		//验证
		if(record.getId()==null || record.getId().equals(""))
		{
			resp.setCode(MemberCertificationEnum.IdIsNULL.value());
			resp.setMsg(MemberCertificationEnum.IdIsNULL.desc());
			return resp;			
		}
		if(record.getTruename()==null || record.getTruename().equals(""))
		{
			resp.setCode(MemberCertificationEnum.TrueNameIsNULL.value());
			resp.setMsg(MemberCertificationEnum.TrueNameIsNULL.desc());
			return resp;			
		}
		if(record.getIdcard()==null || record.getIdcard().equals(""))
		{
			resp.setCode(MemberCertificationEnum.IdCardIsNULL.value());
			resp.setMsg(MemberCertificationEnum.IdCardIsNULL.desc());
			return resp;			
		}		
		
		int row=memberDao.updateByPrimaryKeySelective(record);
		if(row<=0)
		{
			resp.setCode(MemberCertificationEnum.Err.value());
			resp.setMsg(MemberCertificationEnum.Err.desc());
			return resp;	
		}
		resp.setCode(MemberCertificationEnum.Success.value());
		resp.setMsg(MemberCertificationEnum.Success.desc());		
		return resp;
	}	


}
