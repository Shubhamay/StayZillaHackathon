package com.appzoy.trendonairs.view;


import com.example.demomallapp.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity1 extends Activity
{
	Button regButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_activity1demo);
		
		getActionBar().hide();
		
		regButton = (Button) findViewById(R.id.register2_button);
		
		regButton.setOnClickListener(new OnClickListener()
		{	
			@Override
			public void onClick(View v)
			{
				Intent intent = new Intent(MainActivity1.this, HomeTestActivity1.class);
				startActivity(intent);
			}
		});
	}

	

}
