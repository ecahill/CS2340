package com.example.model;

public class User {
	private String username;
	private String password;
	
	public User(String u, String p){
		username = u;
		password = p;
	}
	
	public String toString(){
		return username+" "+password;
	}
}
