package com.example.activities;

import com.example.cs2340.R;
import com.example.cs2340.R.id;
import com.example.cs2340.R.layout;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;

public class MainActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		final Context context = this; 
		Button loginButton = (Button)this.findViewById(R.id.button1);
		loginButton.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v){
				Intent i = new Intent(MainActivity.this, LoginActivity.class);
				startActivity(i);
				}
		}); // Add event listener to button
	}

//	@Override
	/*public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}*/

	/*final OnClickListener listener = new OnClickListener() // button listener to change screen
	{
		public void onClick(View v){
			//Intent i = new Intent(getApplicationContext(), LoginActivity.class);
			//startActivity(i);
			setContentView(R.layout.login_view); // change content view to login
		}
	};*/



}
