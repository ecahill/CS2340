package com.example.presenters;

public class TransactionAdapter {	
	
	public static double deposit(double depositAmount, double accBalance) {
		return depositAmount + accBalance;
	}
	
	public static double withdraw(double withdrawAmount, double accBalance) {
		return accBalance - withdrawAmount;
	}
}
