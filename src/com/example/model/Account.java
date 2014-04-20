package com.example.model;

import java.text.NumberFormat;

/**
 * Model for the Account object.
 * 
 * @author Emily Cahill
 *
 */
public class Account implements IAccount {
	/**
	 * @param id The id of the account
	 */
    private long id;
    /**
     * @param accountName The name of the account
     */
    private String accountName;
    /**
     * @param balance The current balance of the account
     */
    private double balance;
    /**
     * @param userID The user ID of the user that created the account
     */
    private long userID;
    /**
     * @param interestRate The interest rate of the account
     */
    private double interestRate;

    /**
     * Default constructor for Account.
     */
    public Account() {

    }

    /**
     * Constructor for account.
     * 
     * @param accName the account name
     * @param bal the account balance
     * @param uID the current user's ID
     * @param intRate the interest rate
     */
    public Account(String accName, double bal, long uID,
            double intRate) {
        this.accountName = accName;
        this.balance = bal;
        this.userID = uID;
        this.interestRate = intRate;
    }

    /**
     * Constructor for Account.
     * 
     * @param curID the account ID
     * @param accName the account name
     * @param bal the starting account balance
     * @param uID the current user's ID
     * @param intRate the interest rate
     */
    public Account(long curID, String accName, double bal, long uID,
            double intRate) {
        this.id = curID;
        this.accountName = accName;
        this.balance = bal;
        this.userID = uID;
        this.interestRate = intRate;
    }
    
    /**
     * Sets the id of the account.
     * @param newID The new account's id
     */
    public void setID(long newID) {
        this.id = newID;
    }
    
    /**
     * Sets the account name.
     * @param name The new account name
     */

    public void setAccountName(String name) {
        this.accountName = name;
    }
    
    /**
     * Sets the balance of the account.
     * @param bal The new balance
     */
    public void setBalance(double bal) {
        this.balance = bal;
    }
    
    /**
     * Sets the user ID of the account.
     * @param newID The new User ID
     */
    public void setUserID(long newID) {
        this.userID = newID;
    }
    
    /**
     * Sets the interest rate of the account.
     * @param rate The new interest rate
     */
    public void setInterestRate(double rate) {
        this.interestRate = rate;
    }
    
    /**
     * Returns the ID of the account.
     * @return the ID of the account
     */
    public long getID() {
        return this.id;
    }
    
    /**
     * Returns the account name.
     * @return the account name
     */
    public String getAccountName() {
        return this.accountName;
    }
    
    /**
     * Returns the current balance.
     * @return the balance of the account
     */
    public double getBalance() {
        return this.balance;
    }
    
    /**
     * Returns the user ID.
     * @return the user id of the account
     */
    public long getUserID() {
        return this.userID;
    }
    
    /**
     * Returns the interest rate.
     * @return the interest rate of the account
     */
    public double getInterestRate() {
        return this.interestRate;
    }
    
    /**
     * Creates a string of account details.
     * @return a string of the account name and balance
     */
    public String toString() {
        NumberFormat us = NumberFormat.getCurrencyInstance();
        return this.accountName + " [" + us.format(balance) + "]";
    }
}
