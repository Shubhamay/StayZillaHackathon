package com.appzoy.trendonairs.view;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

import com.example.demomallapp.R;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


public class SplashActivity extends Activity {
	String [] rowData;
	ArrayList<String> token = new ArrayList<String>();
	ArrayList<String> smsdata = new ArrayList<String>();
	ArrayList<Integer> rank = new ArrayList<Integer>(Collections.nCopies(570, 0));
	ArrayList<String> placerank = new ArrayList<String>();
	SharedPreferences sharedpreferences;
	public static final String MyPREFERENCES = "MyPrefs" ;
	 public static final String Place = "Bangalore"; 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitysplash);
        
        InputStream is = getResources().openRawResource(R.raw.places);
        try
        {
	         BufferedReader reader = new BufferedReader(new InputStreamReader(is));
	         String line;
	         while ((line = reader.readLine()) != null )
	         {
				   rowData = line.split(",");
				   for(int i=0; i < rowData.length; i++)
				   {
					   	 String x = rowData[i];
					   x = x.replaceAll(","," ");
					   if(x.compareTo(" ")!=0)
			        	 token.add(x);
				   }
	         }
	         Log.e("arraylist", "csv val :"+ token);
        }

        catch (FileNotFoundException e)
        {
		  // TODO Auto-generated catch block
		  e.printStackTrace();
        } 
        catch (IOException e) {
		  // TODO Auto-generated catch block
		  e.printStackTrace();
        	}
        
        Uri uri = Uri.parse("content://sms/draft");
        Cursor c= getContentResolver().query(uri, null, null ,null,null);
        startManagingCursor(c);
         
        // Read the sms data and store it in the list
        if(c.moveToFirst()) {
            for(int i=0; i < c.getCount(); i++) {
              String xd = c.getString(c.getColumnIndexOrThrow("body")).toString();
              Log.e("sms", "no of message:"+c.getCount()+"mess body is"+xd);
              xd = xd.replace(",", "");
             xd = xd.replace("!", "");
             xd = xd.replace(".", "");
             xd = xd.replace("?", "");
             xd = xd.replace("/", "");
             xd = xd.replace("+", "");
             xd = xd.replace(":", "");
             xd = xd.replace(";", "");
             xd = xd.replace("'", "");
             xd = xd.replace("]", "");
             xd = xd.replace("[", "");
             
//             xd = xd.replace("  ", " ");
             String[] tk =  xd.split(" ");
             for(int k=0;k<tk.length;k++)
             {
            	 String tok = tk[k].trim();
              Log.e("tokenized", "message token: "+tok);
           	  if(token.contains(tok) && !tok.isEmpty())
           	  {
           		  Log.e("tokresult", "token is present");
           		  placerank.add(tok);
           	  }
	          
             }
             c.moveToNext();
        }
        }
        c.close();
        
        if(placerank != null)
        {
        	for(int n =0 ; n<placerank.size(); n++)
        	{
        		int indx = token.indexOf(placerank.get(n).toString());
        		int cnt = rank.get(indx);
        		rank.set(indx, ++cnt);
        		Log.e("max", "position to be incremented"+indx+"incremented to"+rank.get(indx));
        		//Log.e("rank", "rank list:"+rank);
        	}
        	
        }
        Log.e("max", "max rank :"+Collections.max(rank));
        int indexOfMax = 0;
        for (int z=1; z < rank.size(); z++) {
            if (rank.get(z) > rank.get(indexOfMax))
            		{
                indexOfMax = z;
            		}
        }
        sharedpreferences =  getSharedPreferences("stayzilla",MODE_PRIVATE);
        Editor editor = sharedpreferences.edit();
        editor.putString("Place", token.get(indexOfMax).toString());
        editor.commit();
       
        Thread background = new Thread() {
            public void run() {
                 
                try {
                    sleep(2*1000);
                   
                    Intent i = new Intent(SplashActivity.this,HomeTestActivity1.class);
                    startActivity(i);
                    
                    finish();
                } catch (Exception e) {
                 
                }
            }
        };
         
        // start thread
        background.start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
