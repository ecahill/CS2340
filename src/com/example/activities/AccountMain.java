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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_account_main);
		
		Button viewAcc = (Button)this.findViewById(R.id.bViewAccount);
		Button createAcc = (Button)this.findViewById(R.id.bCreateAccount);
		Button viewSpendCatRep = (Button)this.findViewById(R.id.bViewSpendingCategoryReport);
		
		viewAcc.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v){				
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
				Intent i = new Intent(AccountMain.this, GetDates.class);
				Intent report = new Intent(AccountMain.this, SpendCatReportActivity.class);
				startActivity(i);
				//startActivity(report);
				//Toast.makeText(AccountMain.this, "View report", Toast.LENGTH_SHORT).show();
			}
		});
	}
}
