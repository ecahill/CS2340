package com.example.activities;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.cs2340.R;
import com.example.model.Account;
import com.example.model.DatabaseHandler;
import com.example.model.SessionManager;
import com.example.presenters.IDatabaseHandler;

/**
 * This is the account's main screen.
 * 
 * @author Jesse Wu
 *
 */
public class AccountViewActivity extends Activity {

	/**
	 * @param session the current application session.
	 */
    private SessionManager session;
    
    /**
     * @para db the current database.
     */
    private IDatabaseHandler db;
    
    /**
     * @param itemID the position of the account relative to the accountList.
     */
    private long itemID;
    
    /**
     * @param accName the current account name.
     */
    private TextView accName;
    
    /**
     * @param curAccount the current account.
     */
    private Account curAccount;
    
    /**
     * @param ITEM_ID a string that holds the value "itemID".
     */
    private static final String ITEM_ID = "itemID";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_account_view);

        accName = (TextView) findViewById(R.id.accNameHeader);
        Button makeTransaction = (Button) findViewById(R.id.makeTransaction);

        session = new SessionManager(getApplicationContext());
        db = new DatabaseHandler(this);
        long userID = session.getUserID();
        List<Account> accountList = db.getAllAccountsByID(userID);

        // receives the clicked account position from ViewAccountsActivity
        itemID = getIntent().getLongExtra(ITEM_ID, 0);

        // gets the account to display from the account list
        for (int i = 0; i < accountList.size(); i++) {
            if (i == itemID - 1) {
                curAccount = accountList.get(i);
            }
        }

        // Log.d("SessionManager", "ID: "+ userID);
        // Log.d("AccountList", accountList.get(0).toString());
        // Log.d("2: AccountID", "ID" + accountID);

        session.createAccountSession(curAccount.getAccountName(),
                session.getUserID(), itemID);
        accName.setText(curAccount.toString());

        makeTransaction.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent transaction = new Intent(AccountViewActivity.this,
                        MakeTransactionActivity.class);
                transaction.putExtra(ITEM_ID, itemID);
                AccountViewActivity.this.finish();
                startActivity(transaction);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        // session.createAccountSession(curAccount.getAccountName(),
        // session.getUserID(), itemID);
        accName = (TextView) findViewById(R.id.accNameHeader);
        accName.setText(curAccount.toString());
    }
}
