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

public class AccountMain extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_account_main);
		
		Button viewAcc = (Button)this.findViewById(R.id.bViewAccount);
		Button createAcc = (Button)this.findViewById(R.id.bCreateAccount);
		
		viewAcc.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v){
				//display accounts
				
				//Intent i = new Intent(AccountMain.this, SomeClass.class);
				//startActivity(i);
			}
		});
		
		createAcc.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v){
				//display create account activity
				
				//Intent i = new Intent(AccountMain.this, SomeClass.class);
				//startActivity(i);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.account_main, menu);
		return true;
	}

}
