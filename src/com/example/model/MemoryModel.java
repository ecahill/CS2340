package com.example.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import android.util.Log;



public class MemoryModel implements Model {
	
	/** the collection of users, keyed by name */
	private Map<String, User> users;
	
	/**
	 * Makes a new model
	 */
	public MemoryModel() {
		users = new HashMap<String, User>();
		users.put("admin", new User("admin", "pass123"));
	}

	@Override
	public boolean isUser(String username, String password) {
		User u = getUserByName(username);
		if (u!=null){
			return ((u.getUsername().equals(username))&&(u.getPassword().equals(password)));
		}
		return false;
		
	}

	@Override
	public User getUserByName(final String name) {
		User u = users.get(name);
		return u;
	}

	@Override
	public Collection<User> getUsers() {
		return users.values();
	}

}
