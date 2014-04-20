package com.example.model;

import java.text.NumberFormat;

public interface ITransaction {
	
	 /**
     * Create a withdraw transaction.
     * 
     * @param withdraw The amount being withdrawn
     */
    public void withdraw(double withdraw);
    
    /**
     * Create a deposit transaction.
     * 
     * @param deposit The amount being deposited
     */
    public void deposit(double deposit);
    
    /**
     * Get the id of the transaction.
     * 
     * @return the id of the transaction
     */
    public long getID();
    
    /**
     * Set the id of the transaction.
     * 
     * @param aID The new id
     */
    public void setID(long aID);
    
    /**
     * Get the id of the user making the transaction.
     * 
     * @return the user id
     */
    public long getUserID();
    
    /**
     * Set the user id.
     * 
     * @param uid The new user id
     */
    public void setUserID(long uid);
    
    /**
     * Get the id of the account the transaction is being made in.
     * 
     * @return The account id
     */
    public long getAccountID();
    
    /**
     * Set the id of the account.
     * 
     * @param aid The new account id
     */
    public void setAccountID(long aid);
    
    /**
     * Get the name of the transaction being made.
     * 
     * @return the transaction name
     */
    public String getTransactionName();
    
    /**
     * Sets the name of the transaction being made.
     * 
     * @param name the new transaction name
     */
    public void setTransactionName(String name);
    
    /**
     * Get the amount being deposited.
     * 
     * @return deposit amount
     */
    public double getDepositAmount();
    
    /**
     * Sets the amount being deposited.
     * 
     * @param deposit The new deposit amount
     */
    public void setDepositAmount(double deposit);
    
    /**
     * Gets the amount being withdrawn.
     * 
     * @return The withdrawal amount
     */
    public double getWithdrawAmount();
    
    /**
     * Sets the amount being withdrawn.
     * 
     * @param withdraw The new withdrawal amount
     */
    public void setWithdrawAmount(double withdraw);
    
    /**
     * Gets the date of the transaction.
     * 
     * @return the date
     */
    public long getDate();
    
    /**
     * Sets the date of the transaction.
     * 
     * @param d The new date
     */
    public void setDate(long d);
    
    /**
     * Sets the type of transaction.
     * 
     * @param type The new transaction type
     */
    public void setTransactionType(String type);
    
    /**
     * Gets the type of transaction.
     * 
     * @return transaction type
     */
    public String getTransactionType();
    
    /**
     * Puts transaction info into string.
     * 
     * @return string of transaction info
     */
    public String toString();
}
