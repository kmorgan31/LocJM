package com.example.hotellocator;

import java.util.ArrayList;

import com.google.android.gms.maps.model.LatLng;

import android.os.Bundle;
import android.os.Parcelable;
import android.app.ListActivity;
import android.content.Intent;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class FeaturedFragment extends ListActivity {

	TextView tBox1;
	TextView name;
	TextView tBox2;
	Button seeMore;
	String type;

	ArrayList<String> featuredList = new ArrayList<String>();
	ArrayAdapter<String> listAdapter;
	
	final Hotel[] featuredHotels = {
			new Hotel("Pegasus", "7 Knutsford Boulevard", "Lorem Ipsum is simply dummy text of the printing and typesetting industry.", 1000, new LatLng(25.002856, -76.795659), 5),
	    		new Hotel("Hilton", "7 Hibiscus Drive", "Lorem ipsum", 1001, new LatLng(18.002856, -76.795659), 4),
	    		new Hotel("Four Seasons", "2A Shalimar Close", "Eureka", 1002, new LatLng(28.002856, -76.795659), 6),
	    		new Hotel("Grande Palladium", "3 Old Way", "Boring", 1004, new LatLng(18.002856, 45.795659), 2),
	};
	
	final Attraction[] featuredAttractions = {
    		new Attraction("Xayamaca", "fun", "Montego Bay", "Mr. Paul Hastings", "Dalton Hastings", "Howard Cooke Blvd, Freeport", "Montego Bay", "St. James", new String[]{"844-9935"}, new String[]{"Plant Centre", "Complex Grounds", "Gift Shop", "Juice Bar"}),
    		new Attraction("White River Valley", "boring", "Ocho Rios", "Daniel Melville", "Vaneka McKenzie", "Cascade", "Endevour", "St. Mary", new String[]{"974-2018","382-6907"}, new String[]{"River Tubing", "Horseback Riding", "Kayaking"} ),
    		new Attraction("Veronica Park", "nice food", "Montego Bay", "Mr. Brasco Lee", "Michael Lee Chin", "Allsides District", "Waita Bit P.O.", "Trelawney", new String[]{"538-8940", "468-9449"}, null),
    };

	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_featured);
      	
		name = (TextView)findViewById(R.id.name);
      	tBox1 = (TextView)findViewById(R.id.desBox1);
      	tBox2 = (TextView)findViewById(R.id.desBox2);
      	
      	type = getIntent().getStringExtra("type");
      	
      	if(type.equals("hotel")){	
			name.setText(featuredHotels[0].getHotelName());
			tBox1.setText(featuredHotels[0].getDescription());
			tBox2.setVisibility(View.INVISIBLE);
			
			for (int i=1;i<featuredHotels.length;i++){
				featuredList.add(featuredHotels[i].getHotelName());
			}
      	}
      	else{
      		if(type.equals("attraction"))
      		{
      			name.setText(featuredAttractions[0].getName());
    			tBox1.setText(featuredAttractions[0].getDescription());
    			String s = "";
    			for(int i=0;i<featuredAttractions[0].getThemes().length;i++)
    			{
    				s = s + featuredAttractions[0].getThemes()[0] + "\n";
    			}
    			tBox2.setText(s);
    			
    			for (int i=1;i<featuredAttractions.length;i++){
    				featuredList.add(featuredAttractions[i].getName());
    			}
      		}
      	}
			
			listAdapter = new ArrayAdapter<String>(this,
			        android.R.layout.simple_list_item_1, featuredList);
			
			setListAdapter(listAdapter);
		
		seeMore = (Button)findViewById(R.id.button1);
		seeMore.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent myIntent = new Intent();
				myIntent.setClass(FeaturedFragment.this, DetailsFragment.class);
				if(type.equals("hotel")){
					myIntent.putExtra("name", featuredHotels[0].getHotelName());
					myIntent.putExtra("type", type);}
				else
					if(type.equals("attraction")){
						myIntent.putExtra("name", featuredAttractions[0].getName());
						myIntent.putExtra("type", type);}
			    startActivity(myIntent);
			}
			
		});
	}
		
		@Override
		public void onListItemClick(ListView l, View v, int position, long id) {
				 //TODO Auto-generated method stub
				 //Go to details of hotel
				Intent myIntent = new Intent();
				myIntent.setClass(this, DetailsFragment.class);
				if(type.equals("hotel")){
					myIntent.putExtra("name", featuredHotels[position+1].getHotelName());
					myIntent.putExtra("type", "hotel");}
				else
					if(type.equals("attraction")){
						myIntent.putExtra("name", featuredAttractions[position+1].getName());
						myIntent.putExtra("type", "attraction");}
				
			    startActivity(myIntent);
			}
	}
