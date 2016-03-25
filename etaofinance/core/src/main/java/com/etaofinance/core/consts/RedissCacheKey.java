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
    public static final String LOGIN_COUNT_B = "JF_LoginCount_B_";//商家登录次数
    public final static String Business_LOGIN_COOKIE = "JF_Business_login_";//登录Cookie的key,对应redis的缓存key
    public final static String GroupBusiness_LOGIN_COOKIE = "GroupBusiness_login_";
    public static final String Order_TimeSpan = "JF_jOrder_TimeSpan_";//商家发单时间戳
    public static final String Menu_Auth = "JF_Menu_Auth_";//用户有权限的菜单
    public static final String GlobalConfig_Key ="GlobalConfig_%s_0";
    
    public static final String SSCancelOrder = "JF_SSCancelOrder";
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
