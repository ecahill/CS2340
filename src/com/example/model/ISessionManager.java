package com.example.model;

import java.util.HashMap;

public interface ISessionManager {

	public void createLoginSession(String name, long id);

    /**
     * Creates an account session.
     * 
     * @param name The username of the user logged in
     * @param id The user id of the user logged in
     * @param accountid The account id of the account that the user is using
     */
    public void createAccountSession(String name, long id, long accountid);

    /**
     * Get stored session data.
     * 
     * @return Hashmap of the current user's name and id
     * */
    public HashMap<String, String> getUserDetails();

    /**
     * Get session user id.
     * 
     * @return the ID of the current user
     */
    public long getUserID();
    
    /**
     * Get current account id.
     * @return the ID of the current account
     */
    public long getAccountID();

    /**
     * Check login method will check user login status If false it will redirect
     * user to login page Else won't do anything.
     * */
    public void checkLogin();

    /**
     * Removes account from session.
     */
    public void removeAccount();

    /**
     * Clear session details.
     * */
    public void logoutUser();
    
    /**
     * Quick check for login.
     * 
     * @return true if someone is logged in
     */
    public boolean isLoggedIn();
}
