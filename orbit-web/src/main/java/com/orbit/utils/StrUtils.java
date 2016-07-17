package com.orbit.utils;

public class StrUtils {
	
	public final static String EMPTY = "";
	
	public static boolean isNullOrEmpty(String s){
		return s == null || s.trim().length() == 0;
	}
	
	public static String valueOf(Object obj){
		return obj == null ? null : obj.toString();
	}
}
