package com.example.activities;

import android.os.Bundle;
import android.view.Menu;
import android.widget.EditText;

import com.example.cs2340.R;
import com.example.presenters.LoginPresenter;
import com.example.views.LoginView;

/**
 * Allows the user to login by entering their username or password or register.
 * 
 * @author Emily Cahill
 * 
 */
public class LoginActivity extends DesignActivity implements LoginView {

    /**
     * @param nameField the username field.
     */
    private EditText nameField;
    
    /**
     * @param password the password field.
     */
    private EditText password;
    
    private LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_view);
        presenter = new LoginPresenter(this, this);
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
    public String getUsername() {
    	nameField = (EditText) findViewById(R.id.AccountNameField);
        return nameField.getText().toString().trim();
    }

    /**
     * Returns the password entered in the password EditText.
     * @return the string obtained from the password EditText, which is the password
     */
    public String getPassword() {
    	password = (EditText) findViewById(R.id.AcctBalanceField);
        return password.getText().toString().trim();
    }
}
