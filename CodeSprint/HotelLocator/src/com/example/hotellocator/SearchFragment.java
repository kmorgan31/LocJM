package com.example.hotellocator;

import java.util.ArrayList;

import com.google.android.gms.maps.model.LatLng;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

public class SearchFragment extends ListFragment {
	final Hotel[] hotels = {
			new Hotel("Pegasus", "7 Knutsford Boulevard", "Lorem Ipsum is simply dummy text of the printing and typesetting industry.", 1000, new LatLng(25.002856, -76.795659), 5),
    		new Hotel("Hilton", "7 Hibiscus Drive", "Lorem ipsum", 1001, new LatLng(18.002856, -76.795659), 4),
    		new Hotel("Four Seasons", "2A Shalimar Close", "Eureka", 1002, new LatLng(28.002856, -76.795659), 6),
    		new Hotel("Holiday Inn", "83 Lore Lane", "Exciting", 1003, new LatLng(18.002856, 76.795659), 3),
    		new Hotel("Grande Palladium", "3 Old Way", "Boring", 1004, new LatLng(18.002856, 45.795659), 2),
    		new Hotel("Riu", "Ocho Rios", "nice food", 1005, new LatLng(14.002856, -7.795659), 4)
    };
	
	ArrayAdapter<String> listAdapter;
	ImageButton searchButton;
	Button aroundMe;
	EditText query;
	
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	          Bundle savedInstanceState) {
	  	
	      	View rootView = inflater.inflate(R.layout.activity_searchable, container, false);
	      	query = (EditText)rootView.findViewById(R.id.searchField);
	      	aroundMe = (Button)rootView.findViewById(R.id.mapSearchButton);
	      	aroundMe.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					Bundle b = new Bundle();
					b.putString("search", query.getText().toString());
					Intent myIntent = new Intent(getActivity(), GoogleMapActivity.class).putExtras(b);
					startActivity(myIntent);
				}
	      		
	      	});
	      	searchButton = (ImageButton)rootView.findViewById(R.id.searchButton);
	      	searchButton.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					System.out.println(query.getText().toString());
					searchHotels(query.getText().toString());
					
				}
	      		
	      	});
	      	ArrayList<String> hotelList = new ArrayList<String>();
			for (int i=0;i<hotels.length;i++){
				hotelList.add(hotels[i].getHotelName());
			}
			listAdapter = new ArrayAdapter<String>(getActivity(),
			        android.R.layout.simple_list_item_1, hotelList);
			
			setListAdapter(listAdapter);
	      	return rootView;
	}
	
	protected void searchHotels(String query) {
		// TODO Auto-generated method stub
		ArrayList<String> foundHotels = new ArrayList<String>();
		
		for (int i=0;i<hotels.length;i++){	
			if(hotels[i].getHotelName().equals(query)){
				foundHotels.add(hotels[i].getHotelName());
			}
		}
		
		Intent myIntent = new Intent();
		myIntent.setClass(getActivity(), SearchableActivity.class);
		myIntent.putExtra("hotel names", foundHotels);
	    startActivity(myIntent);
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
			 //TODO Auto-generated method stub
			 //Go to details of hotel
			Intent myIntent = new Intent();
			myIntent.setClass(getActivity(), DetailsFragment.class);
			myIntent.putExtra("hotel name", hotels[position].getHotelName());
		    startActivity(myIntent);
		}
}
