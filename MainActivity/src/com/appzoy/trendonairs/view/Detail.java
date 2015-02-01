package com.appzoy.trendonairs.view;

import java.util.ArrayList;

import com.example.demomallapp.R;
import com.squareup.picasso.Picasso;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ListView;

public class Detail extends Activity {
	
	String displayName;
	String price;
	String imageUrl;
	String starRating;
	String resturant;
	String bar;
	String coffe;
	String biz;
	String swim;
	String internet;
	String creditCard;
	String laundary;
	String pickUpandDrop;
	String healthClub;
	String freeNewspaper;
	String elevator;
	String pureveg;
	String parking ;
	String twenty4HourCheckin ;
	String twenty4HourCheckOut;
	ArrayList<String> AllAmenities =  new ArrayList<String>();
	private LazyImageLoadAdapter_Detail adapter1;
	 ListView actuallist;
	 ImageView img;
	@Override
	public void onCreate(Bundle savedInstanceState) {
	this.requestWindowFeature(Window.FEATURE_NO_TITLE);
	super.onCreate(savedInstanceState);
	setContentView(R.layout.questiondefaultlayouttest);
	img = (ImageView) findViewById(R.id.img);
	Bundle intent = getIntent().getExtras();
	displayName = intent.getString("displayName");
	price =  intent.getString("price");
	imageUrl =  intent.getString("imageUrl");
	starRating =  intent.getString("starRating");
	resturant =  intent.getString("resturant");
	bar =  intent.getString("bar");
	coffe =  intent.getString("coffe");
	biz =  intent.getString("biz");
	swim =  intent.getString("swim");
	internet =  intent.getString("internet");
	creditCard =  intent.getString("creditCard");
	laundary =  intent.getString("laundary");
	pickUpandDrop =  intent.getString("pickUpandDrop");
	healthClub =  intent.getString("healthClub");
	freeNewspaper =  intent.getString("freeNewspaper");
	elevator =  intent.getString("elevator");
	pureveg =  intent.getString("pureveg");
	parking = intent.getString("parking");
	twenty4HourCheckin =  intent.getString("twenty4HourCheckin");
	twenty4HourCheckOut =  intent.getString("twenty4HourCheckOut");
	AllAmenities.clear();
	AllAmenities.add(resturant);
	AllAmenities.add(bar);
	AllAmenities.add(coffe);
	AllAmenities.add(biz);
	AllAmenities.add(swim);
	AllAmenities.add(internet);
	AllAmenities.add(creditCard);
	AllAmenities.add(laundary);
	AllAmenities.add(pickUpandDrop);
	AllAmenities.add(healthClub);
	AllAmenities.add(freeNewspaper);
	AllAmenities.add(elevator);
	AllAmenities.add(pureveg);
	AllAmenities.add(parking);
	AllAmenities.add(twenty4HourCheckin);
	AllAmenities.add(twenty4HourCheckOut);
	AllAmenities.add("resturant");
	AllAmenities.add("bar");
	AllAmenities.add("coffe");
	AllAmenities.add("biz");
	AllAmenities.add("swim");
	AllAmenities.add("internet");
	AllAmenities.add("creditCard");
	AllAmenities.add("laundary");
	AllAmenities.add("pickUpandDrop");
	AllAmenities.add("healthClub");
	AllAmenities.add("freeNewspaper");
	AllAmenities.add("elevator");
	AllAmenities.add("pureveg");
	AllAmenities.add("parking");
	AllAmenities.add("twenty4HourCheckin");
	AllAmenities.add("twenty4HourCheckOut");
	
	Picasso.with(Detail.this).load(imageUrl).fit().centerCrop().into(img);
	
	actuallist = (ListView) findViewById(R.id.list);
	adapter1 = new LazyImageLoadAdapter_Detail(Detail.this,AllAmenities,0);
	actuallist.setAdapter(adapter1);
	
	}
}
