package com.example.presenters;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

import com.example.activities.MakeTransactionActivity;
import com.example.activities.ViewAccountsActivity;
import com.example.cs2340.R;
import com.example.model.Account;
import com.example.model.DatabaseHandler;
import com.example.model.IAccount;
import com.example.model.IDatabaseHandler;
import com.example.model.ISessionManager;
import com.example.model.SessionManager;
import com.example.views.AccountViewView;
import com.example.views.ClickListener;

public class AccountViewPresenter implements ClickListener {

	private AccountViewView view;
	private Activity activity;
	private IDatabaseHandler db;
	private ISessionManager session;
	private long itemID;
	private IAccount curAccount;
	
	public AccountViewPresenter(AccountViewView v, Activity act) {
		view = v;
		view.linkNotifyCallback(this);	
		activity = act;
		db = new DatabaseHandler(activity);
		session = new SessionManager(activity.getApplicationContext());	
		prepare();
	}
	
	private void prepare() {
		long userID = session.getUserID();
        List<Account> accountList = db.getAllAccountsByID(userID);

        // receives the clicked account position from ViewAccountsActivity
        itemID = activity.getIntent().getLongExtra("itemID", 0);

        // gets the account to display from the account list
        for (int i = 0; i < accountList.size(); i++) {
            if (i == itemID - 1) {
                curAccount = accountList.get(i);
            }
        }
        session.createAccountSession(curAccount.getAccountName(),
                session.getUserID(), itemID);
        view.setAccName(curAccount.toString());
	}
	
	@Override
	public void onClick(View v) {
		int backB = R.id.fromSingleAccToList;
		int makeTransactionB = R.id.makeTransaction;
		int curButton = v.getId();
		
		if (curButton == makeTransactionB) {
			Intent transaction = new Intent(activity, MakeTransactionActivity.class);
	        transaction.putExtra("itemID", itemID);
	        activity.finish();
	        activity.startActivity(transaction);	
		} else if (curButton == backB) {
			Intent transaction = new Intent(activity, ViewAccountsActivity.class);
	        transaction.putExtra("itemID", itemID);
	        activity.finish();
	        activity.startActivity(transaction);
		}		
	}

}
