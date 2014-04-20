package com.example.presenters;

import java.sql.Date;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.example.activities.AccountMainActivity;
import com.example.activities.SpendCatReportActivity;
import com.example.cs2340.R;
import com.example.views.ClickListener;
import com.example.views.GetDatesView;

public class GetDatesPresenter implements ClickListener {

	private GetDatesView view;
	private Activity activity;
	
	public GetDatesPresenter(GetDatesView v, Activity act) {
		view = v;
		view.linkNotifyCallback(this);	
		activity = act;
	}
	
	@Override
	public void onClick(View v) {
		int generateReportB = R.id.makeReport;
		int backB = R.id.fromDatesToAccountMain;
		int curButton = v.getId();
		
		if (curButton == generateReportB) {
			if (curButton == generateReportB) {
				
			}
			String startDate = view.getStartDate();
			String endDate = view.getEndDate();
			
			if (startDate.length() > 0 && endDate.length() > 0) {
	            long start = 0;
	            long end = 0;
	            boolean flag = false;
	            try {
	                start = Date.parse(startDate);
	                end = Date.parse(endDate);
	                flag = true;
	            } catch (IllegalArgumentException e) {
	                Toast.makeText(activity, "Please enter a valid date!",
	                        Toast.LENGTH_SHORT).show();
	            }
	            if (flag) {
	                Intent i = new Intent(activity, SpendCatReportActivity.class);
	                long[] dates = {start, end};
	                i.putExtra("DATES", dates);	                
	                activity.finish();
	                activity.startActivity(i);
	            }
	        } else {
	            Toast.makeText(activity, "Invalid date!",
	                    Toast.LENGTH_SHORT).show();
	        }
		} else if (curButton == backB) {
			launchAccMainActivity();			
		}		
	}
	
	private void launchAccMainActivity() {
		Intent i = new Intent(activity, AccountMainActivity.class);
		activity.finish();
		activity.startActivity(i);
	}
	
}
