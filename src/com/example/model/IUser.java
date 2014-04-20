package com.example.model;

public interface IUser {
	 /**
     * Gets the id of the user.
     * 
     * @return the user id
     */
    public long getID();
    
    /**
     * Sets the user id of the user.
     * 
     * @param aID The new user id
     */

    public void setID(long aID);
    
    /**
     * Gets the username of the user.
     * 
     * @return the username
     */
    public String getUsername();
    
    /**
     * Sets the username of the user.
     * 
     * @param u the new username
     */
    public void setUsername(String u);
    
    /**
     * Gets the password of the user.
     * 
     * @return the user's password
     */
    public String getPassword();
    
    /**
     * sets the password of the user.
     * 
     * @param p the new password
     */
    public void setPassword(String p);
}
