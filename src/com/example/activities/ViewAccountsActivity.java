package com.example.activities;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;

import com.example.cs2340.R;
import com.example.presenters.ViewAccountsPresenter;
import com.example.views.ClickListener;
import com.example.views.ViewAccountsView;

/**
 * Activity for viewing any existing accounts belonging to the user.
 *  
 * @author Jesse Wu
 */
public class ViewAccountsActivity extends ListActivity implements ViewAccountsView {

    private ClickListener listener;
    
    private ViewAccountsPresenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewaccounts_view);
        presenter = new ViewAccountsPresenter(this, this);
    }
    
    public void linkNotifyCallback(ClickListener aListener) {
		listener = aListener;
	}

	public void onButtonClick(View v) {
		listener.onClick(v);
	}
}
