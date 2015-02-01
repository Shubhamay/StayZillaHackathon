package com.appzoy.trendonairs.view;

import com.example.demomallapp.R;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends Activity
{
	Button loginButton, regButton, forgotButton;
	Intent intent;
	EditText userName_editText;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_maindemo);
		getActionBar().hide();
		loginButton = (Button) findViewById(R.id.login_button);
		regButton = (Button) findViewById(R.id.register1_button);
		forgotButton = (Button) findViewById(R.id.forgotPswd_button);
		userName_editText =(EditText) findViewById(R.id.userName_editText);
		
		loginButton.setOnClickListener(new OnClickListener()
		{		
			@Override
			public void onClick(View arg0)
			{
				if(userName_editText.getText().toString().equalsIgnoreCase("admin")){
					intent = new Intent(MainActivity.this, Admin.class);
					SharedPreferences sp = getSharedPreferences(
							"admin", Activity.MODE_PRIVATE);
					SharedPreferences.Editor editor = sp.edit();
					editor.putString("a", "admin");
					editor.commit();

					startActivity(intent);	
				}else{
					SharedPreferences sp = getSharedPreferences(
							"admin", Activity.MODE_PRIVATE);
					SharedPreferences.Editor editor = sp.edit();
					editor.putString("a", "user");
					editor.commit();
	
				intent = new Intent(MainActivity.this, HomeTestActivity1.class);
				startActivity(intent);
				}
			}
		});
		
		regButton.setOnClickListener(new OnClickListener()
		{	
			@Override
			public void onClick(View v)
			{
				intent = new Intent(MainActivity.this, MainActivity1.class);
				startActivity(intent);
			}
		});
	}

	

}
