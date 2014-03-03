package com.example.activities;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.model.User;
import com.example.model.DatabaseHandler;
import com.example.cs2340.*;

 
public class RegisterActivity extends Activity {
	EditText username;
	EditText password;
	EditText cPassword;
	Button goButton;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_view); 
         
        final DatabaseHandler db = new DatabaseHandler(this);
        final Context context = this;
        
        username = (EditText)findViewById(R.id.NameField);
        password = (EditText)findViewById(R.id.PassField);
        cPassword = (EditText)findViewById(R.id.CPassField);
        goButton = (Button)findViewById(R.id.RegButton);
        goButton.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v){
				if (db.checkUsername(getName())){
					if (getPassword().equals(getCPassword())){
						long id = db.addUser(new User(getName(), getPassword()));
						User u = db.getUser(id);
						u.setID(id);
					}
					else{
						Toast.makeText(context, "Passwords do not match", Toast.LENGTH_LONG).show();
					}
					Toast.makeText(context, "User has been registered", Toast.LENGTH_LONG).show();
				}
				else{
					Toast.makeText(context, "Username is taken", Toast.LENGTH_LONG).show();
				}
			}
        });
        
    }
        
	public String getName() {
		return username.getText().toString();
	}
	
	public String getPassword(){
		return password.getText().toString();
	}
	
	public String getCPassword(){
		return cPassword.getText().toString();
	}
}
    