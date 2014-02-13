package com.example.cs2340;

import com.example.cs2340.R;

import com.example.model.MemoryModel;
import com.example.presenters.SearchViewPresenter;
import com.example.views.ClickListener;
import com.example.views.UserSearchView;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.view.View.OnClickListener;

public class LoginActivity extends Activity implements  UserSearchView {
	
	//private ClickListener listener;
	SearchViewPresenter presenter;
    EditText nameField;
    EditText password;
    EditText resultField;
    //Button goButton;
    //EditText resultField;
    
    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_view);
		final Context context = this;
		presenter = new SearchViewPresenter(this, new MemoryModel());
		
		nameField = (EditText) findViewById(R.id.editText1);
		password = (EditText) findViewById(R.id.editText2);
		resultField = (EditText) findViewById(R.id.editText3);
		Button goButton = (Button) this.findViewById(R.id.button1);
		goButton.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v){
				if (presenter.isUser(getName())){
					setContentView(R.layout.loginsuccess_view);
				}
				else{
					resultField.setText("Login Failed.");
				}
				//Intent i = new Intent(context, LoginActivity.class);
				//startActivity(i);
				}
		}); // Add event listener to button
		
		//resultField = (EditText) findViewById(R.id.editText2);
	}
	
	
	/*public void onSearchClick(View v) {
		listener.onClick();
		setContentView(R.layout.loginsuccess_view);
	}*/

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	//@Override
	public String getName() {
		return nameField.getText().toString();
	}
	
	public String getPassword(){
		return password.getText().toString();
	}
	
	/*@Override
	public void setResultData(final String text) {
		resultField.setText(text);		
	}
	
	@Override
	public void addSearchRequestNotifyCallback(final ClickListener lsnr) {
		listener = lsnr;		
	}*/


	
}
