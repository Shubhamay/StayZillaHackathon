package com.appzoy.trendonairs.view;


import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.demomallapp.R;

public class Settings extends Activity{
ImageView imgslide;
ProgressDialog progressdialog;
Dialog dialog;
Button yes,no;
EditText data;
TextView Nicknametvagain2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.settings);
		Nicknametvagain2= (TextView) findViewById(R.id.Nicknametvagain2);
		progressdialog = new ProgressDialog(this,R.style.MyTheme);
		progressdialog.setCanceledOnTouchOutside(false);
		progressdialog.setProgressStyle(android.R.style.Widget_ProgressBar_Small);

		 		dialog = new Dialog(this);
	        // Include dialog.xml file
			 dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
	        dialog.setContentView(R.layout.customdialogchangeurl);
			yes = (Button) dialog.findViewById(R.id.yeschangeurl);
			no = (Button) dialog.findViewById(R.id.nochangeurl);
			data = (EditText) dialog.findViewById(R.id.message);
			no.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					dialog.dismiss();
				}
			});
		
			yes.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					dialog.dismiss();
					Nicknametvagain2.setText(data.getText().toString());
				}
			});
			
		imgslide= (ImageView) findViewById(R.id.imgslide);
		imgslide.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dialog.show();
				
			}
		});
	}
	
}
