package com.appzoy.trendonairs.view;


import com.example.demomallapp.R;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingActivity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class Admin extends SlidingActivity implements View.OnTouchListener{
	private ImageView mImageView;
	private ViewGroup mRrootLayout;
	private int _xDelta;
	private int _yDelta;
	float deltaXnew=0;
	float deltaYnew=0;
	static SlidingMenu menuS;
	static DisplayMetrics metrics;
	static int width = 0;
	Dialog dialog;  
	Button yes,no;
	ImageView action_person;
	ImageView action_personss;
	LinearLayout settings,settings0,settings1;
	private void setUpSlidingMenu() {
		setBehindContentView(R.layout.mainactivitydemo);
		menuS = getSlidingMenu();
		 menuS.setFadeDegree(0.35f);
		menuS.setShadowWidthRes(R.dimen.slidingmenuWidth);
		menuS.setBehindOffsetRes(R.dimen.slidingmenuOffset);
		menuS.setFadeDegree(0.35f);
		menuS.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.adminhome);
		metrics = getResources().getDisplayMetrics();
		width = metrics.widthPixels;
		setUpSlidingMenu();
		mRrootLayout = (ViewGroup) findViewById(R.id.headerrel);
		mImageView = (ImageView) mRrootLayout.findViewById(R.id.temporaryimg);
		
		mImageView.setOnTouchListener(this);
		
		 dialog = new Dialog(this);
	        // Include dialog.xml file
			 dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
	        dialog.setContentView(R.layout.customdialog);
			yes = (Button) dialog.findViewById(R.id.yesshop);
			no = (Button) dialog.findViewById(R.id.noshop);
			
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
					mImageView.setImageResource(R.drawable.tagadminpermanent);
					mImageView.setOnTouchListener(null);
					
				}
			});
			
			action_person = (ImageView) findViewById(R.id.action_person);
			action_person.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent i = new Intent(Admin.this,Profile.class);
					startActivity(i);
				}
			});
			action_personss = (ImageView) findViewById(R.id.action_personss);
			action_personss.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					toggle();
				}
			});
			 settings = (LinearLayout) menuS.findViewById(R.id.settings);
			    settings0 = (LinearLayout) menuS.findViewById(R.id.settings0);
			    settings1 = (LinearLayout) menuS.findViewById(R.id.settings1);

			    settings.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Intent i = new Intent(Admin.this,Settings.class);
						startActivity(i);
						toggle();
					}
				});
	}

	public boolean onTouch(View view, MotionEvent event) {
		
		Log.e("x",""+event.getRawX());
		Float a,b;
		
		final int X = (int) event.getRawX();
		final int Y = (int) event.getRawY();
		switch (event.getAction() & MotionEvent.ACTION_MASK) {
		
		
		
		case MotionEvent.ACTION_DOWN:

			RelativeLayout.LayoutParams lParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
			_xDelta = X - lParams.leftMargin;
			_yDelta = Y - lParams.topMargin;
			Log.e("ACTION_DOWN",""+_xDelta);
			
			a = deltaXnew - event.getRawX();
			b = deltaYnew - event.getRawY();
			
			
			
			if((Math.abs(a)>10)&&(Math.abs(b)>10)){
				Log.e("sss","sss");
			}else{
				ping();	
			}
			
			return true;
			
		case MotionEvent.ACTION_UP:
			Log.e("ACTION_Up",""+_xDelta);
			 
	             
	            float deltaX = Math.abs(event.getX() - _xDelta);
	            float deltaY = Math.abs(event.getY() - _yDelta);
	            Log.d("intercept", "up"+deltaX+deltaY);
	            if(deltaX<25 && deltaY<25 ){
	            	Log.d("intercept", "up");
	                return false;
	            }
			 
			
		case MotionEvent.ACTION_POINTER_DOWN:
			a = deltaXnew - event.getRawX();
			b = deltaYnew - event.getRawY();
			
			
			
			if((Math.abs(a)>10)&&(Math.abs(b)>10)){
				Log.e("sss","sss");
			}else{
				ping();	
			}
			Log.e("ACTION_POINTER_DOWN",""+_xDelta);
			
			onShowDialog();
			
			return true;
			
		case MotionEvent.ACTION_POINTER_UP:
			Log.e("ACTION_POINTER_UP",""+_xDelta);
			a = deltaXnew - event.getRawX();
			b = deltaYnew - event.getRawY();
			
			
			
			if((Math.abs(a)>10)&&(Math.abs(b)>10)){
				Log.e("sss","sss");
			}else{
				ping();	
			}
			return true;
			
		case MotionEvent.ACTION_MOVE:
			RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view
					.getLayoutParams();
			layoutParams.leftMargin = X - _xDelta;
			layoutParams.topMargin = Y - _yDelta;
			layoutParams.rightMargin = -250;
			layoutParams.bottomMargin = -250;
			view.setLayoutParams(layoutParams);
			Log.e("ACTION_MOVE",""+_xDelta);
			
			a = deltaXnew - event.getRawX();
			b = deltaYnew - event.getRawY();
			
			
			
			if((Math.abs(a)>10)&&(Math.abs(b)>10)){
				Log.e("sss","sss");
			}else{
				//ping();	
			}
			
			return true;
			
		}
		
		a = deltaXnew - event.getRawX();
		b = deltaYnew - event.getRawY();
		
		
		
		if((Math.abs(a)>10)&&(Math.abs(b)>10)){
			Log.e("sss","sss");
		}else{
			ping();	
		}
		
		deltaXnew = event.getRawX();
		deltaYnew =	event.getRawY();	
		
		mRrootLayout.invalidate();
		
		return false;
	}




	private void onShowDialog() {
		// TODO Auto-generated method stub
		dialog.show();
	}

	private void ping() {
		// TODO Auto-generated method stub
		Log.e("Ping",""+_xDelta);
		
	}
	
}
