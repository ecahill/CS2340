package com.example.model;
import java.text.NumberFormat;

public class Account {
	private long id; 
	private String account_name;
	private double balance;
	private long userID;
	private double interestRate;
	
	public Account(){
		
	}
	
	public Account(String account_name, double balance, long userID, double interestRate){
		this.account_name = account_name;
		this.balance = balance;
		this.userID = userID;
		this.interestRate = interestRate;
	}
	
	public Account(long id, String account_name, double balance, long userID, double interestRate){
		this.id = id;
		this.account_name = account_name;
		this.balance = balance;
		this.userID = userID;
		this.interestRate = interestRate;
	}
	
	public void setID(long id){
		this.id = id;
	}
	
	public void setAccountName(String name){
		this.account_name = name;
	}
	
	public void setBalance(double bal){
		this.balance = bal;
	}
	
	public void setUserID(long id){
		this.userID = id;
	}
	
	public void setInterestRate(double rate){
		this.interestRate = rate;
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
	
	public double getInterestRate(){
		return this.interestRate;
	}
	
	public String toString(){
		NumberFormat us = NumberFormat.getCurrencyInstance();
		return this.account_name+ " ["+us.format(balance)+"] "+interestRate;
	}
}
