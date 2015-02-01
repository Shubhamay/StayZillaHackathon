package com.appzoy.trendonairs.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

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

import com.example.demomallapp.R;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;


public class McDNearBy extends Activity implements LocationListener{
	
	LocationManager proximityLocation;
	List<PendingIntent> piList;	
	final String TAG_RESULTS = "results";
	final String TAG_LOCATION = "location";
	final String TAG_GEOMETRY = "geometry";
	final String TAG_LAT = "lat";
	final String TAG_LNG = "lng";
	final String TAG_ICON= "icon";
	final String TAG_ID = "id";
	final String TAG_NAME = "name";
	final String TAG_OPENING_HOURS = "opening_hours";
	final String TAG_OPEN_NOW = "open_now";
	final String TAG_PRICE_LEVEL = "price_level";
	final String TAG_RATING = "rating";
	final String TAG_TYPES = "types";
	final String TAG_VICINITY = "vicinity";
	// contacts JSONArray
	JSONArray results = null;
	//Button b = (Button)findViewById(R.id.button1);
	boolean isNetworkEnabled = false;
	Location location = null;;
	boolean canGetLocation = false;
	static double latitude;
	static double longitude;
	boolean isGPSEnabled = false;
	LocationManager locationManager;
	TextView tv;
	static ListView lv;
	// flag for GPS status
	static ArrayList<HashMap<String, String>> resultList = new ArrayList<HashMap<String, String>>();
	DecimalFormat twoDForm = new DecimalFormat("#.##");
	List<Float> dist;
	HashMap<Float, String> names, vicinity;
	HashMap<Float, Float> destLat, destLong;
	NetworkCheck networkCheck;
	SimpleAdapter sAdapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.near_by);
		
		resultList.clear();
		networkCheck = new NetworkCheck();
		
		((Button)findViewById(R.id.proximityMcDButton)).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				setProximity();
			}
		});
		
		piList = new ArrayList<PendingIntent>();
		
		dist = new ArrayList<Float>();
		names = new HashMap<Float, String>();
		vicinity = new HashMap<Float, String>();
		destLat = new HashMap<Float, Float>();
		destLong = new HashMap<Float, Float>();
        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
		isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
		isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

			if (!isGPSEnabled && !isNetworkEnabled) {
				// no network provider is enabled
			} else {
				this.canGetLocation = true;
				if (isNetworkEnabled) {
					locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000*60*1, 10, this);
					Log.d("Network", "Network Enabled");
					if (locationManager != null) {
						
						location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
						if (location != null) {
							latitude = location.getLatitude();
							longitude = location.getLongitude();
						}
					}
				}
				// if GPS Enabled get lat/long using GPS Services
				if (isGPSEnabled) {
					if (location == null) {
						locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000*60*1,	10, this);
						Log.d("GPS", "GPS Enabled");
						if (locationManager != null) {
							location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
							if (location != null) {
								latitude = location.getLatitude();
								longitude = location.getLongitude();
							}
						}
					}
				}
			}
			
			
			new UploadReceipt(McDNearBy.this).execute();
			
				}

	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}
	
	
	public void setProximity(){
		proximityLocation = (LocationManager) getSystemService(LOCATION_SERVICE);
        if(resultList.size()!=0){
			for(int i=0; i<resultList.size(); i++){
				HashMap<String, String> mapRead = resultList.get(i);
				String name = mapRead.get("Name");
				String lati = mapRead.get("Lat");
				String lngi = mapRead.get("Lng");
				String vicinity = mapRead.get("Vicinity");
				
				Intent intent= new Intent("marketi.app.mcd.community.proximityalert");
		        intent.putExtra("mcd name", name);
		        intent.putExtra("vicinity", vicinity);
		        PendingIntent pi = PendingIntent.getBroadcast(getApplicationContext(), -1, intent, 0);
		        proximityLocation.addProximityAlert(Double.parseDouble(lati), Double.parseDouble(lngi), 20, -1, pi);
		        
		        Log.e("proximity " + i, name + "at" + vicinity);
		        
			}
			Toast.makeText(getApplicationContext(), "proximity set", Toast.LENGTH_SHORT).show();
        }
	}
	
	static class UploadReceipt extends AsyncTask<Void, Void, String>
	{
		String json;
		 HttpResponse response;
		String sResponse;
		 StringBuffer sb=new StringBuffer();
		 WeakReference<McDNearBy> weakReference;
		 McDNearBy mAct;
      public UploadReceipt(McDNearBy splashActivity){
          weakReference = new WeakReference<McDNearBy>(splashActivity);
             mAct = weakReference.get();    
      }


			@Override
			 protected void onPreExecute() {
				super.onPreExecute();
				
			 }

			 @Override
			 protected void onPostExecute(String result) {
				 Log.e("Json Response",""+json);
				 lv = (ListView) mAct.findViewById(R.id.list_near_by);
					lv.setAdapter(mAct.sAdapter);

					lv.setOnItemClickListener(new ListView.OnItemClickListener() {
						@Override
				        public void onItemClick(AdapterView<?> a, View v, int i, long l) {
							HashMap<String, String> mapRead = resultList.get(i);
							String name = mapRead.get("Name");
							String lati = mapRead.get("Lat");
							String lngi = mapRead.get("Lng");
							String vic = mapRead.get("Vicinity");
							String type = mapRead.get("Type");
							
//							Toast.makeText(getApplicationContext(), "name : " + name + "\nlocation : " + lati + ", " + lngi + "\nvicinity : " + vic, Toast.LENGTH_LONG).show();
							
							String uri = String.format(Locale.ENGLISH, "http://maps.google.com/maps?daddr=%f,%f (%s)", Float.parseFloat(lati), Float.parseFloat(lngi), name);
							Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
							intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
							mAct.startActivity(intent);
							
				        }
					});
			 }	

			@Override
			protected String doInBackground(Void... params) {
			
				if(mAct.networkCheck.isNetworkAvailable(mAct)){
					final String url = "https://maps.googleapis.com/maps/api/place/search/json?location="+latitude+","+longitude+"&keyword=hotels%27s&radius=20000&sensor=true&key=AIzaSyDGs8rok1OPY9s4X72jBUXFJOawKynyeqk";
//					https://maps.googleapis.com/maps/api/place/search/json?location=25.0912347,55.1533389&keyword=mcdonald's&radius=700&sensor=true&key=AIzaSyDGs8rok1OPY9s4X72jBUXFJOawKynyeqk
					mAct.sAdapter = new SimpleAdapter(mAct, resultList, R.layout.near_by_row, new String[] {"Image", "Name", "Distance", "Vicinity"}, new int[] {R.id.nearby_imageview, R.id.nearby_textview1, R.id.nearby_textview2, R.id.nearby_textview3});
					//tv = (TextView)findViewById(R.id.textView1);
//					lv = (ListView)findViewById(R.id.list);
					//NearBy.tv.append("\n\nentered parse");
					// Hashmap for ListView
		     		// Creating JSON Parser instance
					JSONParser jParser = new JSONParser();
					// getting JSON string from URL
					JSONObject json = jParser.getJSONFromUrl(url);
					Log.e("mcd locator", json.toString());			
					try {
						JSONArray htmlattr = json.getJSONArray("html_attributions");
						// Getting Array of Contacts
						mAct.results = json.getJSONArray(mAct.TAG_RESULTS);
						
						// looping through All Contacts
						for(int i = 0; i < mAct.results.length(); i++){
							JSONObject c = mAct.results.getJSONObject(i);
							
							// Storing each json item in variable
							
							// Geometry is again JSON object
							JSONObject geometry = c.getJSONObject(mAct.TAG_GEOMETRY);
							// Location is again JSON object
							JSONObject location = geometry.getJSONObject(mAct.TAG_LOCATION);
							String lat = location.getString(mAct.TAG_LAT);
							String lng = location.getString(mAct.TAG_LNG);	
							
							String icon = c.getString(mAct.TAG_ICON);
							String id = c.getString(mAct.TAG_ID);
							String name = c.getString(mAct.TAG_NAME);
							//String rating = c.getString(TAG_RATING);
							String types = c.getString(mAct.TAG_TYPES);
							String vicinity = c.getString(mAct.TAG_VICINITY);
							
							Log.e("mcd locator name", name);
												
							//NearBy.tv.append("\n"+id+" - "+name+" - "+lat+" - "+lng+" - "+/*rating+*/" - "+types+" - "+vicinity+"\n"	);
							
							if(!name.contains("onald")){
								// creating new HashMap
								
								double lat1 = ((double)latitude) / 1e6;
								double lng1 = ((double)longitude) / 1e6;
								double lat2 = (Double.parseDouble(lat));
								double lng2 = (Double.parseDouble(lng));
								float [] dist = new float[1];
								Location.distanceBetween(lat1, lng1, lat2, lng2, dist);
//								double d = dist[0] * 0.000621371192f * 1e6;
								
								Location l = new Location(mAct.location);
								l.setLatitude(lat2);
								l.setLongitude(lng2);
								
								float d = mAct.location.distanceTo(l) / 1000.0f;
								
								d = Float.valueOf(mAct.twoDForm.format(d));
								
								// adding each child node to HashMap key => value
								
								mAct.dist.add(d);
								mAct.names.put(d, name);
								mAct.vicinity.put(d, vicinity);
								mAct.destLat.put(d, Float.parseFloat(lat));
								mAct.destLong.put(d, Float.parseFloat(lng));
								
								/*map.put("Image", String.valueOf(R.drawable.mcd_near_by));
								map.put("Name", name);
								map.put("Type", types);
								map.put("Vicinity", vicinity);
								map.put("id",  TAG_ID);
								map.put("Lat", lat);
								map.put("Lng", lng);
								map.put("Distance", d + " km");*/
								//map.put(TAG_RATING, rating);

								// adding HashList to ArrayList
							
							}
							
//							Collections.sort(map);
							
						}
						
						Collections.sort(mAct.dist);
						Log.e("dist", mAct.dist.toString());
						
						for(int i=0; i<mAct.dist.size(); i++){
							HashMap<String, String> map = new HashMap<String, String>();
							map.put("Image", String.valueOf(R.drawable.a));
							map.put("Name", mAct.names.get(mAct.dist.get(i)));
							map.put("Distance", mAct.dist.get(i) + "km");
							map.put("Vicinity", mAct.vicinity.get(mAct.dist.get(i)));
							map.put("Lat", mAct.destLat.get(mAct.dist.get(i)).toString());
							map.put("Lng", mAct.destLong.get(mAct.dist.get(i)).toString());
							resultList.add(map);
						}
						
					} catch (JSONException e) {
						//NearBy.tv.setText(e.getMessage());
						e.printStackTrace();
						Log.e("mcd locator error", e.getMessage());
					} 
					
					
					/**
					 * Updating parsed JSON data into ListView
					 * */

					
					
					/*b.setOnClickListener(new OnClickListener()
				    {
				      public void onClick(View v)
				      {
				         Intent mapIntent = new Intent(getApplicationContext(), PlacesMapActivity.class);
				         startActivity(mapIntent);
				      }
				    });*/
					}else{
						//Toast.makeText(McDNearBy.this, "No Network Please try again" , Toast.LENGTH_LONG).show();
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