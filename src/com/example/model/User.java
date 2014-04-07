package com.example.model;

/**
 * Model for the User object.
 * 
 * @author Emily Cahill
 * 
 */

public class User {
	
	/**
	 * @param id The user id
	 */
    private long id;
    /**
     * @param username The user's username.
     */
    private String username;
    /**
     * @param password The user's password.
     */
    private String password;

    /**
     * Default constructor for User.
     */
    public User() {

    }

    /**
     * Constructor for User.
     * 
     * @param aID the user ID
     * @param u the username
     * @param p the password
     */
    public User(long aID, String u, String p) {
        this.id = aID;
        this.username = u;
        this.password = p;
    }

    /**
     * Constructor for User.
     * 
     * @param u the username
     * @param p the password
     */
    public User(String u, String p) {
        this.username = u;
        this.password = p;
    }
    
    /**
     * Gets the id of the user.
     * 
     * @return the user id
     */
    public long getID() {
        return this.id;
    }
    
    /**
     * Sets the user id of the user.
     * 
     * @param aID The new user id
     */

    public void setID(long aID) {
        this.id = aID;
    }
    
    /**
     * Gets the username of the user.
     * 
     * @return the username
     */
    public String getUsername() {
        return this.username;
    }
    
    /**
     * Sets the username of the user.
     * 
     * @param u the new username
     */
    public void setUsername(String u) {
        this.username = u;
    }
    
    /**
     * Gets the password of the user.
     * 
     * @return the user's password
     */
    public String getPassword() {
        return this.password;
    }
    
    /**
     * sets the password of the user.
     * 
     * @param p the new password
     */
    public void setPassword(String p) {
        this.password = p;
    }
}
