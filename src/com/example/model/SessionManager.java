package com.example.model;

import java.util.HashMap;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.example.activities.LoginActivity;
/**
 * A class to manage what user that is logged in and what account they are viewing.
 * 
 * @author Emily Cahill
 *
 */
public class SessionManager {
	/**
	 * @param Shared preferences
	 */
    private SharedPreferences pref;

    /**
     *@param Editor for shared preferences
     */
    private Editor editor;

    /**
     * @param Context
     */
    private Context context;

    /**
     * @param PRIVATE_MODE shared pref mode
     */
    private static final int PRIVATE_MODE = 0;

    /**
     * @param PREF_NAME Shared pref file name
     */
    private static final String PREF_NAME = "Pref";

    /**
     * @param IS_LOGIN Shared pref loggedIn key name
     */
    private static final String IS_LOGIN = "IsLoggedIn";

    /**
     * @param KEY_NAME Shared pref username key name
     */
    public static final String KEY_NAME = "name";

    /**
     * @param KEY_ID Shared pref user id key name
     */
    public static final String KEY_ID = "id";

    /**
     * @param KEY_ACCOUNT_ID Shared pref account id key name
     */
    public static final String KEY_ACCOUNT_ID = "account_id";

    /**
     * Constructor for SessionManager takes in the current application context.
     * 
     * @param c the current application context
     */
    public SessionManager(Context c) {
        this.context = c;
        pref = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    /**
     * Creates a login session.
     * 
     * @param name The username of the user logging in
     * @param id The id of the user logging in
     * */
    public void createLoginSession(String name, long id) {
        // Storing login value as TRUE
        editor.putBoolean(IS_LOGIN, true);

        // Storing name in pref
        editor.putString(KEY_NAME, name);

        // Storing id in pref
        editor.putLong(KEY_ID, id);

        // commit changes
        editor.commit();

        //Log.d("SessionManager", "Name: " + name + ", ID: " + id);
    }

    /**
     * Creates an account session.
     * 
     * @param name The username of the user logged in
     * @param id The user id of the user logged in
     * @param accountid The account id of the account that the user is using
     */

    public void createAccountSession(String name, long id, long accountid) {
        editor.putBoolean(IS_LOGIN, true);
        editor.putString(KEY_NAME, name);
        editor.putLong(KEY_ID, id);
        editor.putLong(KEY_ACCOUNT_ID, accountid);
        editor.commit();
    }

    /**
     * Get stored session data.
     * 
     * @return Hashmap of the current user's name and id
     * */
    public HashMap<String, String> getUserDetails() {
        HashMap<String, String> user = new HashMap<String, String>();
        // user name
        user.put(KEY_NAME, pref.getString(KEY_NAME, null));

        // user id
        user.put(KEY_ID, pref.getString(KEY_ID, null));

        // return user
        return user;
    }

    /**
     * Get session user id.
     * 
     * @return the ID of the current user
     */
    public long getUserID() {
        return pref.getLong(KEY_ID, 0);
    }
    
    /**
     * Get current account id.
     * @return the ID of the current account
     */
    public long getAccountID() {
        return pref.getLong(KEY_ACCOUNT_ID, 0);
    }

    /**
     * Check login method will check user login status If false it will redirect
     * user to login page Else won't do anything.
     * */
    public void checkLogin() {
        // Check login status
        if (!this.isLoggedIn()) {
            // user is not logged in redirect him to Login Activity
            Intent i = new Intent(context, LoginActivity.class);
            // Closing all the Activities
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            // Add new Flag to start new Activity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            // Staring Login Activity
            context.startActivity(i);
        }
    }

    /**
     * Removes account from session.
     */
    public void removeAccount() {
        editor.remove(KEY_ACCOUNT_ID);
        editor.commit();
        // re add login session?
    }

    /**
     * Clear session details.
     * */
    public void logoutUser() {
        // Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();

        // After logout redirect user to Login Activity
        Intent i = new Intent(context, LoginActivity.class);
        // Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // Add new Flag to start new Activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        // Staring Login Activity
        context.startActivity(i);
    }

    /**
     * Quick check for login.
     * 
     * @return true if someone is logged in
     * **/
    // Get Login State
    public boolean isLoggedIn() {
        return pref.getBoolean(IS_LOGIN, false);
    }
}