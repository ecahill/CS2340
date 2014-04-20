package com.example.model;

import java.text.NumberFormat;

/**
 * Model for the Transaction class.
 * 
 * @author Emily Cahill
 *
 */

public class Transaction implements ITransaction {
	/**
	 * @param id the id of the transaction.
	 */
    private long id;
    /**
     * @param userID the id of the user making the transaction.
     */
    private long userID;
    /**
     * @param accountID the id of the account associated with the transaction.
     */
    private long accountID;
    /**
     * @param transactionName the name of the transaction.
     */
    private String transactionName;
    /**
     * @param depositAmount the amount being deposited.
     */
    private double depositAmount;
    /**
     * @param withdrawAmount the amount being withdrawn.
     */
    private double withdrawAmount;
    /**
     * @param date the Date of the transaction.
     */
    private long date;
    /**
     * @param account The account where the transaction is occurring.
     */
    private Account account;    
    /**
     * @param DEPOSIT the name of the deposit key.
     */
    private static final String DEPOSIT = "Deposit";
    /**
     * @param WITHDRAW the name of the withdraw key.
     */
    private static final String WITHDRAW = "Withdraw";
    /**
     * @param transactionType the type of transaction.
     */
    private String transactionType;

    /**
     * Default constructor for a transaction.
     */
    public Transaction() {

    }

    /**
     * Constructor for transaction.
     * 
     * @param acc the current account
     * @param name the name of the transaction
     * @param d the date of the transaction
     */
    public Transaction(Account acc, String name, long d) {
        this.transactionName = name;
        this.account = acc;
        this.date = d;
    }

    /**
     * Constructor for transaction.
     * 
     * @param uID the ID of the user based on the database
     * @param aID the accountID based on the database
     * @param name the name of the transaction
     * @param deposit the deposit amount
     * @param withdraw the withdraw amount
     * @param d the date of the transaction
     */
    public Transaction(long uID, long aID, String name,
            double deposit, double withdraw, long d) {
        this.userID = uID;
        this.accountID = aID;
        this.transactionName = name;
        this.depositAmount = deposit;
        this.withdrawAmount = withdraw;
        this.date = d;
        // db = new DatabaseHandler(context);
        // this.curAccount = db.getAccount(accountID);
    }

    /**
     * Constructor for transaction.
     * 
     * @param aID the transaction ID based on the database.
     * @param uID the user ID based on the database.
     * @param accID the account ID based on the database
     * @param name the name of the transaction
     * @param deposit the deposit amount
     * @param withdraw the withdraw amount
     * @param d the date of the transaction
     */
    public Transaction(long aID, long uID, long accID,
            String name, double deposit,
            double withdraw, long d) {
        this.id = aID;
        this.userID = uID;
        this.accountID = accID;
        this.transactionName = name;
        this.depositAmount = deposit;
        this.withdrawAmount = withdraw;
        this.date = d;
    }
    
    /**
     * Create a withdraw transaction.
     * 
     * @param withdraw The amount being withdrawn
     */
    public void withdraw(double withdraw) {
        double curBalance = account.getBalance();
        if (withdrawAmount <= curBalance && withdrawAmount > 0) {
            account.setBalance(curBalance - withdrawAmount);
        }
        this.withdrawAmount = withdraw;
    }
    /**
     * Create a deposit transaction.
     * 
     * @param deposit The amount being deposited
     */
    public void deposit(double deposit) {
        double curBalance = account.getBalance();
        if (depositAmount > 0) {
            account.setBalance(curBalance + depositAmount);
        }
        this.depositAmount = deposit;
    }
    /**
     * Get the id of the transaction.
     * 
     * @return the id of the transaction
     */
    public long getID() {
        return this.id;
    }
    
    /**
     * Set the id of the transaction.
     * 
     * @param aID The new id
     */
    public void setID(long aID) {
        this.id = aID;
    }
    
    /**
     * Get the id of the user making the transaction.
     * 
     * @return the user id
     */
    public long getUserID() {
        return this.userID;
    }
    
    /**
     * Set the user id.
     * 
     * @param uid The new user id
     */
    public void setUserID(long uid) {
        this.userID = uid;
    }
    
    /**
     * Get the id of the account the transaction is being made in.
     * 
     * @return The account id
     */
    public long getAccountID() {
        return this.accountID;
    }
    
    /**
     * Set the id of the account.
     * 
     * @param aid The new account id
     */
    public void setAccountID(long aid) {
        this.accountID = aid;
    }
    
    /**
     * Get the name of the transaction being made.
     * 
     * @return the transaction name
     */
    public String getTransactionName() {
        return transactionName;
    }
    
    /**
     * Sets the name of the transaction being made.
     * 
     * @param name the new transaction name
     */
    public void setTransactionName(String name) {
        this.transactionName = name;
    }
    
    /**
     * Get the amount being deposited.
     * 
     * @return deposit amount
     */
    public double getDepositAmount() {
        return depositAmount;
    }
    
    /**
     * Sets the amount being deposited.
     * 
     * @param deposit The new deposit amount
     */
    public void setDepositAmount(double deposit) {
        this.depositAmount = deposit;
    }
    
    /**
     * Gets the amount being withdrawn.
     * 
     * @return The withdrawal amount
     */
    public double getWithdrawAmount() {
        return withdrawAmount;
    }
    
    /**
     * Sets the amount being withdrawn.
     * 
     * @param withdraw The new withdrawal amount
     */
    public void setWithdrawAmount(double withdraw) {
        this.withdrawAmount = withdraw;
    }
    
    /**
     * Gets the date of the transaction.
     * 
     * @return the date
     */
    public long getDate() {
        return this.date;
    }
    
    /**
     * Sets the date of the transaction.
     * 
     * @param d The new date
     */
    public void setDate(long d) {
        this.date = d;
    }
    
    /**
     * Sets the type of transaction.
     * 
     * @param type The new transaction type
     */
    public void setTransactionType(String type) {
        transactionType = type;
    }
    
    /**
     * Gets the type of transaction.
     * 
     * @return transaction type
     */
    public String getTransactionType() {
        return transactionType;
    }
    
    /**
     * Puts transaction info into string.
     * 
     * @return string of transaction info
     */
    public String toString() {
        NumberFormat us = NumberFormat.getCurrencyInstance();
        if (getWithdrawAmount() > 0) {
            return ("Transaction Name: [" + transactionName
                    + "] Withdraw Amount: " + us.format(getWithdrawAmount()));
        } else {
            return ("Transaction Name:[" + transactionName
                    + "] Deposit Amount: " + us.format(getDepositAmount()));
        }
    }
}
