package com.orbit.actions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.struts2.ServletActionContext;

import com.orbit.configs.SystemConfig;


public class UploadAction extends ActionBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4117614733192689618L;
	
	private File file;
	private String fileFileName;
	private String fileContentType;
	
	public void setFile(File f){
		this.file = f;
	}
	public File getFile(){
		return this.file;
	}
	
	public void setFileFileName(String s){
		this.fileFileName = s;
	}
	public String getFileFileName(){
		return this.fileFileName;
	}
	
	public void setFileContentType(String s){
		this.fileContentType = s;
	}
	public String getFileContentType(){
		return this.fileContentType;
	}
	
	public String testindex(){
		return SUCCESS;
	}

	public String execute() throws Exception{
		String savedir = SystemConfig.getUploadDir();
		InputStream is = new FileInputStream(this.getFile());
		OutputStream os = new FileOutputStream(new File(savedir, this.getFileFileName()));
		byte[] buffer = new byte[1024000];
		int length = 0;
		while((length = is.read(buffer, 0, buffer.length)) != -1){
			os.write(buffer);
		}
		os.close();
		is.close();
		return SUCCESS;
	}

}
