package com.appzoy.trendonairs.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.demomallapp.R;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingActivity;


public class HomeTestActivity1 extends SlidingActivity  {
	
	private View header;
	private int headerHeight, baseScrollHeight, lowerHeaderHeight;
	private RelativeLayout floatingBarHeader;
	private Dictionary<Integer, Integer> listViewItemHeights;
	private static boolean setScrollHeight, offsetSet;
	private  Button btnmainsearchsbtn;
	//ImageView btnmainsearchsbtns;
	private  Button btnmainsearch1;
	private  RelativeLayout relativeLayoutsearchmain;
	ScrollView relative;
	CountDownTimer waitTimer;
	private  ListView actuallist;
	ImageView discounttag;
	private LazyImageLoadAdapter_Home adapter1;
	TextView email_err_tv;
	Button galiv;
	ImageView action_person;
	String errordialogHeader;
	String errordialogBottom;
	TextView wrong_email_tv;
	RelativeLayout rl_sc; 
	static SlidingMenu menuS;
	static int width = 0;
	static DisplayMetrics metrics;
	ImageView action_personss;
	LinearLayout settings,settings0,settings1;
	int p=0;
	NetworkCheck networkCheck;
	ArrayList<String> displayName = new ArrayList<String>();
	ArrayList<String> price = new ArrayList<String>();
	ArrayList<String> imageUrl = new ArrayList<String>();
	ArrayList<String> Gallery = new ArrayList<String>();
	ArrayList<String> starRating = new ArrayList<String>();
	ArrayList<String> resturant = new ArrayList<String>();
	ArrayList<String> bar = new ArrayList<String>();
	ArrayList<String> coffe = new ArrayList<String>();
	ArrayList<String> biz = new ArrayList<String>();
	ArrayList<String> swim = new ArrayList<String>();
	ArrayList<String> internet = new ArrayList<String>();
	ArrayList<String> creditCard = new ArrayList<String>();
	ArrayList<String> laundary = new ArrayList<String>();
	ArrayList<String> pickUpandDrop = new ArrayList<String>();
	ArrayList<String> healthClub = new ArrayList<String>();
	ArrayList<String> freeNewspaper = new ArrayList<String>();
	ArrayList<String> elevator = new ArrayList<String>();
	ArrayList<String> pureveg = new ArrayList<String>();
	ArrayList<String> parking = new ArrayList<String>();
	ArrayList<String> twenty4HourCheckin = new ArrayList<String>();
	ArrayList<String> twenty4HourCheckOut = new ArrayList<String>();
	Button search;
	EditText editText1;
	EditText editText2;
	EditText editText3;
	
	
	private void setUpSlidingMenu() {
		setBehindContentView(R.layout.mainactivitydemo);
		menuS = getSlidingMenu();
//		menuS.setMode(SlidingMenu.LEFT);
//		menuS.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
//		menuS.setBehindWidth((int) (width * 0.7));
		 menuS.setFadeDegree(0.35f);
		menuS.setShadowWidthRes(R.dimen.slidingmenuWidth);
		menuS.setBehindOffsetRes(R.dimen.slidingmenuOffset);
		menuS.setFadeDegree(0.35f);
		menuS.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
		//menuS.addView(child, width, height)
	}
	   
	@Override
	public void onCreate(Bundle savedInstanceState) {
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.homedemo);
		
		
		
		networkCheck = new NetworkCheck();
		metrics = getResources().getDisplayMetrics();
		width = metrics.widthPixels;
		setUpSlidingMenu();
		toggle();
		setScrollHeight = false;
		offsetSet = false;
		listViewItemHeights = new Hashtable<Integer, Integer>();
		rl_sc = (RelativeLayout) findViewById(R.id.rl_sc);
		actuallist = (ListView) findViewById(R.id.listView);
		btnmainsearch1 = (Button) findViewById(R.id.btnmainsearch1);
		relativeLayoutsearchmain = (RelativeLayout) findViewById(R.id.relativeLayoutsearchmain);
		    
		//SGD = new ScaleGestureDetector(this,new ScaleListener());
			//relativeLayoutsearchmain.set
		action_person = (ImageView) findViewById(R.id.action_person);
		action_person.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(HomeTestActivity1.this,Profile.class);
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
		// Include dialog.xml file
			    settings = (LinearLayout) menuS.findViewById(R.id.settings);
			    settings0 = (LinearLayout) menuS.findViewById(R.id.settings0);
			    settings1 = (LinearLayout) menuS.findViewById(R.id.settings1);

