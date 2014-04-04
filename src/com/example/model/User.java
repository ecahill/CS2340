package com.example.model;

/**
 * Model for the User object
 * @author Emily Cahill
 * 
 */

public class User {
	/**
	 * @param _id The user id
	 */
    private long _id;
    /**
     * @param _username The user's username
     */
    private String _username;
    /**
     * @param _password The user's password
     */
    private String _password;

    public User() {

    }

    public User(long id, String u, String p) {
        this._id = id;
        this._username = u;
        this._password = p;
    }

    public User(String u, String p) {
        this._username = u;
        this._password = p;
    }
    /**
     * Gets the id of the user
     * @return the user id
     */
    public long getID() {
        return this._id;
    }
    /**
     * Sets the user id of the user
     * @param id The new user id
     */

    public void setID(long id) {
        this._id = id;
    }
    /**
     * Gets the username of the user
     * @return the username
     */
    public String getUsername() {
        return this._username;
    }
    /**
     * Sets the username of the user
     * @param u the new username
     */
    public void setUsername(String u) {
        this._username = u;
    }
    /**
     * Gets the password of the user
     * @return the user's password
     */
    public String getPassword() {
        return this._password;
    }
    /**
     * sets the password of the user
     * @param p the new password
     */
    public void setPassword(String p) {
        this._password = p;
    }
}
