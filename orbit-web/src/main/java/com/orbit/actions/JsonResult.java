package com.orbit.actions;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

public class JsonResult {
	
	/**
	 * OK(200)
	 * */
	public static Integer STATUSCODE_OK = 200;
	/**
	 * Error(500)
	 * */
	public static Integer STATUSCODE_ERROR = 500;
	
	// 状态码：200(OK)、500(Error)
	private Integer statusCode = 500;
	// 内容
	private Object content;
	
	public JsonResult(){}
	
	public JsonResult(Integer code,Object content){
		this.setStatusCode(code);
		this.setContent(content);
	}
	
	
	public void setStatusCode(Integer code){
		this.statusCode = code;
	}
	public Integer getStatusCode(){
		return this.statusCode;
	}

	public void setContent(Object content){
		this.content = content;
	}
	public Object getContent(){
		return this.content;
	}
	
	public String toString(){
		return JSONObject.fromObject(this).toString();
	}
	
	public String toString(JsonConfig config)
	{
		return JSONObject.fromObject(this,config).toString();
	}
}

