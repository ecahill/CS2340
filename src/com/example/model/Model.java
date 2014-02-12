package com.example.model;

import java.util.Collection;

public interface Model {
	boolean isUser(final String name);
	User getUserByName(final String name);
	Collection<User> getUsers();
}
