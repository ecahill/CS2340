package com.example.activities;

import android.os.Bundle;
import android.widget.TextView;

import com.example.cs2340.R;
import com.example.presenters.AccountViewPresenter;
import com.example.views.AccountViewView;

/**
 * This is the account's main screen.
 * 
 * @author Jesse Wu
 *
 */
public class AccountViewActivity extends DesignActivity implements AccountViewView {
    
    /**
     * @param accName the current account name.
     */
    private TextView accName;
    
    private AccountViewPresenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_account_view);
        presenter = new AccountViewPresenter(this, this);
    }
    
    public void setAccName(String name) {
    	accName = (TextView) findViewById(R.id.accNameHeader);
        accName.setText(name);
    }
}
