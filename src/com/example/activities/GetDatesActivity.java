package com.example.activities;

import java.sql.Date;
import java.util.ArrayList;

import java.util.List;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cs2340.R;
import com.example.model.Account;
import com.example.model.DatabaseHandler;
import com.example.model.SessionManager;
import com.example.model.Transaction;
import com.example.presenters.IDatabaseHandler;
import com.example.presenters.TransactionHistory;

public class GetDatesActivity extends Activity {
    private SessionManager session;
    private TextView startDate;
    private TextView endDate;
    private Button makeReportButton;
    private List<Account> accounts;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.genspendcatrepdates_view);

        final Context context = this;
        final IDatabaseHandler db = new DatabaseHandler(context);

        session = new SessionManager(getApplicationContext());
        accounts = db.getAllAccountsByID(session.getUserID());
        startDate = (TextView) findViewById(R.id.sDate);
        endDate = (TextView) findViewById(R.id.eDate);
        makeReportButton = (Button) findViewById(R.id.makeReport);

        // final long id = session.getAccountID();

        makeReportButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (startDate.getText().toString().length() > 0
                        && endDate.getText().toString().length() > 0) {
                    long start = 0, end = 0;
                    boolean flag = false;
                    try {
                        start = Date.parse(startDate.getText().toString());
                        end = Date.parse(endDate.getText().toString());
                        flag = true;
                    } catch (IllegalArgumentException e) {
                        Toast.makeText(context, "Please enter a valid date!",
                                Toast.LENGTH_SHORT).show();
                    }

                    // long startDate = startDate.getMinDate();
                    // long endDate = endDate.getMinDate();

                    List<Transaction> trans = db.getTransactionsByDates(start,
                            end, session.getUserID());
                    // Toast.makeText(context, "UserID: " + session.getUserID(),
                    // Toast.LENGTH_SHORT).show();

                    if (flag == true) {
                        Intent i = new Intent(GetDatesActivity.this,
                                SpendCatReportActivity.class);
                        long[] dates = { start, end };
                        i.putExtra("DATES", dates);
                        startActivity(i);
                    }
                } else {
                    Toast.makeText(GetDatesActivity.this, "Invalid date!",
                            Toast.LENGTH_SHORT).show();
                }
            }
            // }
        });
    }
}