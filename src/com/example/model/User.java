package com.example.model;


public class User {
	int _id;
	String _username;
	String _password;

	
	public User(){}
	
	public User(int id, String u, String p){
		this._id = id;
		this._username = u;
		this._password = p;
	}
	
	public User(String u, String p){
		this._username = u;
		this._password = p;
	}
	
	public int getID(){
		return this._id;
	}
	
	public void setID(int id){
		this._id = id;
	}
	
	public String getUsername(){
		return this._username;
	}
	
	public void setUsername(String u){
		this._username = u;
	}
	
	public String getPassword(){
		return this._password;
	}
	
	public void setPassword(String p){
		this._password = p;
	}
	
}
