package com.example.model;

public class Transaction {
	
	private String transactionName;
	private double depositAmount;
	private double withdrawAmount;
	
	public Transaction() {
//		transactionName = name;
//		depositAmount = depAmount;
//		withdrawAmount = wdAmount;
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
	
	
}
