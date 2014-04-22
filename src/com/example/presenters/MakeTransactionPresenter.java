package com.example.presenters;

import java.text.NumberFormat;
import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.activities.ViewAccountsActivity;
import com.example.cs2340.R;
import com.example.model.Account;
import com.example.model.DatabaseHandler;
import com.example.model.IDatabaseHandler;
import com.example.model.ISessionManager;
import com.example.model.SessionManager;
import com.example.model.Transaction;
import com.example.views.ClickListener;
import com.example.views.MakeTransactionView;

public class MakeTransactionPresenter implements ClickListener {

	private MakeTransactionView view;
	private Activity activity;
	private IDatabaseHandler db;
	private ISessionManager session;
	/**
     * @param DEPOSIT a string used for comparison.
     */
    private static final String DEPOSIT = "Deposit";
	

	public MakeTransactionPresenter(MakeTransactionView v, Activity act) {
		view = v;
		view.linkNotifyCallback(this);	
		activity = act;
		db = new DatabaseHandler(activity);
		session = new SessionManager(activity.getApplicationContext());			
	}
	
	private void launchViewAccountsActivity(Account curAccount, Transaction trans) {
		db.updateAccount(curAccount);
        Log.d("DatabaseHandler",
                "Transaction: [" + trans.getTransactionName()
                        + "] added to account: "
                        + curAccount.getAccountName());
        Intent i = new Intent(activity, ViewAccountsActivity.class);
       	activity.finish();
        activity.startActivity(i);
	}
	
	@Override
	public void onClick(View v) {
		int makeTransactionB = R.id.acceptTransaction;
		int backB = R.id.fromMakeTransToAccView;
		int curButton = v.getId();
		
		if (curButton == makeTransactionB) {
			String transactionReason = view.getTransactionReason();
			session = new SessionManager(activity.getApplicationContext());
	        long userID = session.getUserID();
			List<Account> accountList = db.getAllAccountsByID(userID);
			
	        long itemID = activity.getIntent().getLongExtra("itemID", 0);
	        Date date = new Date();
	        if (view.verifyTransactionAmount()
	                		&& transactionReason.length() > 0) {
	            String reason = transactionReason;
	            double amount = view.getTransactionAmount();
	            Account curAccount = null;
	            for (int i = 0; i < accountList.size(); i++) {
	                if (i == itemID - 1) {
	                    curAccount = accountList.get(i);
	                }
	            }
	            double curBalance = curAccount.getBalance();
	            Transaction trans = new Transaction(session.getUserID(),
	                    curAccount.getID(), reason, 0, 0, date
	                            .getTime());
	            trans.setTransactionType(view.getTransactionType());
	            TransactionAdapter transAdapter = new TransactionAdapter();
	            NumberFormat us = NumberFormat.getCurrencyInstance();

	            if (trans.getTransactionType().equals(DEPOSIT)) {
	                if (amount <= 0) {
	                    Toast.makeText(activity, "Invalid deposit amount!",
	                            Toast.LENGTH_SHORT).show();
	                } else {
	                    transAdapter.setFinalDepositAmount(amount,
	                            curBalance);
	                    curAccount.setBalance(transAdapter
	                            .getAfterDeposit());
	                    trans.setDepositAmount(amount);
	                    db.addTransaction(trans);
	                    Toast.makeText(activity,"New balance:  "
	                                    + us.format(curAccount.getBalance()),
	                                    		Toast.LENGTH_SHORT).show();
	                }
	            } else {
	                if (amount > curBalance || amount < 0) {
	                    Toast.makeText(activity, "Invalid withdraw amount!",
	                            Toast.LENGTH_SHORT).show();
	                } else {
	                    transAdapter.setFinalWithdrawAmount(amount, curBalance);
	                    curAccount.setBalance(transAdapter.getAfterWithdraw());
	                    trans.setWithdrawAmount(amount);
	                    db.addTransaction(trans);
	                    Toast.makeText(activity, "New balance: "
	                                    + us.format(curAccount.getBalance()),
	                                    			Toast.LENGTH_SHORT).show();
	                }
	            }
	            launchViewAccountsActivity(curAccount, trans);
	            activity.overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
	        } else {
	            Toast.makeText(activity, "Transaction Failed.",
	                    Toast.LENGTH_LONG).show();
	        }		
		} else if (curButton == backB) {
			launchAccViewActivity();
		}
	}
	
	private void launchAccViewActivity() {
		Intent i = new Intent(activity, ViewAccountsActivity.class);
		activity.finish();
		activity.startActivity(i);
		activity.overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
	}
}
