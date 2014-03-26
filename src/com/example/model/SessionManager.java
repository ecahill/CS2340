package com.example.model;

import java.util.HashMap;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;

import com.example.activities.LoginActivity;
 
public class SessionManager {
    // Shared Preferences
    private SharedPreferences pref;
     
    // Editor for Shared preferences
    Editor editor;
     
    // Context
    Context _context;
     
    // Shared pref mode
    int PRIVATE_MODE = 0;
     
    // Sharedpref file name
    private static final String PREF_NAME = "Pref";
     
    // All Shared Preferences Keys
    private static final String IS_LOGIN = "IsLoggedIn";
     
    // User name (make variable public to access from outside)
    public static final String KEY_NAME = "name";
     
    // User id (make variable public to access from outside)
    public static final String KEY_ID = "id";
    
    // Account id
    public static final String KEY_ACCOUNT_ID = "account_id";
     
    // Constructor
    public SessionManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }
    
    /**
     * Create login session
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
        
        Log.d("SessionManager", "Name: "+name+", ID: "+id);
    }
    
    /**
     * Create account session
     */
    
    public void createAccountSession(String name, long id, long accountid) {
        editor.putBoolean(IS_LOGIN, true);
        editor.putString(KEY_NAME, name);
        editor.putLong(KEY_ID, id);
    	editor.putLong(KEY_ACCOUNT_ID, accountid);
    	editor.commit();
    }
    
    /**
     * Get stored session data
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
     * Get session user id
     */
    public long getUserID() {
    	return pref.getLong(KEY_ID, 0);
    }
    
    public long getAccountID() {
    	return pref.getLong(KEY_ACCOUNT_ID, 0);
    }
    
    /**
     * Check login method wil check user login status
     * If false it will redirect user to login page
     * Else won't do anything
     * */
    public void checkLogin() {
        // Check login status
        if(!this.isLoggedIn()){
            // user is not logged in redirect him to Login Activity
            Intent i = new Intent(_context, LoginActivity.class);
            // Closing all the Activities
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
             
            // Add new Flag to start new Activity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
             
            // Staring Login Activity
            _context.startActivity(i);
        }      
    }
    
    public void removeAccount() {
    	editor.remove(KEY_ACCOUNT_ID);
    	editor.commit();
    	//re add login session?
    }
    
    /**
     * Clear session details
     * */
    public void logoutUser() {
        // Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();
         
        // After logout redirect user to Login Activity
        Intent i = new Intent(_context, LoginActivity.class);
        // Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
         
        // Add new Flag to start new Activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
         
        // Staring Login Activity
        _context.startActivity(i);
    }
     
    /**
     * Quick check for login
     * **/
    // Get Login State
    public boolean isLoggedIn() {
        return pref.getBoolean(IS_LOGIN, false);
    }    
}