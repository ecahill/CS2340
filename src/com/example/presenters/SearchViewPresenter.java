package com.example.presenters;

import com.example.cs2340.R;
import com.example.model.Model;
import com.example.model.User;
import com.example.views.ClickListener;
import com.example.views.UserSearchView;

public class SearchViewPresenter{// implements ClickListener {
	
	private UserSearchView view;
	private Model model;
	
	public SearchViewPresenter(UserSearchView v, Model m) {
		view = v;
		model = m;
		//view.addSearchRequestNotifyCallback(this);
	}

	/**
	 * Handle the search button press in the view
	 */
	//@Override
	public boolean onClick() {
		String name = view.getName();
		User user = model.getUserByName(name);
		return user!=null;
		

	}
	
	public boolean isUser(String username, String password){
		return model.isUser(username, password);
	}

}
