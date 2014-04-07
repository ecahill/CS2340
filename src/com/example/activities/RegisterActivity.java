package com.example.activities;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cs2340.R;
import com.example.model.DatabaseHandler;
import com.example.model.User;
import com.example.presenters.IDatabaseHandler;

/**
 * This activity allows the user to register.
 * 
 * @author Ryan Farrow
 *
 */
public class RegisterActivity extends Activity {
	
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
    
    /**
     * @param the button the user should press to finish the registration.
     */
    private Button goButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_view);

        final Context context = this;
        final IDatabaseHandler db = new DatabaseHandler(context);

        username = (EditText) findViewById(R.id.NameField);
        password = (EditText) findViewById(R.id.PassField);
        cPassword = (EditText) findViewById(R.id.CPassField);
        goButton = (Button) findViewById(R.id.RegButton);

        goButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (db.checkUsername(getName())) {
                    if (getPassword().equals(getCPassword())) {
                        long id = db
                                .addUser(new User(getName(), getPassword()));
                        User u = db.getUser(id);
                        u.setID(id);
                    } else {
                        Toast.makeText(context, "Passwords do not match",
                                Toast.LENGTH_LONG).show();
                    }
                    Toast.makeText(context, "User has been registered",
                            Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(context, "Username is taken",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    /**
     * Returns the username that was entered in the username field.
     * 
     * @return the username
     */
    public String getName() {
        return username.getText().toString();
    }

    /**
     * returns the password that was entered in the password field.
     * 
     * @return the password
     */
    public String getPassword() {
        return password.getText().toString();
    }

    /**
     * Returns the second confirm password that was entered.
     * 
     * @return the second password entry
     */
    public String getCPassword() {
        return cPassword.getText().toString();
    }
}
