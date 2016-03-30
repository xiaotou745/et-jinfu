package com.etaofinance.wap.common;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
@Documented
@Retention(RetentionPolicy.RUNTIME)//
@Target({ElementType.METHOD, ElementType.TYPE})//该注解修饰类中的方法
@Inherited
public @interface RequireLogin{
 /**
  * 登录验证注解
  * 该注解可以标记Controller 或 Controller 中的方法.
  * 如果Controller 有该标记,那么这个Controller下面所有的方法都会被过滤器
  * 进行验证
  * 如果Controller 没有有该标记,但Controller中的某个方法拥有该标记
  * 那么这个方法将被过滤器验证(其他没有被标记的不会被验证)
  * 
  * 特别注意,如果一个Controller 被标记RequireLogin 需要验证
  * 但是其中某些方法不想被验证.请参见NoRequireLogin标记
  * 
  * 茹化肖 2016年3月30日10:51:13
  */
}
