package com.example.activities;

import com.example.views.ClickListener;
import com.example.views.DesignView;

import android.app.Activity;
import android.view.View;

public class DesignActivity extends Activity implements DesignView {

	private ClickListener aListener;
	
	@Override
	public void linkNotifyCallback(ClickListener listener) {
		aListener = listener;		
	}

	@Override
	public void onButtonClick(View v) {
		aListener.onClick(v);		
	}

}
