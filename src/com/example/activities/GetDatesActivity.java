package com.example.activities;

import android.os.Bundle;
import android.widget.TextView;

import com.example.cs2340.R;
import com.example.presenters.GetDatesPresenter;
import com.example.views.GetDatesView;

/**
 * Allows the user to enter the time frame that will be used to generate 
 * reports.
 * 
 * @author Johnny Farrow
 *
 */
public class GetDatesActivity extends DesignActivity implements GetDatesView {
    
    /**
     * @param startDate the starting date of the time frame.
     */
    private TextView startDate;
    
    /**
     * @param endDate the end date of the time frame.
     */
    private TextView endDate;

    private GetDatesPresenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.genspendcatrepdates_view);
        presenter = new GetDatesPresenter(this, this);
    }
    
    public String getStartDate() {
    	startDate = (TextView) findViewById(R.id.sDate);
    	return startDate.getText().toString();
    }
    
    public String getEndDate() {
    	endDate = (TextView) findViewById(R.id.eDate);
    	return endDate.getText().toString();
    }
}