			    settings.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Intent i = new Intent(HomeTestActivity1.this,McDNearBy.class);
						startActivity(i);
						toggle();
					}
				});
			    
		header = header();
		actuallist.addHeaderView(header, null, false);
		
		search = (Button) header.findViewById(R.id.search);
		editText1 = (EditText) header.findViewById(R.id.editText1);
		editText2 = (EditText) header.findViewById(R.id.editText2);
		editText3 = (EditText) header.findViewById(R.id.editText3);
		Calendar c = Calendar.getInstance();
		
		final Calendar myCalendar = Calendar.getInstance();

		final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

		    @Override
		    public void onDateSet(DatePicker view, int year, int monthOfYear,
		            int dayOfMonth) {
		        // TODO Auto-generated method stub
//		        myCalendar.set(Calendar.YEAR, year);
//		        myCalendar.set(Calendar.MONTH, monthOfYear);
//		        myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
		    	//06/02/2015
		    	monthOfYear = monthOfYear + 1;
		       editText2.setText(""+dayOfMonth+"/"+monthOfYear+"/"+year);
		    }

		};
		final DatePickerDialog.OnDateSetListener date1 = new DatePickerDialog.OnDateSetListener() {

		    @Override
		    public void onDateSet(DatePicker view, int year, int monthOfYear,
		            int dayOfMonth) {
		        // TODO Auto-generated method stub
//		        myCalendar.set(Calendar.YEAR, year);
//		        myCalendar.set(Calendar.MONTH, monthOfYear);
//		        myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
		    	monthOfYear = monthOfYear + 1;
		    	 editText3.setText(""+dayOfMonth+"/"+monthOfYear+"/"+year);
		    }

		};
		search.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new UploadReceipt(HomeTestActivity1.this).execute();
			}
		});
		editText2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new DatePickerDialog(HomeTestActivity1.this, date, myCalendar
	                    .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
	                    myCalendar.get(Calendar.DAY_OF_MONTH)).show();
			}

			 
		});
		editText3.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		new DatePickerDialog(HomeTestActivity1.this, date1, myCalendar
                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show();
	}
		});
		
		adapter1 = new LazyImageLoadAdapter_Home(HomeTestActivity1.this,displayName,price,imageUrl,
				starRating,resturant,bar,coffe,biz,swim,internet,creditCard,laundary,pickUpandDrop,
				healthClub,freeNewspaper,elevator,pureveg,parking,twenty4HourCheckin,twenty4HourCheckOut,0);
		actuallist.setAdapter(adapter1);
		
		new UploadReceipt(HomeTestActivity1.this).execute();
		
		floatingBarHeader = (RelativeLayout) findViewById(R.id.progBarFloat);
		
		setOffset();
		placeFloatingViewWhenReady();
		
		ViewTreeObserver vto = actuallist.getViewTreeObserver();
		vto.addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
			@Override
			public void onGlobalLayout() {
				if (!setScrollHeight) {
					baseScrollHeight = getScroll();
					setScrollHeight = true;
				}

			}
		});

		
