package com.example.activities;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.cs2340.R;
import com.example.model.Account;
import com.example.model.DatabaseHandler;
import com.example.model.SessionManager;
import com.example.model.Transaction;
import com.example.model.User;
import com.example.presenters.IDatabaseHandler;
import com.example.presenters.TransactionHistory;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class SpendCatReportActivity extends ListActivity {
	
	private SessionManager session;
	private ArrayAdapter<Transaction> adapter;
	private long[] dates;
	private IDatabaseHandler db;
	private long itemID;
	private Button back;
	private TextView total;
	private TextView title;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.viewexpensereport_view);
	
	    Intent intent = getIntent();
	    
	    db = new DatabaseHandler(this);
		session = new SessionManager(getApplicationContext());
		dates = intent.getLongArrayExtra("DATES");
		back = (Button)findViewById(R.id.bBack);
		total = (TextView)findViewById(R.id.tTotal);
		title = (TextView)findViewById(R.id.tTitle);	
	    
		final Context context = this;		
		List<Transaction> trans = db.getTransactionsByDates(dates[0], dates[1], session.getUserID());
		User user = db.getUser(session.getUserID());	
		
		title.setText("SCR for [" + user.getUsername() + "]");
		
		double totalWithdrawAmount = 0;

		for (int i = 0; i < trans.size(); i++) {			
			if ((trans.get(i).getDepositAmount() > 0) || (trans.get(i).getWithdrawAmount() == 0)) {
				trans.remove(i);
			}				
			if (i != trans.size()) {
				totalWithdrawAmount = totalWithdrawAmount + trans.get(i).getWithdrawAmount();
			}			
		}
		
//		for (int i = 0; i < trans.size(); i++) {
//			
//		}
//		for (Transaction transaction : trans) {
//			if (transaction.getDepositAmount() > 0) {
//				trans.remove(transaction);
//			} else {
//				totalWithdrawAmount = totalWithdrawAmount + transaction.getWithdrawAmount();
//			}
//		}
		
		NumberFormat us = NumberFormat.getCurrencyInstance();
		total.setText("Total Spending: " + us.format(totalWithdrawAmount));		
		
		back.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v){
				Intent intent = new Intent(SpendCatReportActivity.this, AccountMain.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT); 
				startActivity(intent);
			}
		});		
		
		adapter = new ArrayAdapter<Transaction>(context, android.R.layout.simple_list_item_1, trans);
		setListAdapter(adapter);
		
		//ListView myListView = getListView();
	    
		//if (expenses.length > 1) {
//			adapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, expenses);
//			setListAdapter(adapter);
		//} else {
			//myListView.getEmptyView();
			//Toast.makeText(context, "No accounts to display!", Toast.LENGTH_LONG).show();
		//}
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id1) {
//		itemID = l.getItemIdAtPosition(position) + 1;		
		//Account account = db.getAccount(itemID);
//		List<Account> accountList = db.getAllAccountsByID(session.getUserID());	
//		for (int i = 0; i < accountList.size(); i++) {
//			if (i == itemID - 1) {
//				curAccount = accountList.get(i);
//			}
//		}
//		Intent viewAccount = new Intent(SpendCatReportActivity.this, AccountViewActivity.class);
//		viewAccount.putExtra("itemID", itemID);
//		startActivity(viewAccount);
	}

	@Override
	protected void onResume() {
		super.onResume();
		setListAdapter(adapter);
	}
}
