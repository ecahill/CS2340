package com.example.model;

import java.sql.Date;

public class Transaction {
	private long id;
	private long userID;
	private long accountID;
	private String transactionName;
	private double depositAmount;
	private double withdrawAmount;
	private long date;
	private Account account;
	
	public Transaction(){
		
	}
	
	public Transaction(Account account, String transactionName, long date) {
		this.transactionName = transactionName;
		this.account = account;
		this.depositAmount = depositAmount;
		this.withdrawAmount = withdrawAmount;
		this.date = date;
	}
	
	public Transaction(long userID, long accountID, String transactionName, double depositAmount, 
			double withdrawAmount, long date){
		this.userID = userID;
		this.accountID = accountID;
		this.transactionName = transactionName;
		this.depositAmount = depositAmount;
		this.withdrawAmount  = withdrawAmount;
		this.date = date;
//		db = new DatabaseHandler(context);
//		this.curAccount = db.getAccount(accountID);
	}
	
	public Transaction(long id, long userID, long accountID, String transactionName, double depositAmount,
			double withdrawAmount, long date){
		this.id = id;
		this.userID = userID;
		this.accountID = accountID;
		this.transactionName = transactionName;
		this.depositAmount = depositAmount;
		this.withdrawAmount  = withdrawAmount;
		this.date = date;
	}
	
	public void withdraw(double withdrawAmount) {
		double curBalance = account.getBalance();
		if (withdrawAmount <= curBalance && withdrawAmount > 0) {
			account.setBalance(curBalance - withdrawAmount);			
		}
	}
	
	public void deposit(double depositAmount) {
		double curBalance = account.getBalance();
		if (depositAmount > 0) {
			account.setBalance(curBalance + depositAmount);
		}
	}
	
	public long getID(){
		return this.id;
	}
	
	public void setID(long id){
		this.id = id;
	}
	
	public long getUserID(){
		return this.userID;
	}
	
	public void setUserID(long uid){
		this.userID = uid;
	}
	
	public long getAccountID(){
		return this.accountID;
	}
	
	public void setAccountID(long aid){
		this.accountID = aid;
	}
	
	public String getTransactionName() {
		return transactionName;
	}
	
	public void setTransactionName(String transactionName) {
		this.transactionName = transactionName;
	}
	
	public double getDepositAmount() {
		return depositAmount;
	}
	
	public void setDepositAmount(double depositAmount) {
		this.depositAmount = depositAmount;
	}

	public double getWithdrawAmount() {
		return withdrawAmount;
	}

	public void setWithdrawAmount(double withdrawAmount) {
		this.withdrawAmount = withdrawAmount;
	}
	
	public long getDate(){
		return this.date;
	}
	
	public void setDate(long d){
		this.date = d;
	}	
}
