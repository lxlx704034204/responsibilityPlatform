package com.orbit.utils;

import java.text.DecimalFormat;

public class NumericUtils {
	
	public static String DECIMAL_BASIC_FORMAT = "###0.######";
	public static String DECIMAL_2_FORMAT = "###0.00";
	public static String PERCENT_2_FORMAT = "##0.00%";
	public static String INTEGER_FORMAT = "0";
	
	public static DecimalFormat DECIMAL_BASIC_FORMATTER = new DecimalFormat(DECIMAL_BASIC_FORMAT);
	public static DecimalFormat DECIMAL_2_FORMATTER = new DecimalFormat(DECIMAL_2_FORMAT);
	public static DecimalFormat PERCENT_2_FORMATTER = new DecimalFormat(PERCENT_2_FORMAT);
	public static DecimalFormat INTEGER_FORMATTER = new DecimalFormat(INTEGER_FORMAT);
	
	public static Double parseDouble(String str){
		Double d = null;
		try{
			if(!StrUtils.isNullOrEmpty(str)){
				d = Double.parseDouble(str);
			}
		}catch(NumberFormatException e){
			d = null;
		}
		return d;
	}
	
	public static Double parsePercent(String str){
		Double d = null;
		try{
			if(!StrUtils.isNullOrEmpty(str)){
				String num = str.substring(0, str.indexOf("%"));
				Double tmp = Double.parseDouble(num);
				d = tmp != null ? tmp / 100 : null;
			}
		}catch(NumberFormatException e){
			d = null;
		}
		return d;
	}
	
	public static Integer parseInteger(String str){
		Integer i = null;
		Double d = parseDouble(str);
		if(d != null){
			i = Integer.parseInt(INTEGER_FORMATTER.format(d));
		}
		return i;
	}
}
