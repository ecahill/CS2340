package com.example.presenters;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

import com.example.activities.*;
import com.example.cs2340.R;
import com.example.views.*;

public class AccountMainPresenter implements ClickListener {

	private Activity activity;
	private AccountMainView view;
	
	public AccountMainPresenter(AccountMainView v, Activity act) {
		activity = act;
		view = v;
		view.linkNotifyCallback(this);
	}

	@Override
	public void onClick(View v) {
		int viewAccountsB = R.id.bViewAccount;
		int createAccountB = R.id.bCreateAccount;
		int viewSpendingCatReportB = R.id.bViewSpendingCategoryReport;
		int logoutB = R.id.bLogout;
		int curButton = v.getId();
		
		if (curButton == viewAccountsB) {
			launchViewAccountsActivity();
		} else if (curButton == createAccountB) {
			launchCreateAccountActivity();
		} else if (curButton == viewSpendingCatReportB) {
			launchViewSpendingCatReportActivity();
		} else if (curButton == logoutB) {
			launchLoginActivity();
		}
		activity.finish();
	}
	
	private void launchViewAccountsActivity() {
		Intent viewAccts = new Intent(activity, ViewAccountsActivity.class);
        activity.startActivity(viewAccts);
        activity.overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
	}	
	
	private void launchCreateAccountActivity() {
		Intent createAcc = new Intent(activity, AccountCreationActivity.class);
        activity.startActivity(createAcc);
        activity.overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
	}
	
	private void launchViewSpendingCatReportActivity() {
		Intent spendCatReport = new Intent(activity, GetDatesActivity.class);
        activity.startActivity(spendCatReport);
        activity.overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
	}
	
	private void launchLoginActivity() {
		Intent login = new Intent(activity, LoginActivity.class);
        activity.finish();
        activity.startActivity(login);
        activity.overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);
	}
	
}
