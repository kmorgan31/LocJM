package com.example.hotellocator;

import java.util.ArrayList;

import com.google.android.gms.maps.model.LatLng;

import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

public class SearchableActivity extends ListActivity {

	final Hotel[] hotels = {
			new Hotel("Pegasus", "7 Knutsford Boulevard", "Lorem Ipsum is simply dummy text of the printing and typesetting industry.", 1000, new LatLng(25.002856, -76.795659), 5),
    		new Hotel("Hilton", "7 Hibiscus Drive", "Lorem ipsum", 1001, new LatLng(18.002856, -76.795659), 4),
    		new Hotel("Four Seasons", "2A Shalimar Close", "Eureka", 1002, new LatLng(28.002856, -76.795659), 6),
    		new Hotel("Holiday Inn", "83 Lore Lane", "Exciting", 1003, new LatLng(18.002856, 76.795659), 3),
    		new Hotel("Grande Palladium", "3 Old Way", "Boring", 1004, new LatLng(18.002856, 45.795659), 2),
    		new Hotel("Riu", "Ocho Rios", "nice food", 1005, new LatLng(14.002856, -7.795659), 4)
    };
	final Attraction[] attractions = {
    		new Attraction("Xayamaca", "fun","Montego Bay", "Mr. Paul Hastings", "Dalton Hastings", "Howard Cooke Blvd, Freeport", "Montego Bay", "St. James", new String[]{"844-9935"}, new String[]{"Plant Centre", "Complex Grounds", "Gift Shop", "Juice Bar"}),
    		new Attraction("White River Valley", "boring", "Ocho Rios", "Daniel Melville", "Vaneka McKenzie", "Cascade", "Endevour", "St. Mary", new String[]{"974-2018","382-6907"}, new String[]{"River Tubing", "Horseback Riding", "Kayaking"} ),
    		new Attraction("Veronica Park", "nice food", "Montego Bay", "Mr. Brasco Lee", "Michael Lee Chin", "Allsides District", "Waita Bit P.O.", "Trelawney", new String[]{"538-8940", "468-9449"}, null),
    		new Attraction("Two Sister's Cave", "scary", "Kingston", "UCD", "Mrs. Winsome Roache", "Hellshire", null, "St. Catherine", null, new String[]{"Arawak Cave"} )
    };
	ArrayAdapter<String> listAdapter;
	ArrayList<String> found;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_searchable_results);
		
		found = getIntent().getStringArrayListExtra("names");
		
			listAdapter = new ArrayAdapter<String>(this,
			        android.R.layout.simple_list_item_1, found);
			
			setListAdapter(listAdapter);

//		Intent myIntent = new Intent();
//		myIntent.setClass(this, SearchFragment.class);
//		startActivity(myIntent);
	    
//		listAdapter = new ArrayAdapter<String>(this,
//		        android.R.layout.simple_list_item_1, foundHotels);
//		
//		setListAdapter(listAdapter);
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
			 //TODO Auto-generated method stub
			 //Go to details of hotel
			String type = null;
			boolean typeFound=false;
			for(int i=0;i<hotels.length;i++)
			{
				if(hotels[i].getHotelName().equals(found.get(position)))
				{
					type="hotel";
					typeFound=true;
					break;
				}
			}
			if(typeFound==false){
			for(int i=0;i<attractions.length;i++)
			{
				if(attractions[i].getName().equals(found.get(position)))
				{
					type="attraction";
					break;
				}
			}}
			Intent myIntent = new Intent();
			myIntent.setClass(this, DetailsFragment.class);
			myIntent.putExtra("name", found.get(position));
			myIntent.putExtra("type", type);
		    startActivity(myIntent);
		}
	
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.searchable, menu);
		return true;
	}

}
