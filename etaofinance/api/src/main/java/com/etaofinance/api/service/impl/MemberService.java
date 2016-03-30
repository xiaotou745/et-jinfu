package com.etaofinance.api.service.impl;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etaofinance.api.common.LoginHelper;
import com.etaofinance.api.dao.inter.IMemberDao;
import com.etaofinance.api.dao.inter.IMemberOtherDao;
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
import com.etaofinance.core.util.RegexHelper;
import com.etaofinance.core.util.SmsUtils;
import com.etaofinance.entity.Member;
import com.etaofinance.entity.MemberOther;
import com.etaofinance.entity.req.ForgetPwdOneReq;
import com.etaofinance.entity.req.ForgetPwdThreeReq;
import com.etaofinance.entity.req.ForgetPwdTwoReq;
import com.etaofinance.entity.req.ModifyMemberReq;
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
public class MemberService implements IMemberService{
	@Autowired
	private IMemberDao memberDao;
	@Autowired
	private IMemberOtherDao memberOtherDao;
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

 	/**
 	 * 手机号获取信息
 	 */
	@Override
	public Member selectByPhoneNo(String phoneno) {
		return memberDao.selectByPhoneNo(phoneno);
	}

 	/**
 	 * 发送验证码
 	 */
	@Override
	public HttpResultModel<Object> sendCode(SendCodeReq req) {
		String key = "";
		String phoneNo = req.getPhoneNo();
		String content = "";// "欢迎您的使用，验证码：#验证码#，请妥善保管相关信息。若非您本人操作，请忽略。";
		//获取验证码类型
		SendCodeType codeType=SendCodeType.getEnum(req.getType());
		HttpResultModel<Object> resp=new HttpResultModel<Object>();
		if(codeType==null)//类型不存在
		{
			resp.setCode(-1);
			resp.setMsg("不存在的类型!");
			return resp;
		}
		boolean phoneIsExist	=memberDao.selectByPhoneNo(phoneNo)!=null;//不为空 存在.否则不存在
		switch (codeType) {
		case Register:{//注册
			if(phoneIsExist)//手机号已经存在
			{
				resp.setCode(-1);
				resp.setMsg("该手机号已经存在,不能注册!");
			}
			key = String.format(RedissCacheKey.JF_Member_Register ,phoneNo);
			content = "验证码#验证码#，您正在注册易淘众筹，请勿向他人泄露短信验证码。";
		}break;
		case ForgetPassword:{//忘记密码
			if(!phoneIsExist)//手机号不存在
			{
				resp.setCode(-1);
				resp.setMsg("该手机号不存在,不能找回密码!");
			}
			key = String.format(RedissCacheKey.JF_Member_ForgetPassword ,phoneNo);
			content = "验证码#验证码#，您正在找回易淘众筹登录密码，请勿向他人泄露短信验证码。";
			
		}break;
		case SetPayPassWord:{//设置支付密码
			if(!phoneIsExist)//手机号不存在
			{
				resp.setCode(-1);
				resp.setMsg("该手机号不存在,不能设置支付密码!");
			}
			key = String.format(RedissCacheKey.JF_Member_SetPayPassWord ,phoneNo);
			content = "验证码#验证码#，您正在设置易淘众筹支付密码，请勿向他人泄露短信验证码。";
			
		}break;
		case FindPayPassWord:{//找回支付密码
			if(!phoneIsExist)//手机号不存在
			{
				resp.setCode(-1);
				resp.setMsg("该手机号不存在,不能找回支付密码!");
			}
			key = String.format(RedissCacheKey.JF_Member_FindPayPassWord ,phoneNo);
			content = "验证码#验证码#，您正在找回易淘众筹支付密码，请勿向他人泄露短信验证码";
			
		}break;
		case ChangePhone:{//修改手机绑定
			if(!phoneIsExist)//手机号不存在
			{
				resp.setCode(-1);
				resp.setMsg("该手机号不存在,不能修改手机绑定!");
			}
			key = String.format(RedissCacheKey.JF_Member_ChangePhone ,phoneNo);
			content = "验证码#验证码#，您正在找回易淘众筹支付密码，请勿向他人泄露短信验证码";
			
		}break;
		case BindNewPhone:{//绑定新手机
			if(phoneIsExist)//手机号存在
			{
				resp.setCode(-1);
				resp.setMsg("该手机号存在,不能绑定!");
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
				resp.setMsg("验证码发送失败!");
				return resp;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		resp.setCode(1);
		resp.setMsg("验证码发送成功!");
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
		//清除验证码
		redisService.remove(key);
		//注册
		Member register=new Member();
		register.setPhoneno(req.getPhoneNo());
		register.setLoginpwd(MD5Util.MD5(req.getPwd()));
		register.setUsername("etao_"+req.getPhoneNo());
		memberDao.insertSelective(register);
		MemberOther moMemberOther=new MemberOther();
		moMemberOther.setMemberid(register.getId());
		memberOtherDao.insertSelective(moMemberOther);
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
	/**
	 * 通过ID获取会员信息
	 */
	@Override
	public Member getById(Long id) {
		
		return memberDao.selectByPrimaryKey(id);
	}
	/**
	 * 忘记密码第一步
	 */
	@Override
	public HttpResultModel<ForgetPwdResp> forgetpwdsetpone(ForgetPwdOneReq req) {
		HttpResultModel<ForgetPwdResp> resultModel=new HttpResultModel<ForgetPwdResp>();
		String redisImgCode=redisService.get(req.getCookieKey(), String.class);
		if(redisImgCode==null||redisImgCode.equals("")||!redisImgCode.equals(req.getImgCode()))
		{
			//验证码错误
			resultModel.setCode(-1);
			resultModel.setMsg("验证码错误,请重试");
			return resultModel;
		}
		//查询会员是否存在
		Member member=null;
		//1.手机号
		if(RegexHelper.IsPhone(req.getLoginName()))
		{
			member=memberDao.selectByPhoneNo(req.getLoginName());
		}
		//2.邮箱
		if(member==null&&RegexHelper.IsEmail(req.getLoginName()))
		{
			member=memberDao.selectByemail(req.getLoginName());
		}
		//3.登录名
		if(member==null)
		{
			member=memberDao.selectByUserName(req.getLoginName());
		}
		if(member==null)
		{
			resultModel.setCode(-1);
			resultModel.setMsg("用户邮箱/登录名/手机号错误!");
			return resultModel;
		}
		//给缓存设置一个UUID 5分钟 第二步进来的时候验证用
		String value=UUID.randomUUID().toString();
		String key=String.format(RedissCacheKey.JF_Member_FindPassWordSetpOne,value);
		redisService.set(key, value,60*5,TimeUnit.SECONDS);
		//验证码错误
		resultModel.setCode(1);
		resultModel.setMsg("验证通过!");
		ForgetPwdResp resp=new ForgetPwdResp();
		resp.setUserID(member.getId());
		resp.setCheckKey(value);
		return resultModel;
	}
	/**
	 * 找回密码第二步
	 * 
	 */
	@Override
	public HttpResultModel<ForgetPwdResp> forgetpwdsetptwo(ForgetPwdTwoReq req) {
		HttpResultModel<ForgetPwdResp> resultModel=new HttpResultModel<ForgetPwdResp>();
		//手机验证码
		String key= String.format(RedissCacheKey.JF_Member_ForgetPassword ,req.getPhoneNo());
		String value=redisService.get(key, String.class);
		//第一步给返回的UUID
		String key2=String.format(RedissCacheKey.JF_Member_FindPassWordSetpOne,req.getCacheKey());
		String value2=redisService.get(key2, String.class);
		if(req.getPhoneNo()==null||req.getVeriCode()==null||value==null||req.getCacheKey()==null||value2==null
				||req.getPhoneNo().equals("")||req.getVeriCode().equals("")||value.equals("")
				||req.getCacheKey().equals("")||value2.equals("")
				||!req.getVeriCode().equals(value)||!req.getCacheKey().equals(value2))
		{
			resultModel.setCode(-1);
			resultModel.setMsg("验证码错误,请重试!");
			return resultModel;
		}
		//删除第一次给的UUID,防止被重复使用
		redisService.remove(key2);
		//查询会员是否存在
		Member member=memberDao.selectByPhoneNo(req.getPhoneNo());
		if(member==null)
		{
			//验证码错误
			resultModel.setCode(-1);
			resultModel.setMsg("用户不存在!");
			return resultModel;
		}
		//给缓存设置一个UUID 5分钟 第二步进来的时候验证用
		String UUIDvalue=UUID.randomUUID().toString();
		String UUIDkey=String.format(RedissCacheKey.JF_Member_FindPassWordSetpTwo,value);
		redisService.set(UUIDkey, UUIDvalue,60*5,TimeUnit.SECONDS);
		resultModel.setCode(1);
		resultModel.setMsg("验证通过!");
		ForgetPwdResp resp=new ForgetPwdResp();
		resp.setUserID(member.getId());
		resp.setCheckKey(UUIDvalue);
		return resultModel;
	}
	/**
	 * 找回密码第三部
	 */
	@Override
	public HttpResultModel<ForgetPwdResp> forgetpwdsetpthree(
			ForgetPwdThreeReq req) {
		HttpResultModel<ForgetPwdResp> resultModel=new HttpResultModel<ForgetPwdResp>();
		//第二步给返回的UUID
		String key=String.format(RedissCacheKey.JF_Member_FindPassWordSetpTwo,req.getCacheKey());
		String value=redisService.get(key, String.class);
		if(req.getCacheKey()==null||req.getCacheKey().equals("")||value==null||value.equals(""))
		{
			resultModel.setCode(-1);
			resultModel.setMsg("验证信息有误,请重新找回密码!");
			return resultModel;
		}
		if(req.getNewPwd()==null||req.getNewPwd().equals("")||
				req.getNewPwd().length()<6||req.getNewPwd().length()>20)
		{
			resultModel.setCode(-1);
			resultModel.setMsg("密码长度需要6-20位,请重试!");
			return resultModel;
		}
		//删除第二步给的缓存,.
		redisService.remove(key);
		//设置新密码
		String nPwdString=MD5Util.MD5(req.getNewPwd());
		Member member=new Member();
		member.setId(req.getUserId());
		member.setLoginpwd(nPwdString);
		int re= memberDao.updateByPrimaryKeySelective(member);
		if(re>0)
		{
			resultModel.setCode(1);
			resultModel.setMsg("新密码设置成功,请重新登录!");
			return resultModel;
		}
		resultModel.setCode(-1);
		resultModel.setMsg("新密码设置失败,请重新设置!");
		return resultModel;
	}
	/**
	 * 修改密码接口
	 */
	@Override
	public HttpResultModel<Object> modifypwd(ModifypwdReq req) {
		HttpResultModel<Object> res=new HttpResultModel<Object>();
		Member oldMember=memberDao.selectByPrimaryKey(req.getUserId());
		String opwd=MD5Util.MD5(req.getOldPwd());
		String npwd=MD5Util.MD5(req.getNewPwd());
		if(!oldMember.getLoginpwd().equals(opwd))
		{
			res.setCode(-1);
			res.setMsg("旧密码错误,请重试!");
			return res;
		}
		Member member=new Member();
		member.setId(req.getUserId());
		member.setLoginpwd(npwd);
		if(memberDao.updateByPrimaryKeySelective(member)>0)
		{
			res.setCode(1);
			res.setMsg("密码修改成功!");
			return res;
		}
		res.setCode(-1);
		res.setMsg("密码修改失败,请重试!");
		return res;
	}

	/**
	 * 通过用户吗获取信息
	 */
	@Override
	public Member selectByUserName(String username) {
		return memberDao.selectByUserName(username);
	}


	@Override
	public Member selectByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}
		@Override
	public int modifyMember(ModifyMemberReq req) {
		return memberDao.modifyMember(req);
	}


}
