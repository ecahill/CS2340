package com.example.activities;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.cs2340.R;
import com.example.model.Account;
import com.example.model.DatabaseHandler;
import com.example.model.SessionManager;

public class ViewAccountsActivity extends ListActivity{
	SessionManager session;
	ArrayAdapter<Account> adapter;
	
	
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.viewaccounts_view);
	
		final Context context = this; 
		DatabaseHandler db = new DatabaseHandler(this);
		session = new SessionManager(context);
		long id = session.getUserID();
		Log.d("SessionManager", "ID: "+id);
		List<Account> list = db.getAllAccountsByID(id);
		Log.d("AccountList", list.get(0).toString());
		adapter = new ArrayAdapter<Account>(context, android.R.layout.simple_list_item_1, list);
	    setListAdapter(adapter);
		
	   // View rootView = inflater.inflate(R.layout.viewaccounts_view, container, false);
	    //ListView myListView = (ListView) this.findViewById(R.id.accountList);   
	    
	}
	

}
