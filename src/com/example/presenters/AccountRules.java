package com.example.presenters;

import java.util.List;

import com.example.model.Account;
import com.example.model.DatabaseHandler;

public class AccountRules {
	DatabaseHandler d;
	
	
	public AccountRules(DatabaseHandler d){
		this.d = d;
	}
	
	public boolean checkAccountName(long userID, String name){
		List<Account> l = d.getAllAccountsByID(userID);
		for (Account a: l){
			if (name.equals(a.getAccountName())){
				return false;
			}
		}
		return true;
	}

}
