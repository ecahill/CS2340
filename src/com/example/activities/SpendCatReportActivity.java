package com.example.activities;

import com.example.cs2340.R;
import com.example.model.Account;
import com.example.model.DatabaseHandler;
import com.example.model.SessionManager;
import com.example.presenters.IDatabaseHandler;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class SpendCatReportActivity extends ListActivity{
	
	private SessionManager session;
	private ArrayAdapter<String> adapter;
	private String[] expenses;
	private IDatabaseHandler db;
	private long itemID;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.viewexpensereport_view);
	
		final Context context = this; 
		db = new DatabaseHandler(this);
		session = new SessionManager(getApplicationContext());
		Intent intent = getIntent();
		expenses = intent.getStringArrayExtra("EXPENSES");
		
		//ListView myListView = getListView();
	    
		if (expenses.length != 0) {
			adapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, expenses);
			setListAdapter(adapter);
		} else {
			//myListView.getEmptyView();
			Toast.makeText(context, "No accounts to display!", Toast.LENGTH_LONG).show();
		}
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id1) {
		itemID = l.getItemIdAtPosition(position) + 1;
		db.getAccount(itemID);
		Intent viewAccount = new Intent(SpendCatReportActivity.this, AccountViewActivity.class);
		viewAccount.putExtra("itemID", itemID);
		startActivity(viewAccount);
	}

	@Override
	protected void onResume() {
		super.onResume();
		setListAdapter(adapter);
	}
}
