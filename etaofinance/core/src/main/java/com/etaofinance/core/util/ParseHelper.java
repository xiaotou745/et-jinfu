package com.etaofinance.core.util;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.etaofinance.core.security.DES;

public class ParseHelper {
	public static short ToShort(Object o, short defaultValue) {
		short result = defaultValue;
		try {
			result = Short.parseShort(o.toString());
		} catch (Exception e) {

		}
		return result;
	}

	/**
	 * 
	 * @param o
	 * @return
	 */
	public static short ToShort(Object o) {
		return ToShort(o, (short) 0);
	}
	public static String ToString(Object o,String defaultValue) {
		if (o==null) {
		   return defaultValue;	
		}
		return o.toString();
	}
	public static int ToInt(Object o, int defaultValue) {
		int result = defaultValue;
		if (o==null) {
			return result;
		}
		try {
			result = Integer.parseInt(o.toString());
		} catch (Exception e) {
			System.out.println(e);
		}
		return result;
	}

	/**
	 * 
	 * @param o
	 * @return
	 */
	public static int ToInt(Object o) {
		return ToInt(o, 0);
	}

	/**
	 * 
	 * @param o
	 * @param defaultValue
	 * @return
	 */
	public static long ToLong(Object o, long defaultValue) {
		long result = defaultValue;
		try {
			result = Long.parseLong(o.toString());
		} catch (Exception e) {

		}
		return result;
	}
	
	/**
	 * 
	 * @param o
	 * @param defaultValue
	 * @return
	 */
	public static double ToDouble(Object o, double defaultValue) {
		double result = defaultValue;
		try {
			result = Double.parseDouble(o.toString());
		} catch (Exception e) {

		}
		return result;
	}

	/**
	 * 
	 * @param o
	 * @param strFormat
	 * @return
	 * @throws ParseException
	 */
	public static Date ToDate(String o) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (o.length()==10) {
			sdf = new SimpleDateFormat("yyyy-MM-dd");
		}else if(o.length()<10) {
			sdf =new SimpleDateFormat("yyyy-M-d");
		}
		Date defaultDate = null;
		try {
			defaultDate = sdf.parse(o);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return defaultDate;
	}

