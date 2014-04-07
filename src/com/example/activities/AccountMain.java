package com.example.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.cs2340.R;

/**
 * The main account activity.
 * 
 * @author Kristian Zhelyazkov
 *
 */
public class AccountMain extends Activity {

	/**
	 * @param viewAcc the button that takes the user to a list of current accounts.
	 */
    private Button viewAcc;
    
    /**
     * @param createAcc the button that takes the user to the account creation page.
     */
    private Button createAcc;
    
    /**
     * @param viewSpendCatRep the button that takes the user to the spending category report page.
     */
    private Button viewSpendCatRep;
    
    /**
     * @param logout the button that logs the current user out
     */
    private Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_main);

        viewAcc = (Button) this.findViewById(R.id.bViewAccount);
        createAcc = (Button) this.findViewById(R.id.bCreateAccount);
        viewSpendCatRep = (Button) this
                .findViewById(R.id.bViewSpendingCategoryReport);
        logout = (Button) this.findViewById(R.id.bLogout);

        viewAcc.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(AccountMain.this,
                        ViewAccountsActivity.class);
                startActivity(i);
            }
        });

        createAcc.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(AccountMain.this,
                        AccountCreationActivity.class);
                startActivity(i);
            }
        });

        viewSpendCatRep.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(AccountMain.this, GetDatesActivity.class);
                startActivity(i);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(AccountMain.this, LoginActivity.class);
                AccountMain.this.finish();
                startActivity(i);
            }
        });
    }
}
