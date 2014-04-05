package com.example.activities;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.view.View;
import android.widget.Toast;

import com.example.cs2340.R;
import com.example.model.Account;
import com.example.model.DatabaseHandler;
import com.example.model.SessionManager;
import com.example.presenters.IDatabaseHandler;

/**
 * Activity for viewing any existing accounts belonging to the user.
 *  
 * @author Jesse Wu
 */
public class ViewAccountsActivity extends ListActivity {

	/**
	 *  @param session  the database agent that helps save user input
	 */
    private SessionManager session;
    /**
     *  @param adapter  the customizer for viewing the account list
     */
    private ArrayAdapter<Account> adapter;
    /**
     *  @param accountList  the list of all the user's accounts
     */
    private List<Account> accountList;
    /**
     * @param db  the database that is being interacted with
     */
    private IDatabaseHandler db;
    /**
     *  @param itemID  id of the selected account item
     */
    private long itemID;
    /**
     * @param back  button used to go back to previous menu
     */
    private Button back;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewaccounts_view);

        db = new DatabaseHandler(this);
        session = new SessionManager(getApplicationContext());
        back = (Button) findViewById(R.id.bBacktoAccMain);

        final Context context = this;
        long id = session.getUserID();

        accountList = db.getAllAccountsByID(id);
        Log.d("SessionManager", "ID: " + id);

        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ViewAccountsActivity.this,
                        AccountMain.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                ViewAccountsActivity.this.finish();
                startActivity(intent);
            }
        });
        // ListView myListView = getListView();

        if (!accountList.isEmpty()) {
            Log.d("AccountList", accountList.get(0).toString());
            adapter = new ArrayAdapter<Account>(context,
                    android.R.layout.simple_list_item_1, accountList);
            setListAdapter(adapter);
        } else {
            // myListView.getEmptyView();
            Toast.makeText(context, "No accounts to display!",
                    Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id1) {
        itemID = l.getItemIdAtPosition(position) + 1;
        // Account account = db.getAccount(itemID);
        // session.createAccountSession(account.getAccountName(),
        // session.getUserID(), account.getID());
        // Toast.makeText(getBaseContext(), "AccName: " +
        // account.getAccountName() + " UserID: " + session.getUserID(),
        // Toast.LENGTH_LONG).show();
        // Log.d("AccountID", "ID" + session.getAccountID());

        Intent viewAccount = new Intent(ViewAccountsActivity.this,
                AccountViewActivity.class);
        viewAccount.putExtra("itemID", itemID);
        startActivity(viewAccount);
    }

    @Override
    protected void onResume() {
        super.onResume();
        long id = session.getUserID();
        accountList = db.getAllAccountsByID(id);
        adapter = new ArrayAdapter<Account>(this,
                android.R.layout.simple_list_item_1, accountList);
        setListAdapter(adapter);
    }
}
