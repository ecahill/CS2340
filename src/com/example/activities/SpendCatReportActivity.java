package com.example.activities;

import java.text.NumberFormat;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.cs2340.R;
import com.example.presenters.SpendCatReportPresenter;
import com.example.views.ClickListener;
import com.example.views.SpendCatReportView;

/**
 * Activity for viewing the user's spending category report.
 *  
 * @author Jesse Wu
 */

public class SpendCatReportActivity extends ListActivity implements SpendCatReportView {
    /**
     * @param total  display of total spending amount
     */
    private TextView total;
    /**
     * @param title  display for title of specified spending category report type
     */
    private TextView title;
    
    private ClickListener aListener;
    
    private SpendCatReportPresenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewexpensereport_view);
        presenter = new SpendCatReportPresenter(this, this);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id1) {
   
    }
    
    public void setTitle(String newTitle) {
    	title = (TextView) findViewById(R.id.tTitle);
    	title.setText("SCR for [" + newTitle + "]");
    }
    
    public void setTotalWithdraw(double totalWithdrawAmount) {
        total = (TextView) findViewById(R.id.tTotal);
    	NumberFormat us = NumberFormat.getCurrencyInstance();
    	total.setText("Total Spending: " + us.format(totalWithdrawAmount));
    }
    
    public void linkNotifyCallback(ClickListener listener) {
    	aListener = listener;
    }
	
	public void onButtonClick(View v) {
		aListener.onClick(v);
	}
}
