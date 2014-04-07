package com.example.activities;

import java.text.NumberFormat;
import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.cs2340.R;
import com.example.model.Account;
import com.example.model.DatabaseHandler;
import com.example.model.SessionManager;
import com.example.model.Transaction;
import com.example.presenters.IDatabaseHandler;
import com.example.presenters.TransactionAdapter;

/**
 * Allows the user to make a transaction for the corresponding account.
 * 
 * @author Ryan Farrow
 *
 */
public class MakeTransactionActivity extends Activity {

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
     * @param acceptButton the button to press after the user inputs
     * all the required data for the transaction.
     */
    private Button acceptButton;
    
    //private String transactionType;
    
    /**
     * @param transactionReason the reason for the transaction.
     */
    private EditText transactionReason;
    
    /**
     * @param transactionAmount the amount in the transaction.
     */
    private EditText transactionAmount;

    
    /**
     * @param session the current session
     */
    private SessionManager session;
    
    /**
     * @param DEPOSIT a string used for comparison.
     */
    private static final String DEPOSIT = "Deposit";
    
    /**
     * @param WITHDRAW a string used for comparison.
     */
    private static final String WITHDRAW = "Withdraw";
    
    /**
     * @param date the date of the transaction.
     */
    private Date date;    
    
    /**
     * @param context the current context of the application.
     */
    private final Context context = this;
    
    /**
     * @param the database of the application.
     */
    private final IDatabaseHandler db = new DatabaseHandler(context);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maketransaction_view);
        session = new SessionManager(getApplicationContext());
        final long userID = session.getUserID();
        final List<Account> accountList = db.getAllAccountsByID(userID);
        transactionRadioGroup = (RadioGroup) findViewById(R.id.transactionradiogroup);
        acceptButton = (Button) findViewById(R.id.acceptTransaction);
        transactionReason = (EditText) findViewById(R.id.transactionReasonEditText);
        transactionAmount = (EditText) findViewById(R.id.transactionAmountEditText);

        acceptButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // get the selected radio button from the group
                int selectedOption = transactionRadioGroup
                        .getCheckedRadioButtonId();

                // find the radiobutton by the previously returned id
                transactionRadioButton = (RadioButton) findViewById(selectedOption);
                long itemID = getIntent().getLongExtra("itemID", 0);
                date = new Date();
                if (transactionAmount.getText().toString().length() > 0
                        && getTransactionReason().length() > 0) {
                    String transactionName = transactionReason.getText()
                            .toString();
                    double amount = Double.parseDouble(transactionAmount
                            .getText().toString());

                    Account curAccount = null;
                    for (int i = 0; i < accountList.size(); i++) {
                        if (i == itemID - 1) {
                            curAccount = accountList.get(i);
                        }
                    }
                    double curBalance = curAccount.getBalance();
                    Transaction trans = new Transaction(session.getUserID(),
                            curAccount.getID(), transactionName, 0, 0, date
                                    .getTime());
                    trans.setTransactionType(transactionRadioButton.getText()
                            .toString());
                    TransactionAdapter transAdapter = new TransactionAdapter();
                    NumberFormat us = NumberFormat.getCurrencyInstance();

                    if (trans.getTransactionType().equals(DEPOSIT)) {
                        if (amount <= 0) {
                            Toast.makeText(context, "Invalid deposit amount!",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            transAdapter.setFinalDepositAmount(amount,
                                    curBalance);
                            curAccount.setBalance(transAdapter
                                    .getAfterDeposit());
                            trans.setDepositAmount(amount);
                            db.addTransaction(trans);
                            Toast.makeText(
                                    context,
                                    "New balance:  "
                                            + us.format(curAccount.getBalance()),
                                    Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        if (amount > curBalance || amount < 0) {
                            Toast.makeText(context, "Invalid withdraw amount!",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            transAdapter.setFinalWithdrawAmount(amount,
                                    curBalance);
                            curAccount.setBalance(transAdapter
                                    .getAfterWithdraw());
                            trans.setWithdrawAmount(amount);
                            db.addTransaction(trans);
                            Toast.makeText(
                                    context,
                                    "New balance: "
                                            + us.format(curAccount.getBalance()),
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                    nextIntent(curAccount, trans);
                } else {
                    Toast.makeText(context, "Transaction Failed.",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    /**
     * Initiates the next intent.
     * 
     * @param curAccount the current account
     * @param trans the current transaction
     */
    public void nextIntent(Account curAccount, Transaction trans) {
    	db.updateAccount(curAccount);
        Log.d("DatabaseHandler",
                "Transaction: [" + trans.getTransactionName()
                        + "] added to account: "
                        + curAccount.getAccountName());
        Intent i = new Intent(MakeTransactionActivity.this,
                ViewAccountsActivity.class);
        MakeTransactionActivity.this.finish();
        startActivity(i);
    }

    /**
     * Gets the transaction reason.
     * 
     * @return the reason for the transaction
     */
    public String getTransactionReason() {
        return transactionReason.getText().toString();
    }

    /**
     * Gets the transaction amount.
     * 
     * @return the amount of the transaction
     */
    public double getTransactionAmount() {
        String transactionAmountString = transactionAmount.getText().toString();
        return Double.parseDouble(transactionAmountString);
    }
}
