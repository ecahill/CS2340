package com.example.presenters;

import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.view.View;
import android.widget.ArrayAdapter;

import com.example.activities.GetDatesActivity;
import com.example.cs2340.R;
import com.example.model.DatabaseHandler;
import com.example.model.IDatabaseHandler;
import com.example.model.ISessionManager;
import com.example.model.SessionManager;
import com.example.model.Transaction;
import com.example.model.User;
import com.example.views.ClickListener;
import com.example.views.SpendCatReportView;

public class SpendCatReportPresenter implements ClickListener {

	private SpendCatReportView view;
	private ListActivity activity;
	private long[] dates;
	private IDatabaseHandler db;
	private ISessionManager session;
	
	public SpendCatReportPresenter(SpendCatReportView v, ListActivity act) {
		view = v;
		view.linkNotifyCallback(this);	
		activity = act;
		db = new DatabaseHandler(activity);
		session = new SessionManager(activity.getApplicationContext());	
		Intent previous = activity.getIntent();
		dates = previous.getLongArrayExtra("DATES");
		prepare();
	}
	
	@Override
	public void onClick(View v) {
		Intent intent = new Intent(activity, GetDatesActivity.class);
		activity.finish();
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        activity.startActivity(intent);		
        activity.overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
	}
	
	public void prepare() {
		List<Transaction> trans = db.getTransactionsByDates(dates[0], dates[1],
					session.getUserID());	
        User user = db.getUser(session.getUserID());
        view.setTitle(user.getUsername());        
        double totalWithdrawAmount = prepareTransTotal(trans);       
        view.setTotalWithdraw(totalWithdrawAmount);
        prepareTransList(trans);
        
	}
	
	private void prepareTransList(List<Transaction> trans) {
		ArrayAdapter<Transaction> adapter = new ArrayAdapter<Transaction>(activity,
        		android.R.layout.simple_list_item_1, trans);
		activity.setListAdapter(adapter);
	}
	
	private double prepareTransTotal(List<Transaction> trans) {	
		double totalWithdrawAmount = 0;

        for (int i = 0; i < trans.size(); i++) {
            if ((trans.get(i).getDepositAmount() > 0)
                    || (trans.get(i).getWithdrawAmount() == 0)) {
                trans.remove(i);
            }
            if (i != trans.size()) {
                totalWithdrawAmount = totalWithdrawAmount
                        + trans.get(i).getWithdrawAmount();
            }
        }
        return totalWithdrawAmount;
	}

}
