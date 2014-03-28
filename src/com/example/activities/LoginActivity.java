package com.example.activities;

import com.example.cs2340.R;
import com.example.cs2340.R.id;
import com.example.cs2340.R.layout;
import com.example.cs2340.R.menu;

import com.example.model.SessionManager;
import com.example.model.User;
import com.example.views.ClickListener;
import com.example.model.DatabaseHandler;
import com.example.presenters.IDatabaseHandler;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;
import android.view.View.OnClickListener;

import android.util.Log;

public class LoginActivity extends Activity {
	
    private EditText nameField;
    private EditText password;
    private EditText resultField;
    private Button goButton;
    private SessionManager session;
    
    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_view);
		final Context context = this;
		final IDatabaseHandler db = new DatabaseHandler(context);
		
		session = new SessionManager(getApplicationContext()); 			
		nameField = (EditText) findViewById(R.id.AccountNameField);
		password = (EditText) findViewById(R.id.AcctBalanceField);
		goButton = (Button) this.findViewById(R.id.acceptButton);
		
		goButton.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v){
				if (getName().length()>0 && getPassword().length()>0){
					User u = db.getUserByUP(getName(), getPassword());
					if (u!=null){
						if(!u.getUsername().equals("admin")) {
							session.createLoginSession(u.getUsername(), u.getID());
							Intent accMain = new Intent(LoginActivity.this, AccountMain.class);
							startActivity(accMain);							
						}
						setContentView(R.layout.loginsuccess_view);										
					} else {
						Toast.makeText(context, "Login Failed.", Toast.LENGTH_LONG).show();
					}
				}
			}
		}); 
	}	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public String getName() {
		return nameField.getText().toString().trim();
	}
	
	public String getPassword(){
		return password.getText().toString().trim();
	}	
}
