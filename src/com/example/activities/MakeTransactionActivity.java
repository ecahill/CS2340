package com.example.activities;

import com.example.cs2340.R;
import com.example.model.Account;
import com.example.model.DatabaseHandler;
import com.example.model.SessionManager;
import com.example.model.Transaction;
import com.example.presenters.AccountRules;
import com.example.presenters.IDatabaseHandler;

import android.app.Activity;
import android.os.Bundle;

import java.util.Date;
import java.text.NumberFormat;
import java.util.Calendar;
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
		final IDatabaseHandler db = new DatabaseHandler(context);
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
					if (transactionAmount.getText().toString().length() > 0 && getTransactionReason().length() > 0) {
						setTransactionType(transactionRadioButton.getText().toString());
						String transactionName = transactionReason.getText().toString();
						double amount = Double.parseDouble(transactionAmount.getText().toString());

						Account curAccount = null;
						for (int i = 0; i < accountList.size(); i++) {
							if (i == itemID - 1) {
								curAccount = accountList.get(i);
							}
						}
						double curBalance = curAccount.getBalance();
						Transaction trans = new Transaction(session.getUserID(), curAccount.getID(), transactionName, 0, 0, date.getTime());
						//Transaction trans = new Transaction(curAccount, transactionName, date.getTime());
						//Toast.makeText(context, "Date: " + date.getTime(), Toast.LENGTH_SHORT).show();
						//Toast.makeText(context, "Date: " + date, Toast.LENGTH_SHORT).show();
						NumberFormat us = NumberFormat.getCurrencyInstance();

						if (transactionType.equals(DEPOSIT)) {							
							if (amount < 0) {
								Toast.makeText(context, "Invalid deposit amount!", Toast.LENGTH_SHORT).show();
							} else {
								//trans.deposit(amount);
								//double currentBalance = curAccount.getBalance();
								if (amount > 0) {
									curAccount.setBalance(curBalance + amount);
									trans.setWithdrawAmount(amount);
								}
								Toast.makeText(context, "New balance: " + us.format(curAccount.getBalance()),
																		Toast.LENGTH_SHORT).show();
							}

						} else {
							if (amount > curBalance || amount < 0) {
								Toast.makeText(context, "Invalid withdraw amount!", Toast.LENGTH_SHORT).show();		
							} else {
								//double curBalance = account.getBalance();
								if (amount <= curBalance && amount > 0) {
									curAccount.setBalance(curBalance - amount);	
									trans.setWithdrawAmount(amount);
								}
								//trans.withdraw(amount);
								Toast.makeText(context, "New balance: " + us.format(curAccount.getBalance()),
																		Toast.LENGTH_SHORT).show();
							}							
						}
						db.addTransaction(trans);
						db.updateAccount(curAccount);
						Log.d("DatabaseHandler", "Transaction: [" + trans.getTransactionName() + "] added to account: " 
																		+ curAccount.getAccountName());
						Intent i = new Intent(MakeTransactionActivity.this, ViewAccountsActivity.class);
						startActivity(i);
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
