package com.example.activities;

import android.os.Bundle;
import android.widget.EditText;

import com.example.cs2340.R;
import com.example.presenters.RegisterPresenter;
import com.example.views.RegisterView;

/**
 * This activity allows the user to register.
 * 
 * @author Ryan Farrow
 *
 */
public class RegisterActivity extends DesignActivity implements RegisterView {
	
	/**
	 * @param username the user's desired username.
	 */
    private EditText username;
    
    /**
     * @param passwrod the user's desired password.
     */
    private EditText password;
    
    /**
     * @param cPassword the confirm password field.
     */
    private EditText cPassword;
    
    private RegisterPresenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_view);
        presenter = new RegisterPresenter(this, this);
    }

    /**
     * Returns the username that was entered in the username field.
     * 
     * @return the username
     */
    public String getUsername() {
    	username = (EditText) findViewById(R.id.NameField);
        return username.getText().toString();
    }

    /**
     * returns the password that was entered in the password field.
     * 
     * @return the password
     */
    public String getPassword() {
    	password = (EditText) findViewById(R.id.PassField);
        return password.getText().toString();
    }

    /**
     * Returns the second confirm password that was entered.
     * 
     * @return the second password entry
     */
    public String getCheckPassword() {
    	cPassword = (EditText) findViewById(R.id.CPassField);
        return cPassword.getText().toString();
    }
}
