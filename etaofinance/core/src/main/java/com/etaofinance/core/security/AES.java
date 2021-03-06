package com.etaofinance.core.security;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * AES加解密
 * 
 * @author talver
 */
public class AES {
	final static String AES_KEY = "k;)*(+nmjdsf$#@d";

	/**
	 * AES加密
	 * 
	 * @param str
	 *            待加密字符串
	 * @return 加密后字符串
	 */
	public static String aesEncrypt(String str) {
		try {
			str=new String(str.getBytes("UTF-8"),"UTF-8");
			String password = AES_KEY;
			SecretKeySpec skeySpec = new SecretKeySpec(password.getBytes(), "AES");
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
			byte[] bs=cipher.doFinal(str.getBytes("UTF-8"));
			byte[] bs64= Base64.getEncoder().encode(bs);
			String strTmp = new String(bs64);
			return strTmp;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
		/*try {
			String password = AES_KEY;
			SecretKeySpec skeySpec = new SecretKeySpec(password.getBytes(), "AES");
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
			String strTmp = Base64.encodeToString(cipher.doFinal(str.getBytes()), Base64.DEFAULT);
			return strTmp;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;*/
	}

	/**
	 * AES解密
	 * 
	 * @param str
	 *            待解密字符串
	 * @return 解密后字符串
	 */
	public static String aesDecrypt(String str) {
		try {
			String password = AES_KEY;
			SecretKeySpec skeySpec = new SecretKeySpec(password.getBytes(), "AES");
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, skeySpec);
			byte [] bs= Base64.getDecoder().decode(str);
			String strTmp = new String(cipher.doFinal(bs),"UTF-8");
			return strTmp;
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return str;
		/*
		 	try {
			String password = AES_KEY;
			SecretKeySpec skeySpec = new SecretKeySpec(password.getBytes(), "AES");
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, skeySpec);
			String strTmp = new String(cipher.doFinal(Base64.decode(str, Base64.DEFAULT)));
			return strTmp;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return str;*/
	}
}
