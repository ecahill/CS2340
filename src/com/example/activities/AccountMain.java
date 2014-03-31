package com.example.activities;

import com.example.cs2340.R;
import com.example.cs2340.R.layout;
import com.example.cs2340.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AccountMain extends Activity {
	
	private Button viewAcc;
	private Button createAcc;
	private Button viewSpendCatRep;
	private Button logout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_account_main);
		
		viewAcc = (Button)this.findViewById(R.id.bViewAccount);
		createAcc = (Button)this.findViewById(R.id.bCreateAccount);
		viewSpendCatRep = (Button)this.findViewById(R.id.bViewSpendingCategoryReport);
		logout = (Button)this.findViewById(R.id.bLogout);
		
		viewAcc.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v) {				
				Intent i = new Intent(AccountMain.this, ViewAccountsActivity.class);
				startActivity(i);
			}
		});
		
		createAcc.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v){
				Intent i = new Intent(AccountMain.this, AccountCreationActivity.class);
				startActivity(i);
			}
		});
		
		viewSpendCatRep.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v){
				Intent i = new Intent(AccountMain.this, GetDatesActivity.class);
				startActivity(i);
				//Intent report = new Intent(AccountMain.this, SpendCatReportActivity.class);
				//startActivity(report);
				//Toast.makeText(AccountMain.this, "View report", Toast.LENGTH_SHORT).show();
			}
		});
		
		logout.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v) {				
				Intent i = new Intent(AccountMain.this, LoginActivity.class);
				AccountMain.this.finish();
				startActivity(i);
			}
		});
	}
}
