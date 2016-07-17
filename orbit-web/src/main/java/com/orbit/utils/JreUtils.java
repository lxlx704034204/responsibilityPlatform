package com.orbit.utils;

public class JreUtils {
	
	public static String getVersion(){
		return System.getProperty("java.version");
	}
	
	public static String getVendor(){
		return System.getProperty("java.vendor");
	}
	
	public static String getVendorUrl(){
		return System.getProperty("java.vendor.url");
	}
	
	public static String getClassPath(){
		return System.getProperty("java.class.path");
	}
	
	public static String getLibraryPath(){
		return System.getProperty("java.library.path");
	}
	
	public static String getTempDir(){
		return System.getProperty("java.io.tmpdir");
	}
	
	public static String getCompiler(){
		return System.getProperty("java.compiler");
	}
}
