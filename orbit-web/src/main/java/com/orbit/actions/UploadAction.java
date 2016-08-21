package com.orbit.actions;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.http.HttpRequest;

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
		HttpServletRequest request = this.getRequest();
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
		
		String type = request.getParameter("type");
		if(type.equals("uploadTaskAttachment")){
			String callback = request.getParameter("callback");
			String taskid_str = request.getParameter("taskid");
			
			Long taskid = Long.parseLong(taskid_str);
			this.saveAttachment(taskid, this.getFile());
			
			request.setAttribute("type", type);
			request.setAttribute("callback", callback);
			request.setAttribute("taskid", taskid);
			request.setAttribute("attachmentName", this.getFileFileName());
			request.setAttribute("attachmentId", "");//TODO:
		}
		
		
		return SUCCESS;
	}
	
	private Integer saveAttachment(Long taskid, File file) throws Exception{
		byte[] bytes = this.getFileByteArray(file);
		return 1;
	}
	
	private byte[] getFileByteArray(File file) throws Exception{
		InputStream is = null;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			is = new FileInputStream(file);// pathStr 文件路径
			byte[] b = new byte[1024];
			int n;
			while ((n = is.read(b)) != -1) {
				out.write(b, 0, n);
			}// end while
		} catch (Exception e) {
			throw e;
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (Exception e) {
				}
			}
		}
       return out.toByteArray();
	}

}
