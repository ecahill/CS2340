package com.example.model;

import java.util.List;

import com.example.model.Account;
import com.example.model.Transaction;
import com.example.model.User;

import android.database.sqlite.SQLiteDatabase;

/**
 * DatabaseHandler interface .
 * 
 * @author Emily Cahill
 *
 */
public interface IDatabaseHandler {

	/**
	 * Create tables for database.
	 * @param db The database
	 */
    void onCreate(SQLiteDatabase db);

    // public SQLiteDatabase getDB();
    /**
     * Drop old tables and recreate them.
     * @param db The database
     * @param oldVersion Old database version number
     * @param newVersion New database version number
     */
    void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion);

    /**
     * Create an account in the accounts table.
     * @param a The account being added
     * @return the id of the account added
     */
    long createAccount(IAccount a);

    /**
     * Add a user to the users table.
     * @param u The user to be added
     * @return the id of the user added
     */
    long addUser(IUser u);
    /**
     * Checks the database to see if the username exists.
     * @param username name being checked
     * @return true if the name does not already exist
     */
    boolean checkUsername(String username);
    /**
     * Gets a user from users table by id.
     * @param id User id of the user
     * @return a user
     */
    User getUser(long id);
    /**
     * Gets an account from accounts table by id.
     * @param accountID account id of the account
     * @return an account
     */
    Account getAccount(long accountID);
    /**
     * Gets a user by username and password.
     * @param username Username being checked
     * @param password Password being checked
     * @return a user, null if doesn't exist
     */
    User getUserByUP(String username, String password);
    /**
     * Gets a list of all users in user table.
     * @return List of all users
     */
    List<User> getAllUsers();
    /**
     * Get all accounts for a specific user id.
     * @param id The user id
     * @return list of all accounts associated with that user id
     */
    List<Account> getAllAccountsByID(long id);

//    public TransactionHistory getAllTransactionsByID(long id);
    
    /**
     * Returns transactions that are within a specified date range.
     * @param startDate Beginning of date range
     * @param endDate End of date range
     * @param userID User ID for transactions
     * @return a list of all transactions that apply
     */
    List<Transaction> getTransactionsByDates(long startDate,
            long endDate, long userID);
    /**
     * Gets number of users in user table.
     * @return number of users
     */
    int getUsersCount();
    /**
     * Updates a user in the database.
     * @param user User with new criteria
     * @return The id of the user
     */
    int updateUser(IUser user);
    /**
     * Updates an account in the accounts table.
     * @param a The account being updated
     * @return the account id
     */
    int updateAccount(IAccount a);
    /**
     * Deletes a user from the users table.
     * @param user The user to be deleted
     */
    void deleteUser(IUser user);
    /**
     * Adds a transactions to the transactions table. 
     * @param t The transaction to be added
     * @return the id of the transaction
     */
    long addTransaction(ITransaction t);

}
