package com.etaofinance.core.util;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import com.etaofinance.core.sms.SmsAPI;

public class SmsUtils {

	/**
	 * 发送短信 2015年9月28日 13:12:08
	 * 
	 * @author haichao
	 * @param Mobile
	 *            手机号码，多个号码‘,’号隔开
	 * @param Content
	 *            发送内容,如果是验证码可以用{num}直接传参，会产生6位随机数
	 * **/
	public static long sendSMS(String Mobile, String Content)
			throws MalformedURLException, UnsupportedEncodingException {
		SmsAPI.sendSmsSaveLogB2B(Mobile, Content);//易淘通道
//		SaltLightSmsAPI.SendSaliLightSms(Mobile, Content);//盐光通道
		return 1;
	}
	/**
	 * 发送语音短信 2015年11月11日 
	 * 
	 * @author CaoHeYang
	 * @param Mobile
	 *            手机号码，多个号码‘,’号隔开
	 * @param Content
	 *            发送内容
	 * **/
	public static long sendVoiceSMS(String Mobile, String Content)
			throws MalformedURLException, UnsupportedEncodingException {
		SmsAPI.sendSmsSaveLogNew(Mobile, Content);//易淘通道
		return 1;
	}
}
