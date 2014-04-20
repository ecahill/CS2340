package com.example.presenters;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.example.activities.AccountMainActivity;
import com.example.activities.RegisterActivity;
import com.example.cs2340.R;
import com.example.model.DatabaseHandler;
import com.example.model.IDatabaseHandler;
import com.example.model.SessionManager;
import com.example.model.User;
import com.example.views.ClickListener;
import com.example.views.LoginView;

public class LoginPresenter implements ClickListener {
	
	private LoginView view;
	private Activity activity;
	private IDatabaseHandler db;
	private SessionManager session;

	public LoginPresenter(LoginView v, Activity act) {
		view = v;
		view.linkNotifyCallback(this);	
		activity = act;
		db = new DatabaseHandler(activity);
		session = new SessionManager(activity.getApplicationContext());			
	}
	
	@Override
	public void onClick(View v) {
		int registerButton = R.id.registerButton;
		int loginButton = R.id.loginButton;
		int curButton = v.getId();
		
		if (curButton == loginButton) {
			login();
		} else if (curButton == registerButton) {
			launchRegisterActivity();
		}
	}
	
	private void login() {
		String username = view.getUsername();
		String password = view.getPassword();
		
		if (username.length() > 0 && password.length() > 0) {
            User u = db.getUserByUP(username, password);
            if (u != null) {
                if (!u.getUsername().equals("admin")) {
                    session.createLoginSession(u.getUsername(), u.getID());
                    launchAccountMainActivity();
                } else {
                	activity.setContentView(R.layout.loginsuccess_view);
                }                
            } else {
                Toast.makeText(activity, "Login Failed",
                        Toast.LENGTH_SHORT).show();
            }
        } else {
        	Toast.makeText(activity, "Invalid credentials", Toast.LENGTH_SHORT).show();
        }
	}
	
	private void launchRegisterActivity() {
		Intent reg = new Intent(activity, RegisterActivity.class);
		activity.finish();
		activity.startActivity(reg);
	}
	
	private void launchAccountMainActivity() {
		Intent accMain = new Intent(activity, AccountMainActivity.class);
		activity.finish();
		activity.startActivity(accMain);
	}
}
