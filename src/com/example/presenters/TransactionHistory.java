package com.example.presenters;

import java.util.List;

import com.example.model.Transaction;

public class TransactionHistory {
	private IDatabaseHandler db;
	
	public TransactionHistory(IDatabaseHandler db){
		this.db = db;
	}
	
	public List<Transaction> getTransactionsByID(long id){
		return db.getAllTransactionsByID(id);
	}
	
	public long addTransaction(Transaction t){
		return db.addTransaction(t);
	}

}
