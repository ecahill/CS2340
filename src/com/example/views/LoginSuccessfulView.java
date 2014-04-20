package com.example.views;

import android.app.Activity;
import android.os.Bundle;

import com.example.cs2340.R;

/**
 * Displays after the admin log in.
 * 
 * @author Erin Sapp
 *
 */
public class LoginSuccessfulView extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginsuccess_view);
    }

}
