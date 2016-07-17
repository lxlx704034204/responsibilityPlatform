package com.orbit.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class FileUtils {

	/**
	 * 获取src目录下文件的路径
	 * 
	 * @param path
	 *            文件相对src目录的路径，不需在路径前加路径分隔符
	 * 
	 * */
	public static String getFilePathInClasspath(String path) {
		URL urlPath = FileUtils.class.getResource("/");
		String absPath = urlPath.getPath();
		return absPath + path;
	}
	
	/**
	 * 判断文件/文件夹是否存在
	 * */
	public static boolean fileExisted(String path){
		File file = new File(path);
		return file.exists();
	}
	
	public static String readTextFile(String path, String encoding) throws IOException{
		if(StrUtils.isNullOrEmpty(encoding)){
			encoding = "UTF8";
		}
		StringBuilder text = new StringBuilder();
		FileInputStream inputStream = null;
		InputStreamReader reader = null;
		BufferedReader bfreader = null;
		try {
			File file = new File(path);
			if(file.exists() && file.isFile()){
				inputStream = new FileInputStream(file);
				reader = new InputStreamReader(inputStream, encoding);
				bfreader = new BufferedReader(reader);
				String line = null;
				while( (line=bfreader.readLine()) != null){
					text.append(line);
					text.append(System.lineSeparator());
				}
				bfreader.close();
			}else{
				return null;
			}
		} catch (IOException e) {
			throw e;
		} finally{
			if(inputStream != null){
				inputStream.close();
			}
			if(reader != null){
				reader.close();
			}
			if(bfreader != null){
				bfreader.close();
			}
		}
		return text.toString();
	}
}
