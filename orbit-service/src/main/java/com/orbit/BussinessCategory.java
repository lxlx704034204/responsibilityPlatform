package com.orbit;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 表示业务种类的annotation,作用在method上,表示多种业务方法属于同一种业务
 */
@Retention(value = RUNTIME)
@Target(ElementType.METHOD)
public @interface BussinessCategory {
  String category();//业务类型,例如测控、异常监测等
}
