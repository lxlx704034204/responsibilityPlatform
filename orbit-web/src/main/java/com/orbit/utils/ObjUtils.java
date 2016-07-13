package com.orbit.utils;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

public class ObjUtils {
	
	public static String toString(Object obj){
		
		if(obj == null){
			return "null";
		}
		Class<?> cl = obj.getClass();
		if(cl == String.class){
			return (String)obj;
		}
		if(cl.isArray()){
			List<String> eStrs = new ArrayList<String>();
			int length = Array.getLength(obj);
			for(int i = 0; i < length; i++){
				Object e = Array.get(obj, i);
				eStrs.add(e.toString());
			}
			return "[" + StringUtils.join(eStrs, ", ") + "]";
//			return Arrays.toString((Array)obj);
//			Arrays.to
		}
		
		List<String> fieldStrs = new ArrayList<String>();
		do{
			Field[] fields = cl.getDeclaredFields();
			AccessibleObject.setAccessible(fields, true);
			for(int i = 0; i < fields.length; i++){
				Field field = fields[i];
				StringBuilder fieldStr = new StringBuilder();
				fieldStr.append(field.getName());
				fieldStr.append("(");
				fieldStr.append(field.getType().getSimpleName());
				fieldStr.append(")");
				fieldStr.append(": ");
				try {
					Object fieldVal = field.get(obj);
					fieldStr.append(String.valueOf(fieldVal));
				} catch (IllegalArgumentException | IllegalAccessException e) {
					fieldStr.append("#ERROR#");
				} 
				fieldStrs.add(fieldStr.toString());
			}
			
			cl = cl.getSuperclass();
		} while(cl != null);
		return "{" + StringUtils.join(fieldStrs, ", ") + "}";
	}
}
