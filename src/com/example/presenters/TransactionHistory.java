package com.example.presenters;

import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import com.example.model.Transaction;

public class TransactionHistory {
    private List<Transaction> transactionList;

    public TransactionHistory(List<Transaction> transactionList) {
        this.transactionList = transactionList;
    }

    public List<Transaction> getTransactions() {
        return transactionList;
    }

    public List<Transaction> getWithdrawals() {
        List<Transaction> withdrawals = new ArrayList<Transaction>();
        for (Transaction t : transactionList) {
            if (t.getWithdrawAmount() != 0) {
                withdrawals.add(t);
            }
        }
        return withdrawals;
    }

    public List<Transaction> getDeposits() {
        List<Transaction> deposits = new ArrayList<Transaction>();
        for (Transaction t : transactionList) {
            if (t.getDepositAmount() != 0) {
                deposits.add(t);
            }
        }
        return deposits;
    }

    public int getTransactionAmount() {
        return transactionList.size();
    }

    /*
     * public List<Transaction> getTransactionsByDate(Date start, Date end){ for
     * (Transaction t : transactionList){ if (t.getDate()) } }
     */
}
