package com.example.model;

public class Account {
	long id; 
	String account_name;
	double balance;
	long userID;
	
	public Account(){
		
	}
	
	public Account(String account_name, double balance, long userID){
		this.account_name = account_name;
		this.balance = balance;
		this.userID = userID;
	}
	
	public Account(long id, String account_name, int balance, long userID){
		this.id = id;
		this.account_name = account_name;
		this.balance = balance;
		this.userID = userID;
	}
	
	public void setID(long id){
		this.id = id;
	}
	
	public void setAccountName(String name){
		this.account_name = name;
	}
	
	public void setBalance(int bal){
		this.balance = bal;
	}
	
	public void setUserID(long id){
		this.userID = id;
	}
	
	public long getID(){
		return this.id;
	}
	
	public String getAccountName(){
		return this.account_name;
	}
	
	public double getBalance(){
		return this.balance;
	}
	
	public long getUserID(){
		return this.userID;
	}
	
	public String toString(){
		return this.account_name+ " [$"+this.balance+"]";
	}
}
