package com.example.model;

import java.text.NumberFormat;

/**
 * Model for the Account object
 * @author Emily Cahill
 *
 */

public class Account {
	/**
	 * @param id The id of the account
	 */
    private long id;
    /**
     * @param account_name The name of the account
     */
    private String account_name;
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

    public Account() {

    }

    public Account(String account_name, double balance, long userID,
            double interestRate) {
        this.account_name = account_name;
        this.balance = balance;
        this.userID = userID;
        this.interestRate = interestRate;
    }

    public Account(long id, String account_name, double balance, long userID,
            double interestRate) {
        this.id = id;
        this.account_name = account_name;
        this.balance = balance;
        this.userID = userID;
        this.interestRate = interestRate;
    }
    /**
     * sets the id of the account
     * @param id The new account id
     */
    public void setID(long id) {
        this.id = id;
    }
    /**
     * sets the account name
     * @param name The new account name
     */

    public void setAccountName(String name) {
        this.account_name = name;
    }
    /**
     * sets the balance of the account
     * @param bal The new balance
     */
    public void setBalance(double bal) {
        this.balance = bal;
    }
    /**
     * sets the user id of the account
     * @param id The new User ID
     */
    public void setUserID(long id) {
        this.userID = id;
    }
    /**
     * Sets the interest rate of the account
     * @param rate The new interest rate
     */
    public void setInterestRate(double rate) {
        this.interestRate = rate;
    }
    /**
     * Returns the id of the account
     * @return the id of the account
     */
    public long getID() {
        return this.id;
    }
    /**
     * returns the account name
     * @return the account name
     */
    public String getAccountName() {
        return this.account_name;
    }
    /**
     * returns the current balance
     * @return the balance of the account
     */
    public double getBalance() {
        return this.balance;
    }
    /**
     * returns the user id
     * @return the user id of the account
     */
    public long getUserID() {
        return this.userID;
    }
    /**
     * returns the interest rate
     * @return the interest rate of the account
     */
    public double getInterestRate() {
        return this.interestRate;
    }
    /**
     * creates a string of account details
     * @return a string of the account name and balance
     */
    public String toString() {
        NumberFormat us = NumberFormat.getCurrencyInstance();
        return this.account_name + " [" + us.format(balance) + "]";
    }
}
