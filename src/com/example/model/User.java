package com.example.model;

public class User {
    private long _id;
    private String _username;
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

    public long getID() {
        return this._id;
    }

    public void setID(long id) {
        this._id = id;
    }

    public String getUsername() {
        return this._username;
    }

    public void setUsername(String u) {
        this._username = u;
    }

    public String getPassword() {
        return this._password;
    }

    public void setPassword(String p) {
        this._password = p;
    }
}
