package com.example.activities;

import java.util.List;

import com.example.cs2340.R;
import com.example.model.Account;
import com.example.model.DatabaseHandler;
import com.example.model.SessionManager;
import com.example.presenters.IDatabaseHandler;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class AccountViewActivity extends Activity {

    private SessionManager session;
    private IDatabaseHandler db;
    private long itemID;
    private TextView accName;
    private Account curAccount;

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
        itemID = getIntent().getLongExtra("itemID", 0);

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
                transaction.putExtra("itemID", itemID);
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
