package com.example.model;

import java.sql.Date;
import java.text.NumberFormat;

/**
 * Model for the Transaction class
 * @author Emily Cahill
 *
 */

public class Transaction {
	/**
	 * @param id the id of the transaction
	 */
    private long id;
    /**
     * @param userID the id of the user making the transaction
     */
    private long userID;
    /**
     * @param accountID the id of the account associated with the transaction
     */
    private long accountID;
    /*
     * @param transactionName the name of the transaction
     */
    private String transactionName;
    /*
     * @param depositAmount the amount being deposited
     */
    private double depositAmount;
    /*
     * @param withdrawAmount the amount being withdrawn
     */
    private double withdrawAmount;
    /*
     * @param date the Date of the transaction
     */
    private long date;
    /*
     * @param account The account where the transaction is occurring 
     */
    private Account account;
    /*
     * @param DEPOSIT the name of the deposit key
     */
    private static final String DEPOSIT = "Deposit";
    /*
     * @param WITHDRAW the name of the withdraw key
     */
    private static final String WITHDRAW = "Withdraw";
    /*
     * @param transactionType the type of transaction
     */
    private String transactionType;

    // to be stored for record
    // private double finalWithdrawAmount;
    // private double finalDepositAmount;

    public Transaction() {

    }

    public Transaction(Account account, String transactionName, long date) {
        this.transactionName = transactionName;
        this.account = account;
        this.date = date;
    }

    public Transaction(long userID, long accountID, String transactionName,
            double depositAmount, double withdrawAmount, long date) {
        this.userID = userID;
        this.accountID = accountID;
        this.transactionName = transactionName;
        this.depositAmount = depositAmount;
        this.withdrawAmount = withdrawAmount;
        this.date = date;
        // db = new DatabaseHandler(context);
        // this.curAccount = db.getAccount(accountID);
    }

    public Transaction(long id, long userID, long accountID,
            String transactionName, double depositAmount,
            double withdrawAmount, long date) {
        this.id = id;
        this.userID = userID;
        this.accountID = accountID;
        this.transactionName = transactionName;
        this.depositAmount = depositAmount;
        this.withdrawAmount = withdrawAmount;
        this.date = date;
    }
    /**
     * Create a withdraw transaction
     * @param withdrawAmount The amount being withdrawn
     */
    public void withdraw(double withdrawAmount) {
        double curBalance = account.getBalance();
        if (withdrawAmount <= curBalance && withdrawAmount > 0) {
            account.setBalance(curBalance - withdrawAmount);
        }
        this.withdrawAmount = withdrawAmount;
    }
    /**
     * Create a deposit transaction
     * @param depositAmount The amount being deposited
     */
    public void deposit(double depositAmount) {
        double curBalance = account.getBalance();
        if (depositAmount > 0) {
            account.setBalance(curBalance + depositAmount);
        }
        this.depositAmount = depositAmount;
    }
    /**
     * get the id of the transaction
     * @return the id of the transaction
     */
    public long getID() {
        return this.id;
    }
    /**
     * set the id of the transaction
     * @param id The new id
     */
    public void setID(long id) {
        this.id = id;
    }
    /**
     * Get the id of the user making the transaction
     * @return the user id
     */
    public long getUserID() {
        return this.userID;
    }
    /**
     * Set the user id
     * @param uid The new user id
     */
    public void setUserID(long uid) {
        this.userID = uid;
    }
    /**
     * Get the id of the account the transaction is being made in
     * @return The account id
     */
    public long getAccountID() {
        return this.accountID;
    }
    /**
     * Set the id of the account
     * @param aid The new account id
     */
    public void setAccountID(long aid) {
        this.accountID = aid;
    }
    /**
     * Get the name of the transaction being made
     * @return the transaction name
     */
    public String getTransactionName() {
        return transactionName;
    }
    /**
     * Sets the name of the transaction being made
     * @param transactionName the new transaction name
     */
    public void setTransactionName(String transactionName) {
        this.transactionName = transactionName;
    }
    /**
     * Get the amount being deposited
     * @return deposit amount
     */
    public double getDepositAmount() {
        return depositAmount;
    }
    /**
     * Sets the amount being deposited
     * @param depositAmount The new deposit amount
     */
    public void setDepositAmount(double depositAmount) {
        this.depositAmount = depositAmount;
    }
    /**
     * Gets the amount being withdrawn
     * @return The withdrawal amount
     */
    public double getWithdrawAmount() {
        return withdrawAmount;
    }
    /**
     * Sets the amount being withdrawn
     * @param withdrawAmount The new withdrawal amount
     */
    public void setWithdrawAmount(double withdrawAmount) {
        this.withdrawAmount = withdrawAmount;
    }
    /**
     * Gets the date of the transaction
     * @return the date
     */
    public long getDate() {
        return this.date;
    }
    /**
     * Sets the date of the transaction
     * @param d The new date
     */
    public void setDate(long d) {
        this.date = d;
    }
    /**
     * Sets the type of transaction
     * @param type The new transaction type
     */
    public void setTransactionType(String type) {
        transactionType = type;
    }
    /**
     * Gets the type of transaction
     * @return transaction type
     */
    public String getTransactionType() {
        return transactionType;
    }
    /**
     * Puts transaction info into string
     * @return string of transaction info
     */
    public String toString() {
        NumberFormat us = NumberFormat.getCurrencyInstance();
        if (getWithdrawAmount() > 0) {
            return ("Transaction Name: [" + transactionName
                    + "] Withdraw Amount: " + us.format(getWithdrawAmount()));
        } else {
            return ("Transaction Name: [" + transactionName
                    + "] Deposit Amount: " + us.format(getDepositAmount()));
        }
    }
}
