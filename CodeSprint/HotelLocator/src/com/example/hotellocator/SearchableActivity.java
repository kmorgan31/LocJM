package com.example.hotellocator;

import java.util.ArrayList;

import com.google.android.gms.maps.model.LatLng;

import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.app.SearchManager;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class SearchableActivity extends ListActivity {

	final Hotel[] hotels = {
			new Hotel("Pegasus", "7 Knutsford Boulevard", "Lorem Ipsum is simply dummy text of the printing and typesetting industry.", 1000, new LatLng(25.002856, -76.795659), 5),
    		new Hotel("Hilton", "7 Hibiscus Drive", "Lorem ipsum", 1001, new LatLng(18.002856, -76.795659), 4),
    		new Hotel("Four Seasons", "2A Shalimar Close", "Eureka", 1002, new LatLng(28.002856, -76.795659), 6),
    		new Hotel("Holiday Inn", "83 Lore Lane", "Exciting", 1003, new LatLng(18.002856, 76.795659), 3),
    		new Hotel("Grande Palladium", "3 Old Way", "Boring", 1004, new LatLng(18.002856, 45.795659), 2),
    		new Hotel("Riu", "Ocho Rios", "nice food", 1005, new LatLng(14.002856, -7.795659), 4)
    };
	ArrayAdapter<String> listAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_searchable);
		
		Intent intent = getIntent();
	    if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
	      String query = intent.getStringExtra(SearchManager.QUERY);
	      doMySearch(query);
	    }
	}

	    
	public void doMySearch(String query){
		ArrayList<String> foundHotels = new ArrayList<String>();
		ArrayList<Integer> positions = new ArrayList<Integer>();
		for (int i=0;i<hotels.length;i++){
			if(hotels[i].getHotelName().equals(query)){
				foundHotels.add(hotels[i].getHotelName());
				positions.add(i);
			}
		}
		
		Intent myIntent = new Intent();
		myIntent.setClass(this, SearchFragment.class);
		myIntent.putExtra("hotel positions", positions);
	    startActivity(myIntent);
	    
//		listAdapter = new ArrayAdapter<String>(this,
//		        android.R.layout.simple_list_item_1, foundHotels);
//		
//		setListAdapter(listAdapter);
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
			 //TODO Auto-generated method stub
			 //Go to details of hotel
			Intent myIntent = new Intent();
			myIntent.setClass(this, DetailsFragment.class);
			myIntent.putExtra("hotel position", position);
		    startActivity(myIntent);
		}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.searchable, menu);
		return true;
	}

}
