package com.etaofinance.core.util;

public class Convert {
	
	public static String ToString(Object o, String defaultValue) {
		
		String result = defaultValue;
		
		double d = ParseHelper.ToDouble(o, 0);
		if(0<d && d<1000){
		 return  ParseHelper.ToString(ParseHelper.ToLong(Math.round(d),0),"")+"m";
		}
		if(d>1000){
			return ParseHelper.ToString(ParseHelper.ToLong(Math.round(d/1000),0),"")+"km";
		} 
		 
		return result;
	}
	/**
	 * 转换金额
	 * @param money
	 * @return
	 */
	public static String toMoneyString(Float money)
	{
		return toMoneyString(money,"￥");
	}
	public static String toMoneyString(Float money,String flag)
	{
		if(money>=10000)
		{
			return flag+(money/10000*1.0)+"万";
		}
		return flag+money;
	}

}
