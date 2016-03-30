package com.etaofinance.wap.common;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
@Documented
@Retention(RetentionPolicy.RUNTIME)//
@Target(ElementType.METHOD)//该注解修饰类中的方法
@Inherited
public @interface NoRequireLogin{
 /**
  * 不需要登录验证的方法注解注解
  * 该注解在Controller 标记了 RequireLogin 特性时
  * 某个方法不需要验证登录,那么为该方法标记该注解
  * 茹化肖 2016年3月30日10:47:16
  */
}