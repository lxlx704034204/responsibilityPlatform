package com.orbit.utils;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class IocUtils {
	
	public static String APPCONTEXT_NAME = "ApplicationContext.xml";
	
	/**
	 * 获取src目录下的ApplicationContext
	 * 
	 * @param contextFilePath ApplicationContext.xml文件路径
	 * */
	public static ApplicationContext getApplicationContext(String contextFilePath){
		ApplicationContext appcnt = new ClassPathXmlApplicationContext(contextFilePath);;
		return appcnt;
	}
	
	/**
	 * 获取src目录下的ApplicationContext
	 * 默认路径为src/ApplicationContext.xml。
	 * 
	 * */
	public static ApplicationContext getApplicationContext(){
		return getApplicationContext(APPCONTEXT_NAME);
	}
	
	/**
	 * 获取web.xml中配置的ApplicationContext。
	 * 
	 * @param servCtx ServletContext
	 * */
	public static ApplicationContext getWebApplicationContext(ServletContext servCtx){
		ApplicationContext appcnt = WebApplicationContextUtils.getWebApplicationContext(servCtx);
		return appcnt;
	}
}
