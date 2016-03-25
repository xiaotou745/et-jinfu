package com.etaofinance.core.util;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;

//import com.etaofinance.core.sms.SmsAPI;

public class SmsUtils {

	/**
	 * 发送短信 2015年9月28日 13:12:08
	 * 
	 * @author haichao
	 * @param Mobile
	 *            手机号码
	 * @param Content
	 * @throws FileNotFoundException 
	 *           
	 * **/
	public static long sendSMS(String Mobile, String Content) {
		Content=Content+"【易淘金服】";
		long res=yunXinMsg(Mobile,Content);
		if(res<1l)
		{
			res=guoDuMsg(Mobile,Content);
		}
		return res;
	}
	/**
	 * 发送语音短信 2015年11月11日 
	 * 
	 * @author CaoHeYang
	 * @param Mobile
	 *            手机号码
	 * @param Content
	 *            发送内容
	 * @throws FileNotFoundException 
	 * **/
	public static long sendVoiceSMS(String Mobile, String Content) {
		return yunXinVoiceMsg(Mobile,Content);
	}
	/**
	 * 发送语音短信 2015年11月11日 
	 * 
	 * @author CaoHeYang
	 * @param Mobile
	 *            手机号码
	 * @param Content
	 *            发送内容
	 * @throws FileNotFoundException 
	 * **/
	public static long sendYingXiaoSMS(String Mobiles, String Content) {
		Content=Content+"【易淘金服】";
		String[] phones=Mobiles.split(",");
		
		if(phones!=null&&phones.length>0)
		{
			if(phones.length<200)//手机号在五百个以内.
			{
				yunXinMsgYingXiao(Mobiles,Content);
				return 1;
			}
			else//大于五百个
			{
				String temp="";
				for(int i=0;i<phones.length;i++)
				{
					temp+=phones[i]+",";
					if(i%200==0||i==phones.length-1)//200个为一组或者最后一组
					{
						yunXinMsgYingXiao(temp.substring(0,temp.length()-1),Content);
						temp="";
					}
				}
			}	
		}	
		
		return 0;
	}

	/**
	 * 云信高速触发类短信
	 * @param phones 手机号
	 * @param Content 短信内容
	 * @return
	 * @throws UnsupportedEncodingException 
	 * @throws FileNotFoundException 
	 */
	private static long yunXinMsg(String phone,String Content)
	{
		try{
		String url="http://h.1069106.com:1210/Services/MsgSend.asmx/SendMsg";
		String userCode="yitaoshi";
		String userPass="YTS5858";
		Content =java.net.URLEncoder.encode(Content,"utf-8");
		String param=String.format("userCode=%s&userPass=%s&DesNo=%s&Msg=%s&Channel=0", userCode,userPass,phone,Content);
		String res=HttpUtil.sendGet(url, param);
		if(res.contains(">-"))//包含>-发送失败
		{
			 return 0l;
		}	
		return 1l;
		}catch (Exception e)
		{
			 return 0l;
		}
	}
	/**
	 * 云信高速触发类语音验证码
	 * @param phones 手机号
	 * @param Content 短信内容
	 * @return
	 * @throws UnsupportedEncodingException 
	 * @throws FileNotFoundException 
	 */
	private static long yunXinVoiceMsg(String phone,String Code)
	
	{
		try
		{
			String url="http://h.1069106.com:1210/Services/MsgSend.asmx/SendVoiceCode";
			String userCode="yitaoshi";
			String userPass="YTS5858";
			String param=String.format("userCode=%s&userPass=%s&DesNo=%s&VoiceCode=%s&Channel=999&Amount=3", userCode,userPass,phone,Code);
			String res=HttpUtil.sendGet(url, param);
			if(res.contains(">-"))//包含>-发送失败
			{
				 return 0l;
			}	
			return 1l;
		}catch (Exception e)
		{
			 return 0l;
		}
		
	}
	/**
	 * 国都触发类短信
	 * @param phones 手机号
	 * @param Content 短信内容
	 * @return
	 * @throws UnsupportedEncodingException 
	 * @throws FileNotFoundException 
	 */
	private static long guoDuMsg(String phone,String Content)
	{
		try{
			String url="http://221.179.180.158:9007/QxtSms/QxtFirewall";
			String OperID="etjfyz";
			String OperPass="u6_DxE3C";
			Content =java.net.URLEncoder.encode(Content,"GBK");
			String param=String.format("OperID=%s&OperPass=%s&DesMobile=%s&Content=%s&SendTime=&ValidTime=&AppendID=", OperID,OperPass,phone,Content);
			String res=HttpUtil.sendGet(url, param);
			 if(res.contains("<code>03</code>"))//单条发送成功
			 {
				 return 1l;
			 }
			 return 0l;
		}catch (Exception e)
		{
			 return 0l;
		}
	}

	/**
	 * 云信营销类短信
	 * @param phones 手机号
	 * @param Content 短信内容
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	private static long yunXinMsgYingXiao(String phones,String Content)
	{
		try{
		String url="http://yes.itissm.com/api/MsgSend.asmx";
		String userCode="ytxk";
		String userPass="4000999177";
		Content =java.net.URLEncoder.encode(Content,"utf-8");
		String param=String.format("userCode=%s&userPass=%s&DesNo=%s&Msg=%s&Channel=183", userCode,userPass,phones,Content);
		String res=HttpUtil.sendGet(url, param);
		if(res.contains(">-"))//包含>-发送失败
		{
			 return 0l;
		}	
		return 1l;
		}catch (Exception e)
		{
			 return 0l;
		}
		
	}
}
