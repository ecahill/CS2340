package com.example.model;

public class User {
	private String username;
	private String password;
	
	public User(String u, String p){
		username = u;
		password = p;
	}
	
	public String toString(){
		return "Username: " + username;
	}
	
	public String getUsername(){
		return username;
	}
	
	public String getPassword(){
		return password;
	}
}
