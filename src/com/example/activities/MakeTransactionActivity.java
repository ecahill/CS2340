package com.example.activities;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.cs2340.R;
import com.example.presenters.MakeTransactionPresenter;
import com.example.views.MakeTransactionView;

/**
 * Allows the user to make a transaction for the corresponding account.
 * 
 * @author Ryan Farrow
 *
 */
public class MakeTransactionActivity extends DesignActivity implements MakeTransactionView {

	/**
	 * @param transactionRadioGroup provides the user with an option to chose
	 * between deposit and withdrawal.
	 */
    private RadioGroup transactionRadioGroup;
    
    /**
     * @param transactionRadioButton allows the user to chose between deposit
     * and withdrawal.
     */
    private RadioButton transactionRadioButton;
        
    /**
     * @param transactionReason the reason for the transaction.
     */
    private EditText transactionReason;
    
    /**
     * @param transactionAmount the amount in the transaction.
     */
    private EditText transactionAmount;
    
    private MakeTransactionPresenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maketransaction_view);
        presenter = new MakeTransactionPresenter(this, this);
    }

    /**
     * Gets the transaction reason.
     * 
     * @return the reason for the transaction
     */
    public String getTransactionReason() {
    	transactionReason = (EditText) findViewById(R.id.transactionReasonEditText);
        return transactionReason.getText().toString();
    }

    /**
     * Gets the transaction amount.
     * 
     * @return the amount of the transaction
     */
    public double getTransactionAmount() {
    	transactionAmount = (EditText) findViewById(R.id.transactionAmountEditText);
        String transactionAmountString = transactionAmount.getText().toString();
        return Double.parseDouble(transactionAmountString);
    }
    
    public String getTransactionType() {
    	transactionRadioGroup = (RadioGroup) findViewById(R.id.transactionradiogroup);
    	int selectedType = transactionRadioGroup.getCheckedRadioButtonId();
    	transactionRadioButton = (RadioButton) findViewById(selectedType);
    	return transactionRadioButton.getText().toString();
    }
    
    public boolean verifyTransactionAmount() {
    	transactionAmount = (EditText) findViewById(R.id.transactionAmountEditText);
    	String transactionAmountString = transactionAmount.getText().toString();
    	return (transactionAmountString.length() > 0);
    }
}
