package com.example.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.cs2340.R;

/**
 * The main home screen of the application.
 * 
 * @author Erin Cahill
 *
 */
public class MainActivity extends Activity {

	/**
	 * @param loginButton logs the user in
	 */
    private Button loginButton;
    
    /**
     * @param registerButton takes the user to a registration screen
     */
    private Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // final Context context = this;
        //IDatabaseHandler db = new DatabaseHandler(this);

        /**
         * Refresh database Delete these 2 lines after running app ONE time
         */
        // SQLiteDatabase d = ((DatabaseHandler) db).getDB();
        // db.onUpgrade(d, 1, 1);

       // List<User> l = db.getAllUsers();
        loginButton = (Button) this.findViewById(R.id.acceptButton);
        registerButton = (Button) this.findViewById(R.id.declineButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(i);
            }
        }); // Add event listener to button

        registerButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(i);
            }
        });
    }
}
