package com.appzoy.trendonairs.view;

import java.util.ArrayList;

import com.example.demomallapp.R;
import com.squareup.picasso.Picasso;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


//Adapter class extends with BaseAdapter and implements with OnClickListener 
public class LazyImageLoadAdapter_Detail extends BaseAdapter implements OnItemClickListener{

	private Activity activity;
	
	ArrayList<String> displayName = new ArrayList<String>();
	
	int nooffeild = 0;
	int p;
	Context mContext;
	
	public LazyImageLoadAdapter_Detail(Context mContext,ArrayList<String> displayName,int i) {
		// TODO Auto-generated constructor stub
		this.activity = (Activity) mContext;
		this.mContext = mContext;
		this.displayName = displayName; 
		
		p = i;
}

	
	public int getCount() {
		//AppzoyDebug.e("Total Elements", "" + dataone.size());
		return displayName.size()/2;

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
		public LinearLayout listid;
			}

	public View getView(int position, View convertView, ViewGroup parent) {

				View vi = convertView;
		final ViewHolder holder;

		if (convertView == null) {

			/****** Inflate tabitem.xml file for each row ( Defined below ) *******/
			//vi = inflater.inflate(R.layout.home_listtest, null);
			vi = LayoutInflater.from(activity).inflate(R.layout.list_item, parent, false);
			/****** View Holder Object to contain tabitem.xml file elements ******/

			holder = new ViewHolder();
			holder.Questionhome = (TextView) vi.findViewById(R.id.Questionhome);
			holder.questionimageinhome = (ImageView) vi
					.findViewById(R.id.questionimageinhome);
			holder.listid = (LinearLayout)  vi
					.findViewById(R.id.listid);

			/************ Set holder with LayoutInflater ************/
			vi.setTag(holder);

		} else
			holder = (ViewHolder) vi.getTag();
		
		if(displayName.get(position).equalsIgnoreCase("true")){
			holder.listid.setBackgroundColor(Color.GREEN);
		}else{
			holder.listid.setBackgroundColor(Color.WHITE);
		}
		
		holder.Questionhome.setText(""+displayName.get((displayName.size()/2)+position));
		if(position ==0)
		holder.questionimageinhome.setImageResource(R.drawable.restaurant);
		else if (position == 1)
			holder.questionimageinhome.setImageResource(R.drawable.bar);
		else if (position == 2)
			holder.questionimageinhome.setImageResource(R.drawable.coffee);
		else if (position == 3)
			holder.questionimageinhome.setImageResource(R.drawable.biz);
		else if (position == 4)
			holder.questionimageinhome.setImageResource(R.drawable.swim);
		else if (position == 5)
			holder.questionimageinhome.setImageResource(R.drawable.internet);
		else if (position == 6)
			holder.questionimageinhome.setImageResource(R.drawable.creditcard);
		else if (position == 7)
			holder.questionimageinhome.setImageResource(R.drawable.laundary);
		else if (position == 8)
			holder.questionimageinhome.setImageResource(R.drawable.pickupdrop);
		else if (position == 9)
			holder.questionimageinhome.setImageResource(R.drawable.healthclub);
		else if (position == 10)
			holder.questionimageinhome.setImageResource(R.drawable.newspaper);
		else if (position == 11)
			holder.questionimageinhome.setImageResource(R.drawable.elevator);
		else if (position == 12)
			holder.questionimageinhome.setImageResource(R.drawable.pureveg);
		else if (position == 13)
			holder.questionimageinhome.setImageResource(R.drawable.parking);
		else if (position == 14)
			holder.questionimageinhome.setImageResource(R.drawable.hr24);
		else if (position == 15)
			holder.questionimageinhome.setImageResource(R.drawable.hr24);
		return vi;

	}


	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		
		
		
	}

	
}