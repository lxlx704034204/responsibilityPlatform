package com.orbit.actions;

public class JsonResultError extends JsonResult {

	public JsonResultError(){
		super.setStatusCode(STATUSCODE_ERROR);
	}
	
	public JsonResultError(Object content){
		super.setStatusCode(STATUSCODE_ERROR);
		super.setContent(content);
	}
	
}
