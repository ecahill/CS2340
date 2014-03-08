package com.example.activities;

import com.example.cs2340.R;
import com.example.model.Account;
import com.example.model.DatabaseHandler;
import com.example.model.SessionManager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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
		long accountID = session.getAccountID();
		Account curAccount = db.getAccount(accountID);
		session.createAccountSession(curAccount.getAccountName(), session.getUserID(), curAccount.getID());
		accName.setText(curAccount.toString());	
		
		makeTransaction.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v){
				Intent viewAccount = new Intent(AccountViewActivity.this, MakeTransactionActivity.class);
				startActivity(viewAccount);
			}
		});
	}
}
