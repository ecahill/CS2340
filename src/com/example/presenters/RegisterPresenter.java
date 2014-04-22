package com.example.presenters;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.example.activities.LoginActivity;
import com.example.cs2340.R;
import com.example.model.DatabaseHandler;
import com.example.model.IDatabaseHandler;
import com.example.model.User;
import com.example.views.ClickListener;
import com.example.views.RegisterView;

public class RegisterPresenter implements ClickListener {

	private RegisterView view;
	private Activity activity;
	private IDatabaseHandler db;

	
	public RegisterPresenter(RegisterView v, Activity act) {
		view = v;
		view.linkNotifyCallback(this);
		activity = act;
		db = new DatabaseHandler(activity);
	}
	
	@Override
	public void onClick(View v) {
		int goButton = R.id.RegButton;
		int backButton = R.id.bReturntoMain;
		int curButton = v.getId();
		
		if (curButton == goButton) {
			if (register()) {
				launchLoginActivity();
				activity.overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
			}			
		} else if (curButton == backButton) {			
			launchLoginActivity();
			activity.overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
		}
	}
	
	private void launchLoginActivity() { 
		Intent login = new Intent(activity, LoginActivity.class);
		activity.finish();
		activity.startActivity(login);		
	}
	
	private boolean register() {
		if (db.checkUsername(view.getUsername())) {
			if (view.getPassword().length() == 0 || 
            		view.getCheckPassword().length() == 0) {
				Toast.makeText(activity, "Password cannot be empty",
                        Toast.LENGTH_SHORT).show();
				return false;
			}
			
            if (view.getPassword().equals(view.getCheckPassword())) {
                long id = db.addUser(new User(view.getUsername(), view.getPassword()));
                User u = db.getUser(id);
                u.setID(id);
            } else {
                Toast.makeText(activity, "Passwords do not match",
                        Toast.LENGTH_SHORT).show();
                return false;
            }
            Toast.makeText(activity, "User has been registered",
                    Toast.LENGTH_SHORT).show();
            return true;
        } else {
            Toast.makeText(activity, "Username is taken",
                    Toast.LENGTH_SHORT).show();
        }
		return false;
	}

}
