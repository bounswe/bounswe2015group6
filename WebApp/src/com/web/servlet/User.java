package com.web.servlet;

public class User {
	public int id;
	public String name;
	public String pass;
	
	public User(){
		id = 0;
		name = "";
		pass = "";
	}
	public User(int id, String name, String pass){
		this.id = id;
		this.name = name;
		this.pass = pass;
	}

}
