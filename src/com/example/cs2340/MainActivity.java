package com.example.cs2340;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.content.Intent; 

public class MainActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button loginButton = (Button)this.findViewById(R.id.button1);
		loginButton.setOnClickListener(listener); // Add event listener to button
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	final OnClickListener listener = new OnClickListener() // button listener to change screen
	{
		public void onClick(View v){
			setContentView(R.layout.login_view); // change content view to login
		}
	};



}
