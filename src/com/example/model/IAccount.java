package com.example.model;

import java.text.NumberFormat;

public interface IAccount {
	
	 /**
     * Sets the id of the account.
     * @param newID The new account's id
     */
    public void setID(long newID);
    
    /**
     * Sets the account name.
     * @param name The new account name
     */

    public void setAccountName(String name);
    
    /**
     * Sets the balance of the account.
     * @param bal The new balance
     */
    public void setBalance(double bal);
    
    /**
     * Sets the user ID of the account.
     * @param newID The new User ID
     */
    public void setUserID(long newID);
    
    /**
     * Sets the interest rate of the account.
     * @param rate The new interest rate
     */
    public void setInterestRate(double rate);
    
    /**
     * Returns the ID of the account.
     * @return the ID of the account
     */
    public long getID();
    
    /**
     * Returns the account name.
     * @return the account name
     */
    public String getAccountName();
    
    /**
     * Returns the current balance.
     * @return the balance of the account
     */
    public double getBalance();
    
    /**
     * Returns the user ID.
     * @return the user id of the account
     */
    public long getUserID();
    
    /**
     * Returns the interest rate.
     * @return the interest rate of the account
     */
    public double getInterestRate();
    
    /**
     * Creates a string of account details.
     * @return a string of the account name and balance
     */
    public String toString();
}
