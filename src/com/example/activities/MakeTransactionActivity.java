package com.example.activities;

import com.example.cs2340.R;
import com.example.model.Account;
import com.example.model.DatabaseHandler;
import com.example.model.SessionManager;
import com.example.model.Transaction;
import com.example.presenters.AccountRules;

import android.app.Activity;
import android.os.Bundle;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MakeTransactionActivity extends Activity {
	
	private RadioGroup transactionRadioGroup;
	private RadioButton transactionRadioButton;
	private Button acceptButton;
	private String transactionType;
	private EditText transactionReason;
	private EditText transactionAmount;
	private SessionManager session;
	private static final String DEPOSIT = "Deposit";
	private static final String WITHDRAW = "Withdraw";
	private Date date;
	private List<Account> accountList;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.maketransaction_view);
	    
	    final Context context = this;
		final DatabaseHandler db = new DatabaseHandler(context);
		session = new SessionManager(getApplicationContext());
		final long userID = session.getUserID();
		final long accountID = session.getAccountID();
		accountList = db.getAllAccountsByID(userID);
	    
		transactionReason = (EditText) findViewById(R.id.transactionReasonEditText);
		transactionAmount = (EditText) findViewById(R.id.transactionAmountEditText);
		transactionRadioGroup = (RadioGroup) findViewById(R.id.transactionradiogroup);
		acceptButton = (Button) findViewById(R.id.acceptTransaction);
		
		acceptButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
			    // get the selected radio button from the group
				int selectedOption = transactionRadioGroup.getCheckedRadioButtonId();
				
				// find the radiobutton by the previously returned id
				transactionRadioButton = (RadioButton) findViewById(selectedOption);
				long itemID = getIntent().getLongExtra("itemID", 0);
				date = new Date();
			
				// Check if transaction amount is valid. So far, the only check
				// is: must be positive. Need to add check to see if withdrawal
				// amount exceeds balance. Access to balance available is not
				// available yet, but that check implementation will look something
				// like: if (getTransactionType().equals("Withdrawal") 
				//				&& getTransactionAmount() < getAccountBalance()) {}
					if (transactionAmount != null && !getTransactionReason().equals("")) {
						setTransactionType(transactionRadioButton.getText().toString());
						String transactionName = transactionReason.getText().toString();
						double amount = Double.parseDouble(transactionAmount.getText().toString());
						
						//Toast.makeText(context, "itemID: " + itemID, Toast.LENGTH_SHORT).show();
						Account curAccount = null;
						for (int i = 0; i < accountList.size(); i++) {
							if (i == itemID - 1) {
								curAccount = accountList.get(i);
							}
						}						
						double curBalance = curAccount.getBalance();
						
						if (transactionType.equals(DEPOSIT)) {
							Transaction depositTrans = new Transaction(userID, itemID, transactionName, 
										amount, amount, date.getTime());
							depositTrans.setWithdrawAmount(0);							
							if (amount > 0) {
								curAccount.setBalance(curBalance + amount);
							}							
							Toast.makeText(context, "New balance: $" + curAccount.getBalance(), Toast.LENGTH_SHORT).show();
						} else {
							Transaction withdrawTrans = new Transaction(userID, accountID, transactionName, 
										amount, amount, date.getTime());
							withdrawTrans.setDepositAmount(0);
							if (amount <= curBalance && amount > 0) {
								curAccount.setBalance(curBalance - amount);			
							}
							Toast.makeText(context, "New balance: $" + curAccount.getBalance(), Toast.LENGTH_SHORT).show();
						}
						
						// ERROR OCCURS AFTER THIS CALL
						db.updateAccount(curAccount);
					} else {
						Toast.makeText(context, "Transaction Failed.", Toast.LENGTH_LONG).show();
					}		
				}
			});
		}
	
	public void setTransactionType(String type) {
		transactionType = type;
	}
	
	public String getTransactionType() {
		return transactionType;
	}
	
	public String getTransactionReason() {
		return transactionReason.getText().toString();
	}
	
	
	public double getTransactionAmount() {
		String transactionAmountString = transactionAmount.getText().toString();
		return Double.parseDouble(transactionAmountString);
	}
}
