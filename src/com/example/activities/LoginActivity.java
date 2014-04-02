package com.example.activities;

import com.example.cs2340.R;
import com.example.cs2340.R.id;
import com.example.cs2340.R.layout;
import com.example.cs2340.R.menu;

import com.example.model.SessionManager;
import com.example.model.User;
import com.example.views.ClickListener;
import com.example.model.DatabaseHandler;
import com.example.presenters.IDatabaseHandler;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;
import android.view.View.OnClickListener;

import android.util.Log;

/**
 * Allows the user to login by entering their username or password or register.
 * 
 * @author Emily Cahill
 * 
 */
public class LoginActivity extends Activity {

    /**
     * @param nameField the username field.
     */
    private EditText nameField;
    
    /**
     * @param password the password field.
     */
    private EditText password;
    
    /**
     * @param loginButton allows the user to login to their account.
     */
    private Button loginButton;
    
    /**
     * @param registerButton allows the user to register a new user.
     */
    private Button registerButton;
    
    /**
     * @param session the current session to allow access to the current user's information.
     */
    private SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_view);
        final Context context = this;
        final IDatabaseHandler db = new DatabaseHandler(context);

        session = new SessionManager(getApplicationContext());
        nameField = (EditText) findViewById(R.id.AccountNameField);
        password = (EditText) findViewById(R.id.AcctBalanceField);
        loginButton = (Button) findViewById(R.id.loginButton);
        registerButton = (Button) findViewById(R.id.registerButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (getName().length() > 0 && getPassword().length() > 0) {
                    User u = db.getUserByUP(getName(), getPassword());
                    if (u != null) {
                        if (!u.getUsername().equals("admin")) {
                            session.createLoginSession(u.getUsername(),
                                    u.getID());
                            Intent accMain = new Intent(LoginActivity.this,
                                    AccountMain.class);
                            startActivity(accMain);
                        }
                        setContentView(R.layout.loginsuccess_view);
                    } else {
                        Toast.makeText(context, "Login Failed.",
                                Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this,
                        RegisterActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    /**
     * Returns the username entered in the appropriate EditText.
     * @return the string obtained from the nameField, which is the username
     *  
     */
    public String getName() {
        return nameField.getText().toString().trim();
    }

    /**
     * Returns the password entered in the password EditText.
     * @return the string obtained from the password EditText, which is the password
     */
    public String getPassword() {
        return password.getText().toString().trim();
    }
}
