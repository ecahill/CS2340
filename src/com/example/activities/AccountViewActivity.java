package com.example.activities;

import java.util.List;

import com.example.cs2340.R;
import com.example.model.Account;
import com.example.model.DatabaseHandler;
import com.example.model.SessionManager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class AccountViewActivity extends Activity {
	
	private SessionManager session;
	private DatabaseHandler db;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.single_account_view);
		
		TextView accName = (TextView) findViewById(R.id.accNameHeader);
		Button makeTransaction = (Button) findViewById(R.id.makeTransaction);
		
		session = new SessionManager(getApplicationContext());
		db = new DatabaseHandler(this);
		long userID = session.getUserID();
		List<Account> accountList = db.getAllAccountsByID(userID);	
		
		// receives the clicked account position from ViewAccountsActivity
		long itemID = getIntent().getLongExtra("itemID", 0);
		
		// gets the account to display from the account list
		Account curAccount = null;
		for (int i = 0; i < accountList.size(); i++) {
			if (i == itemID - 1) {
				curAccount = accountList.get(i);
			}
		}
		
//		Log.d("SessionManager", "ID: "+ userID);
//		Log.d("AccountList", accountList.get(0).toString());
//		Log.d("2: AccountID", "ID" + accountID);
		
		session.createAccountSession(curAccount.getAccountName(), session.getUserID(), itemID);
		accName.setText(curAccount.toString());	
		
		makeTransaction.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v){
				Intent viewAccount = new Intent(AccountViewActivity.this, MakeTransactionActivity.class);
				startActivity(viewAccount);
			}
		});
	}
}
