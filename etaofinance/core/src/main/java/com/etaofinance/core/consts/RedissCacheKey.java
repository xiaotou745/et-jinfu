package com.etaofinance.core.consts;


/**
 * redis 緩存 key 值
 * @author CaoHeYang
 * @modify pengyi
 */
public class RedissCacheKey {
	/**
	 * 获取开通城市缓存key
	 * redisservice中会统一增加java前缀和版本前缀
	 */
	public static final String JF_PublicProvinceCity = "JF_PublicProvinceCity";// 省份和城市

    public final static String LOGIN_COOKIE = "JF_UserInfo_%s";//登录Cookie的UUID 对应的缓存信息
  
    public static final String Menu_Auth = "JF_Menu_Auth_";//用户有权限的菜单
    public static final String GlobalConfig_Key ="JF_GlobalConfig_%s_0";//全局配置

    /**
     * 注册,修改密码,忘记密码
     */
    public static final String JF_Member_Register= "JF_Member_Register_%s";
    //public static final String JF_Member_UpdatePasswrd= "JF_Member_UpdatePasswrd_%s";
    public static final String JF_Member_ForgetPassword= "JF_Member_ForgetPassword_%s";
    public static final String JF_Member_SetPayPassWord= "JF_Member_SetPayPassWord_%s";
    public static final String JF_Member_FindPayPassWord= "JF_Member_FindPayPassWord_%s";
    public static final String JF_Member_ChangePhone= "JF_Member_ChangePhone_%s";
    public static final String JF_Member_BindNewPhone= "JF_Member_BindNewPhone_%s";
    //找回密码第一步缓存UUID
    public static final String JF_Member_FindPassWordSetpOne= "JF_Member_FindPassWordSetpOne_%s";
    public static final String JF_Member_FindPassWordSetpTwo= "JF_Member_FindPassWordSetpTwo_%s";
    /**
     * 广告轮播图
     */
    public static final String JF_ADvertList= "JF_ADvertList";
 
    /**
     *  商家端找回密码缓存KEY
     */
    public static final String  CheckCodeFindPwd_B = "JF_CheckCodeFindPwd_B_%s";
    /**
     * 商家注册缓存key
     */
    public static final String PostRegisterInfo_B ="JF_PostRegisterInfo_B_%s";
    /**
     * B端修改绑定手机号验证当前手机号
     */
    public static final String Business_SendCode_ModifyPhone ="JF_Business_SendCode_ModifyPhone__%s";
    /**
     * 修改绑定手机号验证新手机号
     */
    public static final String Business_SendCode_ModifyPhoneNewPhone ="JF_Business_SendCode_ModifyPhoneNewPhone__%s";
    
    /**
     * 开通城市
     * */
    public static final String PublicProvinceCity="JF_PublicProvinceCity";
}
