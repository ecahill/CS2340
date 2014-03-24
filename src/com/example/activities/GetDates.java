package com.example.activities;

import java.sql.Date;
import java.util.ArrayList;


import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cs2340.R;
import com.example.model.Account;
import com.example.model.DatabaseHandler;
import com.example.model.SessionManager;
import com.example.model.Transaction;
import com.example.presenters.IDatabaseHandler;
import com.example.presenters.TransactionHistory;

public class GetDates extends Activity {
	SessionManager session;
	EditText startDate;
	EditText endDate;
	Button makeReportButton;
	List<Account> accounts;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.genspendcatrepdates_view);
	    
		final Context context = this;
		final IDatabaseHandler db = new DatabaseHandler(context);
		session = new SessionManager(getApplicationContext());
		
		accounts = db.getAllAccountsByID(session.getUserID());
		//Toast.makeText(GetDates.this, "accounts: " + accounts.size(), Toast.LENGTH_SHORT).show();		
		
		
		final long id = session.getAccountID();
		startDate = (EditText)findViewById(R.id.sDate);
		endDate = (EditText)findViewById(R.id.eDate);
		makeReportButton = (Button)findViewById(R.id.makeReport);
		makeReportButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v){
				if (startDate != null && endDate != null) {
					long accID = 0;
					TransactionHistory transactionsHist = null;
					List<Transaction> transactions = null;
					List<Transaction> validTrans = new ArrayList<Transaction>();
					for (Account a : accounts) {
						accID = a.getID();
						transactionsHist = db.getAllTransactionsByID(accID);
						//Toast.makeText(GetDates.this, "start date: " + , Toast.LENGTH_SHORT).show();
						transactions = transactionsHist.getWithdrawals();
						
						//Toast.makeText(GetDates.this, "start date: " + Date.parse(startDate.toString()), Toast.LENGTH_SHORT).show();
						for (Transaction t : transactions) {
							if (t.getDate() >= Date.parse(startDate.toString()) && t.getDate() <= Date.parse(endDate.toString())) {
								validTrans.add(t);
								Toast.makeText(GetDates.this, "Expenses: " + validTrans.size(), Toast.LENGTH_SHORT).show();
							}
						}
					}
					//TransactionHistory transactionsHist = db.getAllTransactionsByID(id);
					
					
					Toast.makeText(GetDates.this, "id: " + id, Toast.LENGTH_SHORT).show();
					Toast.makeText(GetDates.this, "Before: " + transactions.size(), Toast.LENGTH_SHORT).show();
//					for (Transaction t : transactions) {
//						if (t.getDate() >= Date.parse(startDate.toString()) && t.getDate() <= Date.parse(endDate.toString())) {
//							validTrans.add(t);
//							Toast.makeText(GetDates.this, "Expenses: " + validTrans.size(), Toast.LENGTH_SHORT).show();
//						}
//					}
					String[] expenses = new String[validTrans.size() + 1];
					int index = 0;
					double total = 0;
					for (Transaction t : validTrans) {
						expenses[index++] = t.withdrawToString();
						total =+ t.getWithdrawAmount();
					}
					expenses[index] = ("Total: " + total);
					Intent i = new Intent(GetDates.this, SpendCatReportActivity.class);
					i.putExtra("EXPENSES", expenses);
					startActivity(i);
				}
			}
		});
	}
}