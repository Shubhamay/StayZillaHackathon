package com.appzoy.trendonairs.view;

import java.util.ArrayList;
import com.example.demomallapp.R;
import com.squareup.picasso.Picasso;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


//Adapter class extends with BaseAdapter and implements with OnClickListener 
public class LazyImageLoadAdapter_Home extends BaseAdapter {

	private Activity activity;
	
	ArrayList<String> displayName = new ArrayList<String>();
	ArrayList<String> price = new ArrayList<String>();
	ArrayList<String> imageUrl = new ArrayList<String>();
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
	
	int nooffeild = 0;
	int p;
	Context mContext;
	
	public LazyImageLoadAdapter_Home(Context mContext,ArrayList<String> displayName, ArrayList<String> price,ArrayList<String>imageUrl,
			ArrayList<String>starRating,ArrayList<String>resturant,ArrayList<String>bar,ArrayList<String>coffe,ArrayList<String>biz,
			ArrayList<String>swim,ArrayList<String> internet,ArrayList<String>creditCard,ArrayList<String> laundary,ArrayList<String>pickUpandDrop,
			ArrayList<String> healthClub,ArrayList<String> freeNewspaper,ArrayList<String> elevator,ArrayList<String> pureveg,
			ArrayList<String>parking,ArrayList<String>twenty4HourCheckin,ArrayList<String> twenty4HourCheckOut,int i) {
		// TODO Auto-generated constructor stub
		this.activity = (Activity) mContext;
		this.mContext = mContext;
		this.displayName = displayName; 
		this.price = price;
		this.imageUrl = imageUrl;
		this.starRating = starRating;
		this.resturant = resturant;
		this.bar = bar;
		this.coffe = coffe;
		this.biz = biz;
		this.swim = swim;
		this.internet = internet;
		this.creditCard = creditCard;
		this.laundary = laundary;
		this.pickUpandDrop = pickUpandDrop;
		this.healthClub = healthClub;
		this.freeNewspaper = freeNewspaper;
		this.elevator = elevator;
		this.pureveg = pureveg;
		this.parking = parking;
		this.twenty4HourCheckin = twenty4HourCheckin;
		this.twenty4HourCheckOut = twenty4HourCheckOut;
		p = i;
}

	
	public void updateAdapter(ArrayList<String> displayName, ArrayList<String> price,ArrayList<String>imageUrl,
			ArrayList<String>starRating,ArrayList<String>resturant,ArrayList<String>bar,ArrayList<String>coffe,ArrayList<String>biz,
			ArrayList<String>swim,ArrayList<String> internet,ArrayList<String>creditCard,ArrayList<String> laundary,ArrayList<String>pickUpandDrop,
			ArrayList<String> healthClub,ArrayList<String> freeNewspaper,ArrayList<String> elevator,ArrayList<String> pureveg,
			ArrayList<String>parking,ArrayList<String>twenty4HourCheckin,ArrayList<String> twenty4HourCheckOut,int i)
	{
		
		p=i;
		this.displayName = displayName; 
		this.price = price;
		this.imageUrl = imageUrl;
		this.starRating = starRating;
		this.resturant = resturant;
		this.bar = bar;
		this.coffe = coffe;
		this.biz = biz;
		this.swim = swim;
		this.internet = internet;
		this.creditCard = creditCard;
		this.laundary = laundary;
		this.pickUpandDrop = pickUpandDrop;
		this.healthClub = healthClub;
		this.freeNewspaper = freeNewspaper;
		this.elevator = elevator;
		this.pureveg = pureveg;
		this.parking = parking;
		this.twenty4HourCheckin = twenty4HourCheckin;
		this.twenty4HourCheckOut = twenty4HourCheckOut;	
		notifyDataSetChanged();
	}
	
	public int getCount() {
		//AppzoyDebug.e("Total Elements", "" + dataone.size());
		return displayName.size();

	}

	public Object getItem(int position) {
		return position;
	}

	public long getItemId(int position) {
		return position;
	}

	/********* Create a holder Class to contain inflated xml file elements *********/
	public static class ViewHolder {

		public TextView Questionhome;
		public ImageView questionimageinhome;
		public TextView objectid;
		public RelativeLayout relImage;
		public TextView heading;
		public TextView price;
	}

	public View getView(int position, View convertView, ViewGroup parent) {

				View vi = convertView;
		final ViewHolder holder;

		if (convertView == null) {

			/****** Inflate tabitem.xml file for each row ( Defined below ) *******/
			//vi = inflater.inflate(R.layout.home_listtest, null);
			vi = LayoutInflater.from(activity).inflate(R.layout.home_listtestdemo, parent, false);
			/****** View Holder Object to contain tabitem.xml file elements ******/

			holder = new ViewHolder();
			holder.Questionhome = (TextView) vi.findViewById(R.id.Questionhome);
			holder.questionimageinhome = (ImageView) vi
					.findViewById(R.id.questionimageinhome);
			holder.price = (TextView) vi.findViewById(R.id.prices);
			holder.objectid = (TextView) vi.findViewById(R.id.likeques);
			holder.heading = (TextView) vi.findViewById(R.id.heading);
			holder.relImage = (RelativeLayout) vi
					.findViewById(R.id.imageLayout);

			/************ Set holder with LayoutInflater ************/
			vi.setTag(holder);

		} else
			holder = (ViewHolder) vi.getTag();
		
		holder.heading.setText(displayName.get(position));
		holder.objectid.setText(price.get(position));
		holder.price.setText(starRating.get(position));
		Picasso.with(mContext).load(imageUrl.get(position)).fit().centerCrop().into(holder.questionimageinhome);
	
		return vi;

	}


	

	
}