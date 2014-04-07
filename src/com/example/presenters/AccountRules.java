package com.example.presenters;

import java.util.List;

import com.example.model.Account;

/**
 * Checks the database to see if the given account already exists. 
 * 
 * @author Emily Cahill
 *
 */
public class AccountRules {
    
    /**
     * @param d the database from which to check
     */
    private IDatabaseHandler d;

    /**
     * Constructor for AccountRules. Takes in a IDatabaseHandler instance.
     * 
     * @param db the current database
     */
    public AccountRules(IDatabaseHandler db) {
        d = db;
    }

    /**
     * Checks if the database contains the given account name.
     * 
     * @param userID the current user whom the account belongs to
     * @param name the name of the new account
     * @return false if the account name already exists in the database
     */
    public boolean checkAccountName(long userID, String name) {
        List<Account> l = d.getAllAccountsByID(userID);
        for (Account a : l) {
            if (name.equals(a.getAccountName())) {
                return false;
            }
        }
        return true;
    }
}
