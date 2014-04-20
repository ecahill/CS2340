package com.example.activities;

import android.os.Bundle;
import android.widget.EditText;

import com.example.cs2340.R;
import com.example.presenters.AccountCreationPresenter;
import com.example.views.AccountCreationView;

/**
 * Allows the user to create a new account.
 *
 * @author Jesse Wu
 */
public class AccountCreationActivity extends DesignActivity implements AccountCreationView {

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

    private AccountCreationPresenter presenter;

    @Override
    public final void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.accountcreation_view);
        presenter = new AccountCreationPresenter(this, this);
    }

    /**
     * Returns the account name.
     *
     * @return the account name based on the string entered in the accName field
     */
    public String getAccountName() {
    	accName = (EditText) findViewById(R.id.AccNameField);
        return accName.getText().toString();
    }

    /**
     * Returns the account balance.
     *
     * @return the account balance based on the double entered in the 
     * accBalance field
     */
    public final double getAccountBalance() {
    	accBalance = (EditText) findViewById(R.id.eAccBalanceField);
        String accBalanceString = accBalance.getText().toString();
        return Double.parseDouble(accBalanceString);
    }

    /**
     * Returns the monthly interest rate.
     *
     * @return the monthly interest rate based on the double entered in the monthlyInterestRate field
     */
    public final double getMonthlyInterestRate() {
    	monthlyInterestRate = (EditText) findViewById(R.id.MonthlyInterestField);
        String monthlyInterestRateString = monthlyInterestRate.getText()
                .toString();
        return Double.parseDouble(monthlyInterestRateString);
    }
    
    public boolean validAccountBalance() {
    	accBalance = (EditText) findViewById(R.id.eAccBalanceField);
    	String accBalanceString = accBalance.getText().toString();
    	if (accBalanceString.length() == 0) {
    		return false;
    	}
    	return true;
    }
    
    public boolean validInterestRate() {
    	monthlyInterestRate = (EditText) findViewById(R.id.MonthlyInterestField);
    	String interestRateString = monthlyInterestRate.getText().toString();
    	if (interestRateString.length() == 0) {
    		return false;
    	}
    	return true;
    }
    
    public boolean validAccountName() {
    	accName = (EditText) findViewById(R.id.AccNameField);
    	String accNameString = accName.getText().toString();
    	if (accNameString.length() == 0) {
    		return false;
    	}
    	return true;
    }
}
