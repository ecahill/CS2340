package com.example.presenters;

import com.example.cs2340.R;
import com.example.model.Model;
import com.example.model.User;
import com.example.views.ClickListener;
import com.example.views.UserSearchView;

public class SearchViewPresenter implements ClickListener {
	
	private UserSearchView view;
	private Model model;
	
	public SearchViewPresenter(UserSearchView v, Model m) {
		view = v;
		model = m;
		view.addSearchRequestNotifyCallback(this);
	}

	/**
	 * Handle the search button press in the view
	 */
	@Override
	public void onClick() {
		String name = view.getSearchName();
		User user = model.getUserByName(name);
		String text = (user == null) ?  "Login failed" : user.toString();
		view.setResultData(text);
		

	}
	
	public boolean isUser(String name){
		return model.isUser(name);
	}

}
