package com.example.model;


public class User {
//	int _id;
//	String _username;
//	String _password;
	private String username;
	private String password;
	
	public User(String u, String p){
		username = u;
		password = p;
	}
	
	/*public User(){}
	
	public User(int id, String u, String p){
		this._id = id;
		this._username = u;
		this._password = p;
	}
	
	public User(String u, String p){
		this._username = u;
		this._password = p;
	}*/
	
	
	
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
