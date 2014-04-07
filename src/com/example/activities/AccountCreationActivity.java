package com.example.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cs2340.R;
import com.example.model.Account;
import com.example.model.DatabaseHandler;
import com.example.model.SessionManager;
import com.example.presenters.AccountRules;
import com.example.presenters.IDatabaseHandler;

/**
 * Allows the user to create a new account.
 *
 * @author Jesse Wu
 */
public class AccountCreationActivity extends Activity {

    /**
     * @param accName the name of the new account.
     */
    private EditText accName;

    /**
     * @param accBalance the starting balance of the new account.
     */
    private EditText accBalance;

    /**
     * @param monthlyInterestRate the interest rate of the new account.
     */
    private EditText monthlyInterestRate;

    /**
     * @param acceptButton creates the new account.
     */
    private Button acceptButton;

    /**
     * @param session the current session to allow access to the current
     * user's information.
     */
    private SessionManager session;

    @Override
    public final void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.accountcreation_view);
        final Context context = this;
        final IDatabaseHandler db = new DatabaseHandler(context);
        final AccountRules rules = new AccountRules(db);
        session = new SessionManager(getApplicationContext());

        accName = (EditText) findViewById(R.id.AccNameField);
        accBalance = (EditText) findViewById(R.id.eAccBalanceField);
        monthlyInterestRate = (EditText) 
        					findViewById(R.id.MonthlyInterestField);
        acceptButton = (Button) findViewById(R.id.acceptButton);
        // declineButton = (Button)findViewById(R.id.declineButton);
        session.checkLogin();
        acceptButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(final View v) {
                if (accName.getText().toString().length() > 0
                        && accBalance.getText().toString().length() > 0
                        && monthlyInterestRate.getText().toString().length() > 0) {
                    long userID = session.getUserID();
                    if (rules.checkAccountName(userID, accName.getText()
                            .toString())) {
                        Account a = new Account(accName.getText().toString(),
                                Double.parseDouble(accBalance.getText()
                                        .toString()), userID, Double
                                        .parseDouble(monthlyInterestRate
                                                .getText().toString()));
                        long id = db.createAccount(a);
                        Log.d("Account Balance", "Balance: " + a.getBalance());
                        Log.d("Account", "Balance from db: "
                                + db.getAccount(id).getBalance());
                        a.setID(id);

                        Intent i = new Intent(AccountCreationActivity.this,
                                AccountMain.class);
                        startActivity(i);
                    } else {
                        Toast.makeText(context, "Account name already exists!",
                                Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(context, "Account creation unsuccessful!",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    /**
     * Returns the account name.
     *
     * @return the account name based on the string entered in the accName field
     */
    public String getAccountName() {
        return accName.getText().toString();
    }

    /**
     * Returns the account balance.
     *
     * @return the account balance based on the double entered in the 
     * accBalance field
     */
    public final double getAccountBalance() {
        String accBalanceString = accBalance.getText().toString();
        return Double.parseDouble(accBalanceString);
    }

    /**
     * Returns the monthly interest rate.
     *
     * @return the monthly interest rate based on the double entered in the monthlyInterestRate field
     */
    public final double getMonthlyInterestRate() {
        String monthlyInterestRateString = monthlyInterestRate.getText()
                .toString();
        return Double.parseDouble(monthlyInterestRateString);
    }
}
