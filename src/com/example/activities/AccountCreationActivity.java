package com.example.activities;

import com.example.cs2340.R;

import android.app.Activity;
import android.os.Bundle;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AccountCreationActivity extends Activity {
	EditText accName;
	EditText accBalance;
	EditText monthlyInterestRate;
	Button acceptButton;
	Button declineButton;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.accountcreation_view);
	 
	    accName = (EditText)findViewById(R.id.AccNameField);
        accBalance = (EditText)findViewById(R.id.AccBalanceField);
        monthlyInterestRate = (EditText)findViewById(R.id.MonthlyInterestField);
        acceptButton = (Button)findViewById(R.id.acceptButton);
        declineButton = (Button)findViewById(R.id.declineButton);
        
        acceptButton.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v){
				//new account is created
				
				//Intent i = new Intent(AccountCreationActivity.this, SomeClass.class);
				//startActivity(i);
			}
		});
        declineButton.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v){
				//account is not created
				
				//for now, go back to previous page
				Intent i = new Intent(AccountCreationActivity.this, AccountMain.class);
				startActivity(i);
			}
		});
	}
        
    public String getAccountName() {
		return accName.getText().toString();
	}
	
	public double getAccountBalance() {
		String accBalanceString = accBalance.getText().toString();
		return Double.parseDouble(accBalanceString);
	}
	
	public double getMonthlyInterestRate() {
		String monthlyInterestRateString = monthlyInterestRate.getText().toString();
		return Double.parseDouble(monthlyInterestRateString);
	}
  
	
	

}