	/**
	 * 
	 * @param o
	 * @param strFormat
	 * @return
	 * @throws ParseException
	 */
	public static Date ToDate(String o, String strFormat) throws ParseException {
		if (strFormat == null || strFormat.isEmpty()) {
			strFormat = "yyyy-MM-dd HH:mm:ss";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(strFormat);
		Date defaultDate = sdf.parse(o);
		return defaultDate;
	}
	/**
	 * 
	 * @param o
	 * @param strFormat
	 * @return
	 */
	public static String ToDateString(Date o, String strFormat) {
		if (o == null) {
			return "";
		}
		if (strFormat == null || strFormat.isEmpty()) {
			strFormat = "yyyy-MM-dd HH:mm:ss";
		}
		DateFormat sdf = new SimpleDateFormat(strFormat);
		String defaultDate = "";
		defaultDate = sdf.format(o);
		return defaultDate;
	}

	/**
	 * 
	 * @param o
	 * @return
	 */
	public static String ToDateString(Date o) {
		return ToDateString(o, null);
	}

	/**
	 * 
	 * @param o
	 * @return
	 */
	public static String ShowString(Object o) {
		if (o == null) {
			return "";
		} else {
			return o.toString();
		}
	}

	/**
	 * 日期加减帮助方法
	 * 
	 * @author 赵海龙
	 * @Date 20150817
	 * @param dt
	 *            要操作的日期对象
	 * @param type
	 *            0是年，1是月，2是日  3
	 * @param dif
	 *            需要加减的值，负数时，表示减
	 * @return
	 */
	public static Date plusDate(Date dt, int type, int dif) {
		Calendar rightNow = Calendar.getInstance();
		rightNow.setTime(dt);
		switch (type) {
		case 0:
			rightNow.add(Calendar.YEAR, dif);// 日期加n年
			break;
		case 1:
			rightNow.add(Calendar.MONTH, dif);// 日期加n月
			break;
		case 2:
			rightNow.add(Calendar.DAY_OF_YEAR, dif);// 日期加n天
			break;
		case 3:
			rightNow.add(Calendar.HOUR, dif);// 日期加n小时
			break;
		case 4:
			rightNow.add(Calendar.MINUTE, dif);// 时间加n分钟
			break;
		default:
			break;
		}

		Date dt1 = rightNow.getTime();
		return dt1;
	}

	/**
	 * DES解密   所有解密的数据都先统一转换成大写再解密,和旧版后台一致
	 * @author pengyi
	 * @date 20150828
	 * @param text
	 * @return
	 */
	public static String toDecrypt(String text) {
		try {
			if (StringUtils.isEmpty(text.toUpperCase()))
				return "";
			return DES.decrypt(text);
		} catch (Exception ex) {
			return text;
		}
	}
	
	/**
	 * DES加密   所有加密的数据都统一转换成大写,和旧版后台一致
	 * @author pengyi
	 * @date 20150911
	 * @param text
	 * @return
	 */
	public static String encrypt(String text) {
		try {
			if (StringUtils.isEmpty(text))
				return "";
			return DES.encrypt(text).toUpperCase();
		} catch (Exception ex) {
			return text;
		}
	}
	
	/**
	 * 判断当月有几天
	 * @author 茹化肖
	 * @date 20150828
	 * @param text
	 * @return
	 */
	public static Integer GetMixDay(String year,String month) {
		try {
			Calendar cal = Calendar.getInstance(); 
			cal.set(Calendar.YEAR,Integer.parseInt(year)); 
			cal.set(Calendar.MONTH, Integer.parseInt(month) - 1);//Java月份从0开始算 
			int dateOfMonth = cal.getActualMaximum(Calendar.DATE); 
			return dateOfMonth;
			
		} catch (Exception ex) {
			return 0;
		}
		
	}
	
	/**
	 * 获取当月第一天
	 * @author 茹化肖
	 * @param 
	 * @return 返回当月第一天日期 yyyy-MM-dd
	 */
	public static String GetCurrentMonthFirstDay()
	{   
        //获取前月的第一天
        Calendar   cal_1=Calendar.getInstance();//获取当前日期 
        cal_1.set(Calendar.DAY_OF_MONTH,1);//设置为1号,
        return ToDateString(cal_1.getTime(), "yyyy-MM-dd");
	}
	/**
	 * 获取时间中的月份
	 * @author 茹化肖
	 * @param 1:年 2:月  3:日 4:时 :5:分  6:秒
	 * @return 返回对应类型数值
	 */
	public static Integer GetInDate(Date date,int type)
	{
		int ret=0;
		try {
			Calendar c = Calendar.getInstance();//可以对每个时间域单独修改
			int year = c.get(Calendar.YEAR);//年
			int month = c.get(Calendar.MONTH)+1;//月
			int day = c.get(Calendar.DATE);//日
			int hour = c.get(Calendar.HOUR_OF_DAY);//时
			int minute = c.get(Calendar.MINUTE);//分
			int second = c.get(Calendar.SECOND); //秒
			switch (type) {
			case 1: ret=year; break;
			case 2: ret=month; break;
			case 3: ret=day; break;
			case 4: ret=hour; break;
			case 5: ret=minute; break;
			case 6: ret=second; break;
			}
			return ret;
			
		} catch (Exception ex) {
			return 0;
		}
	}
	
	/**
	 * 按照指定的精度保留n位小数
	 * @param numVaule 要处理的数值
	 * @param digits 小数位数
	 * @date 20151013
	 * @author hailongzhao
	 * @return
	 */
	public static String digitsNum(Object numVaule, int digits){
		if (digits<0) {
			digits=2;
		}
		String f=".0000000000";
		String aString=f.substring(0, digits+1);
		if (Double.parseDouble(numVaule.toString())==0d) {
			return "0"+aString;
		}
		DecimalFormat decimalFormat=new DecimalFormat(aString);//构造方法的字符格式这里如果小数不足2位,会以0补足.
		String reString= decimalFormat.format(numVaule);
		if(reString.indexOf(".")==reString.length()-1)
		{
			reString=reString.replace(".", "");
		}
		return reString;
	}
	
	/**
	 * 
	 * 获取大图
	 * @param url
	 * @return
	 */
	public static String getBigPic(String url)
	{
		String bigpicurl="";
		if(url==null||url.equals(""))
			return "";
		
		String filenameString=url.replaceAll("[.][^.]+$", "");//文件名
		String fileex=url.substring(url.lastIndexOf(".")+1);//文件后最
		return filenameString+"_0_0."+fileex;
	}
}
