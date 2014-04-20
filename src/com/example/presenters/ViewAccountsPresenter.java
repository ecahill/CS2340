package com.example.presenters;

import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.activities.AccountMainActivity;
import com.example.activities.AccountViewActivity;
import com.example.model.Account;
import com.example.model.DatabaseHandler;
import com.example.model.IDatabaseHandler;
import com.example.model.SessionManager;
import com.example.views.ClickListener;
import com.example.views.ViewAccountsView;

public class ViewAccountsPresenter implements ClickListener {

	private ViewAccountsView view;
	private ListActivity activity;
	private IDatabaseHandler db;
	private SessionManager session;
	private long itemID;
	
	public ViewAccountsPresenter(ViewAccountsView v, ListActivity act) {
		view = v;
		view.linkNotifyCallback(this);	
		activity = act;
		db = new DatabaseHandler(activity);
		session = new SessionManager(activity.getApplicationContext());	
		createAccountList();
	}
	
	private void createAccountList() {
		long id = session.getUserID();
		List<Account> accountList = db.getAllAccountsByID(id);
	    Log.d("SessionManager", "ID: " + id);
	    
	    if (accountList.isEmpty()) {           
            Toast.makeText(activity, "No accounts to display!",
                    Toast.LENGTH_LONG).show();
        } else {
            Log.d("AccountList", accountList.get(0).toString());
            ArrayAdapter<Account> adapter = new ArrayAdapter<Account>(activity,
                    android.R.layout.simple_list_item_1, accountList);
            activity.setListAdapter(adapter);
            ListView listView = activity.getListView();
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            	
				@Override
				public void onItemClick(AdapterView<?> l, View v,
														int position, long arg3) {
					itemID = l.getItemIdAtPosition(position) + 1;
					Intent viewAccount = new Intent(activity,
			                AccountViewActivity.class);
			        viewAccount.putExtra("itemID", itemID);
			        activity.finish();
			        activity.startActivity(viewAccount);
				}            	
			});
        }
	}
	
	@Override
	public void onClick(View v) {
		Intent intent = new Intent(activity, AccountMainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        activity.finish();
        activity.startActivity(intent);
	}	
}
