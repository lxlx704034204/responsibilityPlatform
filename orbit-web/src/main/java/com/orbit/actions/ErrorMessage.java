package com.orbit.actions;

public class ErrorMessage {
	
	private Exception exception;
	
	public ErrorMessage(Exception e){
		this.exception = e;
	}
	
	public String getMessage(){
		return this.exception.getMessage();
	}
	
	public String getStack(){
		return this.exception.getCause().toString();
	}
}
