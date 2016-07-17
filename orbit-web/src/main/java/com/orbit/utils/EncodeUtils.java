package com.orbit.utils;

public class EncodeUtils {
	
	public static String gbToUnicode(String gbStr){
		StringBuilder res = new StringBuilder();
		char[] charArr = gbStr.toCharArray();
		for(int charIndex = 0; charIndex < charArr.length; charIndex ++){
			char ch = gbStr.charAt(charIndex);
			if(ch >= 0 && ch <= 255){
				res.append(ch);
			} else {
				res.append("\\u" + Integer.toHexString(ch));
			}
		}
		return res.toString();
	}
}
