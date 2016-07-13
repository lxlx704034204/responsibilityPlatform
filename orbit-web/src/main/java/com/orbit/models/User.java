package com.orbit.models;

public class User {
	private Integer id;
	private String name;
	private String password;
	
	public void setId(Integer id){
		this.id = id;
	}
	public Integer getId(){
		return this.id;
	}
	
	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return this.name;
	}
	
	public void setPassword(String pwd){
		this.password = pwd;
	}
	public String getPassword(){
		return this.password;
	}
}
