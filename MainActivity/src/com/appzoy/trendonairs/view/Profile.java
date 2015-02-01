package com.appzoy.trendonairs.view;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.demomallapp.R;

public class Profile extends Activity{
	CheckBox tb;
	ImageView action_personss;
	TextView Nicknametvagain1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.profiledemo);
		
		SharedPreferences sp11 = getSharedPreferences(
				"admin", Activity.MODE_PRIVATE);
		String highScore11 = sp11.getString("a","user");
		Nicknametvagain1 = (TextView) findViewById(R.id.Nicknametvagain1s);
		
		
		action_personss = (ImageView) findViewById(R.id.action_personss) ;
		tb = (CheckBox) findViewById(R.id.check1);
		
//		if(highScore11.equalsIgnoreCase("user")){
//			tb.setVisibility(View.INVISIBLE);
//			
//			Nicknametvagain1.setVisibility(View.INVISIBLE);
//		}else{
//			tb.setText("   Disabled");
//			tb.setVisibility(View.VISIBLE);
//			Nicknametvagain1.setVisibility(View.VISIBLE);
//		}
		
		SharedPreferences sp1 = getSharedPreferences(
				"mode", Activity.MODE_PRIVATE);
		Boolean highScore1 = sp1.getBoolean("a",
				false);
		tb.setChecked(highScore1);
		tb.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				SharedPreferences sp = getSharedPreferences(
						"mode", Activity.MODE_PRIVATE);
				SharedPreferences.Editor editor = sp.edit();
				editor.putBoolean("a", tb.isChecked());
				editor.commit();
				if(tb.isChecked()){
					tb.setText("   Enabled");
				}else{
					tb.setText("   Disabled");
				}
			}
		});
		action_personss.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(!tb.isChecked()){
					Intent i = new Intent(Profile.this,HomeTestActivity1.class);
					startActivity(i);
				}else{
					Intent i = new Intent(Profile.this,Admin.class);
					startActivity(i);
				}
			}
		});
		
	}
	
	
}
