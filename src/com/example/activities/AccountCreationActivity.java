package com.example.activities;

import com.example.cs2340.R;
import com.example.model.Account;
import com.example.model.DatabaseHandler;
import com.example.model.SessionManager;
import com.example.presenters.AccountRules;
import com.example.presenters.IDatabaseHandler;

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
//	Button declineButton;
	SessionManager session;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.accountcreation_view);
		final Context context = this;
		final IDatabaseHandler db = new DatabaseHandler(context);
		final AccountRules rules = new AccountRules(db);
		session = new SessionManager(getApplicationContext());
		
	    accName = (EditText)findViewById(R.id.AccNameField);
        accBalance = (EditText)findViewById(R.id.AccBalanceField);
        monthlyInterestRate = (EditText)findViewById(R.id.MonthlyInterestField);
        acceptButton = (Button)findViewById(R.id.acceptButton);
//      declineButton = (Button)findViewById(R.id.declineButton);
        session.checkLogin();
        acceptButton.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v){
				if (accName.getText().toString().length() > 0 && accBalance.getText().toString().length() > 0 && 
							monthlyInterestRate.getText().toString().length() > 0){
					long userID = session.getUserID();
					if (rules.checkAccountName(userID, accName.getText().toString())){
						Account a = new Account(accName.getText().toString(), 
											Double.parseDouble(accBalance.getText().toString()),
											userID, Double.parseDouble(monthlyInterestRate.getText().toString()));
						long id = db.createAccount(a);
						Log.d("Account Balance", "Balance: " + a.getBalance());
						Log.d("Account Balance", "Balance from db: " + db.getAccount(id).getBalance());
						a.setID(id);
					
						Intent i = new Intent(AccountCreationActivity.this, AccountMain.class);
						startActivity(i);
					} else {
						Toast.makeText(context, "Account name already exists!", Toast.LENGTH_LONG).show();
					}
				} else {
					Toast.makeText(context, "Account creation unsuccessful!", Toast.LENGTH_LONG).show();
				}
			}
		});
//        declineButton.setOnClickListener(new View.OnClickListener(){
//			public void onClick(View v){
//				//account is not created
//				
//				//for now, go back to previous page
//				Intent i = new Intent(AccountCreationActivity.this, AccountMain.class);
//				startActivity(i);
//			}
//		});
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
