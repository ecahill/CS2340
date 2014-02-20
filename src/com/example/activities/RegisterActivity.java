package com.example.activities;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import com.example.model.User;
import com.example.model.DatabaseHandler;
import com.example.cs2340.*;

 
public class RegisterActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.main); // ****need to change this to register screen
         
        DatabaseHandler db = new DatabaseHandler(this);
        
        //CRUD (create read update delete) operations
        
        Log.d("Insert: ", "Inserting...");
        db.addUser(new User("admin", "pass123"));
        db.addUser(new User("admin2", "pass2"));
        db.addUser(new User("me", "mypass"));
        
        Log.d("Reading: ", "Reading all contacts...");
        List<User> users = db.getAllUsers();
        
        for (User u : users){
        	String log = "ID: "+u.getID()+" ,Username: "+u.getUsername()+" ,Password: "+u.getPassword();	
        	Log.d("Name: ", log);
        }
    }
}
    