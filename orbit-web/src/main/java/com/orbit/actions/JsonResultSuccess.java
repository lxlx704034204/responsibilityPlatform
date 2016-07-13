package com.orbit.actions;

public class JsonResultSuccess extends JsonResult {
	
	public JsonResultSuccess(){
		super.setStatusCode(STATUSCODE_OK);
	}
	
	public JsonResultSuccess(Object content){
		super.setStatusCode(STATUSCODE_OK);
		super.setContent(content);
	}
	
}
