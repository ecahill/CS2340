package com.example.activities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.cs2340.R;
import com.example.model.DatabaseHandler;
import com.example.model.SessionManager;
import com.example.model.Transaction;
import com.example.presenters.IDatabaseHandler;
import com.example.presenters.TransactionHistory;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

public class genSpendCatReportActivity extends Activity {
	SessionManager session;
	EditText startDate;
	EditText endDate;
	Button makeReportButton;
	DatePicker start;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.genspendcatrepdates_view);
		final Context context = this;
		final IDatabaseHandler db = new DatabaseHandler(context);
		final long id = session.getUserID();
		
		session = new SessionManager(getApplicationContext());
		startDate = (EditText)findViewById(R.id.sDate);
		endDate = (EditText)findViewById(R.id.eDate);
		makeReportButton = (Button)findViewById(R.id.makeReport);
		
		makeReportButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v){
				
				if (startDate != null && endDate != null) {
					TransactionHistory transactionsHist = db.getAllTransactionsByID(id);
					List<Transaction> transactions = transactionsHist.getWithdrawals();
					List<Transaction> validTrans = new ArrayList<Transaction>();
					for (Transaction t : transactions) {
						if (t.getDate() >= Date.parse(startDate.toString()) && t.getDate() <= Date.parse(endDate.toString())) {
							validTrans.add(t);
						}
					}					
				}
			}
		});		
	}
}
