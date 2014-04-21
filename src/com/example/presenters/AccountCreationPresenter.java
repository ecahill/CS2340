package com.example.presenters;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.activities.AccountMainActivity;
import com.example.cs2340.R;
import com.example.model.Account;
import com.example.model.DatabaseHandler;
import com.example.model.IDatabaseHandler;
import com.example.model.ISessionManager;
import com.example.model.SessionManager;
import com.example.views.AccountCreationView;
import com.example.views.ClickListener;

public class AccountCreationPresenter implements ClickListener {

	private Activity activity;
	private AccountCreationView view;
	private IDatabaseHandler db;
	private ISessionManager session;
	
	public AccountCreationPresenter(AccountCreationView aView, Activity act) {
		view = aView;
		view.linkNotifyCallback(this);
		activity = act;
		db = new DatabaseHandler(activity);
		session = new SessionManager(activity.getApplicationContext());
	}
	
	@Override
	public void onClick(View v) {
		int acceptButton = R.id.acceptButton;
		int backButton = R.id.fromAccCreationtoAccMain;
		int curButton = v.getId();	
		
		if (curButton == acceptButton) {
			if (view.getAccountName().length() > 0
                    && view.validAccountBalance()
                    	&& view.validInterestRate()) {
				String accName = view.getAccountName();
				Double accBalance = view.getAccountBalance();
				Double monthlyInterestRate = view.getMonthlyInterestRate();
                long userID = session.getUserID();
                if (checkAccountName(userID, accName)) {
                    Account a = new Account(accName, accBalance, userID, monthlyInterestRate);
                    long id = db.createAccount(a);
                    Log.d("Account Balance", "Balance: " + a.getBalance());
                    Log.d("Account", "Balance from db: "
                            + db.getAccount(id).getBalance());
                    				a.setID(id);
                    Toast.makeText(activity, "Account: [" + accName + "] created!",
                                            Toast.LENGTH_LONG).show();
                    launchAccountMainActivity();
                } else {
                    Toast.makeText(activity, "Account name already exists!",
                            Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(activity, "Account creation unsuccessful!",
                        Toast.LENGTH_LONG).show();
            }
		} else if (curButton == backButton) {
			launchAccountMainActivity();
		}
	}
	
	private boolean checkAccountName(long userID, String name) {
        List<Account> l = db.getAllAccountsByID(userID);
        for (Account a : l) {
            if (name.equals(a.getAccountName())) {
                return false;
            }
        }
        return true;
    }
	
	private void launchAccountMainActivity() {
		Intent accMain = new Intent(activity, AccountMainActivity.class);
		activity.finish();
        activity.startActivity(accMain);
	}
	
}