//		  waitTimer = new CountDownTimer(1000, 300) {
//
//		       public void onTick(long millisUntilFinished) {
//		          //called every 300 milliseconds, which could be used to
//		          //send messages or some other action
//		       }
//
//		       public void onFinish() {
//		          //After 60000 milliseconds (60 sec) finish current 
//		          //if you would like to execute something when time finishes 
//		    	   
//		    	   //discounttag.setVisibility(View.VISIBLE);
//		    	   
//		    	  
//		    	   
//		    	   if(actuallist.getLastVisiblePosition()==1 || actuallist.getLastVisiblePosition()==2){
//		    	   if(p==0){
//		    		   p=1;
//		    	   adapter1.updateAdapter(list,p);
//		    	   }
//		    	   else if(p==1){
//		    		   p=2;
//		    	   adapter1.updateAdapter(list,p);
//		    	   }
//		    	   else if(p==2){
//		    		   p=0;
//		    	   adapter1.updateAdapter(list,p);
//		    	   }
//		    	   }
//		    	   
//		    	   waitTimer.start();
//		       }
//		     }.start();
		     
		     actuallist.setOnScrollListener(new OnScrollListener() {

					@Override
					public void onScrollStateChanged(AbsListView view, int scrollState) {
						// TODO Auto-generated method stub

						
						
					}
					@Override
					public void onScroll(AbsListView view, int firstVisibleItem,
							int visibleItemCount, int totalItemCount) {
				
						placeFloatingView();
						//discounttag.setVisibility(View.INVISIBLE);
						//btnmainsearchsbtns.setVisibility(View.INVISIBLE);
						
						//waitTimer.start();

					}

				});
	}




			public void initError(){
			
		}
		private void placeFloatingView() {
			if (getScroll() < headerHeight + baseScrollHeight) {

				floatingBarHeader.setTop(-1 * getScroll() + baseScrollHeight);
				btnmainsearch1.setVisibility(View.INVISIBLE);
				relativeLayoutsearchmain.setVisibility(View.INVISIBLE);
				if (floatingBarHeader.getTop() <= -170)
				{
				btnmainsearch1.setVisibility(View.INVISIBLE);
				relativeLayoutsearchmain.setVisibility(View.INVISIBLE);
				}
				
			} else {
				floatingBarHeader.setTop(-1 * headerHeight);
				btnmainsearch1.setVisibility(View.VISIBLE);
				relativeLayoutsearchmain.setVisibility(View.VISIBLE);
			}


		}

		private int getScroll() {
			int scrollY = 0;
			btnmainsearchsbtn = (Button) header
					.findViewById(R.id.btnmainsearchsbtn);

			View c = actuallist.getChildAt(0);
			if (c != null) {
				scrollY = -c.getTop();
				listViewItemHeights.put(actuallist.getFirstVisiblePosition(),
						c.getHeight());

				for (int i = 0; i < actuallist.getFirstVisiblePosition(); ++i) {
					if (btnmainsearchsbtn.getVisibility() == View.VISIBLE) {
					
						btnmainsearch1.setVisibility(View.VISIBLE);

						relativeLayoutsearchmain.setVisibility(View.VISIBLE);
						

					} else if (btnmainsearchsbtn.getVisibility() == View.INVISIBLE) {
						btnmainsearch1.setVisibility(View.VISIBLE);
						relativeLayoutsearchmain.setVisibility(View.VISIBLE);
						

					} else {
						btnmainsearch1.setVisibility(View.VISIBLE);
						relativeLayoutsearchmain.setVisibility(View.VISIBLE);
						

					}
					if (listViewItemHeights.get(i) != null)
						scrollY += listViewItemHeights.get(i);
					

				}
			}
			return scrollY;
		}

		private void placeFloatingViewWhenReady() {
			View v = findViewById(R.id.progBarFloat);
			ViewTreeObserver vto = v.getViewTreeObserver();
			vto.addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
				@Override
				public void onGlobalLayout() {
					placeFloatingView();
				}
			});
		}

		private void setOffset() {

			final View v = findViewById(R.id.headerbottom);
			ViewTreeObserver vto = v.getViewTreeObserver();

			vto.addOnGlobalLayoutListener(new OnGlobalLayoutListener() {

				@Override
				public void onGlobalLayout() {

					lowerHeaderHeight = v.getMeasuredHeight();
					ViewTreeObserver vto1 = header.getViewTreeObserver();

					vto1.addOnGlobalLayoutListener(new OnGlobalLayoutListener() {

						@Override
						public void onGlobalLayout() {

							if (!offsetSet) {
								headerHeight = header.getMeasuredHeight()
										- lowerHeaderHeight;
								floatingBarHeader.setY(headerHeight);
								offsetSet = true;
							}

						}
					});
				}
			});

		}

		private View header() {
			View header = getLayoutInflater().inflate(R.layout.headerdemo, null);
			return header;
		}

		
		@Override
		protected void onDestroy() {
			// TODO Auto-generated method stub

			super.onDestroy();
		}
		
		static class UploadReceipt extends AsyncTask<Void, Void, String>
		{
			String json;
			 HttpResponse response;
			String sResponse;
			 StringBuffer sb=new StringBuffer();
			 WeakReference<HomeTestActivity1> weakReference;
			 HomeTestActivity1 mAct;
	      public UploadReceipt(HomeTestActivity1 splashActivity){
	          weakReference = new WeakReference<HomeTestActivity1>(splashActivity);
	             mAct = weakReference.get();    
	      }


				@Override
				 protected void onPreExecute() {
					super.onPreExecute();
					mAct.displayName.clear();
					mAct.price.clear();
					mAct.imageUrl.clear();
					mAct.Gallery.clear();
					mAct.starRating.clear();
					mAct.resturant.clear();
					mAct.bar.clear();
					mAct.coffe.clear();
					mAct.biz.clear();
					mAct.swim.clear();
					mAct.internet.clear();
					mAct.creditCard.clear();
					mAct.laundary.clear();
					mAct.pickUpandDrop.clear();
					mAct.healthClub.clear();
					mAct.freeNewspaper.clear();
					mAct.elevator.clear();
					mAct.pureveg.clear();
					mAct.parking.clear();
					mAct.twenty4HourCheckin.clear();
					mAct.twenty4HourCheckOut.clear();
					mAct.adapter1.updateAdapter(mAct.displayName, mAct.price, mAct.imageUrl, mAct.starRating, mAct.resturant, 
							mAct.bar, mAct.coffe, mAct.biz, mAct.swim, mAct.internet, mAct.creditCard, mAct.laundary, mAct.pickUpandDrop, 
							mAct.healthClub, mAct.freeNewspaper, mAct.elevator, mAct.pureveg, mAct.parking, mAct.twenty4HourCheckin, 
							mAct.twenty4HourCheckOut, 0);
				 }

				 @Override
				 protected void onPostExecute(String result) {
					 Log.e("Json Response",""+json);
					 JSONObject jsonObj;
					 
					try {
						jsonObj = new JSONObject(json);
						 JSONArray contacts = jsonObj.getJSONArray("hotels");
						   for (int i = 0; i < contacts.length(); i++) {
		                        JSONObject c = contacts.getJSONObject(i);
		                        
		                        mAct.displayName.add(c.getString("displayName"));
		                        mAct.price.add(c.getString("price"));
		                        mAct.imageUrl.add(c.getString("imageURL"));
		                        mAct.starRating.add(c.getString("starRating"));
		                        JSONObject amenities = c.getJSONObject("amenities");
		                        
		                        
		                        
		                        mAct.resturant.add(amenities.getString("restaurant"));
		                        mAct.bar.add(amenities.getString("bar"));
		                        mAct.coffe.add(amenities.getString("coffee"));
		                        mAct.biz.add(amenities.getString("biz"));
		                        mAct.swim.add(amenities.getString("swim"));
		                        mAct.internet.add(amenities.getString("internet"));
		                        mAct.creditCard.add(amenities.getString("creditCard"));
		                        mAct.laundary.add(amenities.getString("laundry"));
		                        mAct.pickUpandDrop.add(amenities.getString("pickupAndDrop"));
		                        mAct.healthClub.add(amenities.getString("healthClub"));
		                        mAct.freeNewspaper.add(amenities.getString("freeNewspaper"));
		                        mAct.elevator.add(amenities.getString("elevator"));
		                        mAct.pureveg.add(amenities.getString("pureVeg"));
		                        mAct.parking.add(amenities.getString("parking"));
		                        mAct.twenty4HourCheckin.add(amenities.getString("twenty4HourCheckIn"));
		                        mAct.twenty4HourCheckOut.add(amenities.getString("twenty4HourCheckOut"));
		                        
//		                        String path="";
//		                        JSONArray Gallery = c.getJSONArray("gallery");
//		                        for(int ik=0;ik<Gallery.length();ik++){
//		                        	path = path + "," +Gallery.getString(ik);
//		                        }
//		                        
//		                        Log.e("Path",""+path);
						   }
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	                 
					mAct.adapter1.updateAdapter(mAct.displayName, mAct.price, mAct.imageUrl, mAct.starRating, mAct.resturant, 
							mAct.bar, mAct.coffe, mAct.biz, mAct.swim, mAct.internet, mAct.creditCard, mAct.laundary, mAct.pickUpandDrop, 
							mAct.healthClub, mAct.freeNewspaper, mAct.elevator, mAct.pureveg, mAct.parking, mAct.twenty4HourCheckin, 
							mAct.twenty4HourCheckOut, 0);
					
					mAct.actuallist.setOnItemClickListener(new OnItemClickListener() {

						@Override
						public void onItemClick(AdapterView<?> parent,
								View view, int position, long id) {
							// TODO Auto-generated method stub
							
							//Toast.makeText(mAct, "s", Toast.LENGTH_LONG).show();
							
							Intent i = new Intent(mAct,Detail.class);
							i.putExtra("displayName", mAct.displayName.get(position));
							i.putExtra("price", mAct.price.get(position));
							i.putExtra("imageUrl", mAct.imageUrl.get(position));
							i.putExtra("starRating", mAct.starRating.get(position));
							i.putExtra("resturant", mAct.resturant.get(position));
							i.putExtra("bar", mAct.bar.get(position));
							i.putExtra("coffe", mAct.coffe.get(position));
							i.putExtra("biz", mAct.biz.get(position));
							i.putExtra("swim", mAct.swim.get(position));
							i.putExtra("internet", mAct.internet.get(position));
							i.putExtra("creditCard", mAct.creditCard.get(position));
							i.putExtra("laundary", mAct.laundary.get(position));
							i.putExtra("pickUpandDrop", mAct.pickUpandDrop.get(position));
							i.putExtra("healthClub", mAct.healthClub.get(position));
							i.putExtra("freeNewspaper", mAct.freeNewspaper.get(position));
							i.putExtra("elevator", mAct.elevator.get(position));
							i.putExtra("pureveg", mAct.pureveg.get(position));
							i.putExtra("parking", mAct.parking.get(position));
							i.putExtra("twenty4HourCheckin",mAct.twenty4HourCheckin.get(position));
							i.putExtra("twenty4HourCheckOut", mAct.twenty4HourCheckOut.get(position));
							mAct.startActivity(i);
						}
					});
				 }	

				@Override
				protected String doInBackground(Void... params) {
				
					if(mAct.networkCheck.isNetworkAvailable(mAct))
					  {

					Log.e("responseBody","responseBody");
						
						HttpClient httpclient = new DefaultHttpClient();
						HttpPost httppost = new HttpPost("http://180.92.168.7/hotels");

						try {
						    // Add your data
							Log.e("location", ""+mAct.editText1.getText().toString());
							Log.e("checkin", ""+mAct.editText2.getText().toString());
							Log.e("checkout", ""+mAct.editText3.getText().toString());
							
						    List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
						    if(mAct.editText1.getText().toString().length()>0){
						    nameValuePairs.add(new BasicNameValuePair("location", ""+mAct.editText1.getText().toString()));
						    nameValuePairs.add(new BasicNameValuePair("checkin", ""+mAct.editText2.getText().toString()));
						    nameValuePairs.add(new BasicNameValuePair("checkout", ""+mAct.editText3.getText().toString()));
						    }else{
						    	SharedPreferences preferences = mAct.getSharedPreferences("stayzilla",MODE_PRIVATE);
			                    String loginx = preferences.getString("Place", "false");
			                    nameValuePairs.add(new BasicNameValuePair("location", ""+loginx));
							    nameValuePairs.add(new BasicNameValuePair("checkin", "02/02/2015"));
							    nameValuePairs.add(new BasicNameValuePair("checkout", "12/02/2015"));
						    }
						    nameValuePairs.add(new BasicNameValuePair("property_type", "Hotels"));
					
						    httppost.setHeader("Content-Type",
				                    "application/x-www-form-urlencoded;charset=UTF-8");
				            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
				            
						    // Execute HTTP Post Request
						   response = httpclient.execute(httppost);
						   HttpEntity httpEntity = response.getEntity();
					        InputStream is = httpEntity.getContent();
					        BufferedReader reader = new BufferedReader(new InputStreamReader(
					                is, "iso-8859-1"), 8);
					        StringBuilder sb = new StringBuilder();
					        String line = null;
					        while ((line = reader.readLine()) != null) {
					            sb.append(line + "n");
					        }
					        is.close();
					        json = sb.toString();
						    Log.e("Response"+response,"Response"+response );
						} catch (ClientProtocolException e) {
						    // TODO Auto-generated catch block
							Log.e("Response","Response"+e.getMessage());
						} catch (IOException e) {
						    // TODO Auto-generated catch block
							Log.e("Response","Response"+e.getMessage());
						}
					  }
					return json; 
				}


				@Override
				protected void onCancelled() {
					// TODO Auto-generated method stub
					super.onCancelled();
					Log.e("Cancelled","Cancelled");
				}
				 
				
				
			  }
		
}
