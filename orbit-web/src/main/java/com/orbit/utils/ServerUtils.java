package com.orbit.utils;

public class ServerUtils {
	public String getOsName(){
		return System.getProperty("os.name");
	}
	
	public String getOsArth(){
		return System.getProperty("os.arch");
	}
	
	public String getOsVersion(){
		return System.getProperty("os.version");
	}
	
	public String getFileSeparator(){
		return System.getProperty("file.separator");
	}
	
	public String getPathSeparator(){
		return System.getProperty("path.separator");
	}
	
	public String getLineSeparator(){
		return System.getProperty("line.separator");
	}
	
	
}
