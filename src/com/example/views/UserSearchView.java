package com.example.views;

import com.example.views.ClickListener;

public interface UserSearchView {
	/**
	 * This is our interface to the view.  Enables loose coupling so that the presenter does not
	 * need to know about any other information.
	 * 
	 * @author Emily
	 *
	 */
		String getName();
		//void setResultData(String text);
		//void addSearchRequestNotifyCallback(ClickListener listener);
	
}
