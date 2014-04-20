package com.example.activities;

import android.os.Bundle;

import com.example.cs2340.R;
import com.example.presenters.AccountMainPresenter;
import com.example.views.AccountMainView;

/**
 * The main account activity.
 * 
 * @author Kristian Zhelyazkov
 *
 */
public class AccountMainActivity extends DesignActivity implements AccountMainView {

    private AccountMainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_main);
        presenter = new AccountMainPresenter(this, this);
    }
}
