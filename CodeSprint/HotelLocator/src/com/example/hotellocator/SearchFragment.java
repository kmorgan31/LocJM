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
	
	final Attraction[] attractions = {
    		new Attraction("Xayamaca", "fun","Montego Bay", "Mr. Paul Hastings", "Dalton Hastings", "Howard Cooke Blvd, Freeport", "Montego Bay", "St. James", new String[]{"844-9935"}, new String[]{"Plant Centre", "Complex Grounds", "Gift Shop", "Juice Bar"}),
    		new Attraction("White River Valley", "boring", "Ocho Rios", "Daniel Melville", "Vaneka McKenzie", "Cascade", "Endevour", "St. Mary", new String[]{"974-2018","382-6907"}, new String[]{"River Tubing", "Horseback Riding", "Kayaking"} ),
    		new Attraction("Veronica Park", "nice food", "Montego Bay", "Mr. Brasco Lee", "Michael Lee Chin", "Allsides District", "Waita Bit P.O.", "Trelawney", new String[]{"538-8940", "468-9449"}, null),
    		new Attraction("Two Sister's Cave", "scary", "Kingston", "UCD", "Mrs. Winsome Roache", "Hellshire", null, "St. Catherine", null, new String[]{"Arawak Cave"} )
    };
	
	ArrayAdapter<String> listAdapter;
	ImageButton searchButton;
	EditText query;
	ArrayList<String> displayList = new ArrayList<String>();
	
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	          Bundle savedInstanceState) {
	  	
	      	View rootView = inflater.inflate(R.layout.activity_searchable, container, false);
	      	query = (EditText)rootView.findViewById(R.id.searchField);
	      	searchButton = (ImageButton)rootView.findViewById(R.id.searchButton);
	      	searchButton.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					System.out.println(query.getText().toString());
					searchHotels(query.getText().toString());
					
				}
	      		
	      	});
	      	for (int i=0;i<hotels.length;i++){
				displayList.add(hotels[i].getHotelName());
			}
	      	for (int i=0;i<attractions.length;i++){
				displayList.add(attractions[i].getName());
			}
			listAdapter = new ArrayAdapter<String>(getActivity(),
			        android.R.layout.simple_list_item_1, displayList);
			
			setListAdapter(listAdapter);
	      	return rootView;
	}
	
	protected void searchHotels(String query) {
		// TODO Auto-generated method stub
		ArrayList<String> found = new ArrayList<String>();
		
		for (int i=0;i<hotels.length;i++){	
			if(hotels[i].getHotelName().equals(query)){
				found.add(hotels[i].getHotelName());
			}
		}
		for (int i=0;i<attractions.length;i++){	
			if(attractions[i].getName().equals(query)){
				found.add(attractions[i].getName());
			}
		}
		
		Intent myIntent = new Intent();
		myIntent.setClass(getActivity(), SearchableActivity.class);
		myIntent.putExtra("names", found);
	    startActivity(myIntent);
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
			 //TODO Auto-generated method stub
			 //Go to details of hotel
		String type = null;
		boolean typeFound=false;
		for(int i=0;i<hotels.length;i++)
		{
			if(hotels[i].getHotelName().equals(displayList.get(position)))
			{
				type="hotel";
				typeFound=true;
				break;
			}
		}
		if(typeFound==false){
		for(int i=0;i<attractions.length;i++)
		{
			if(attractions[i].getName().equals(displayList.get(position)))
			{
				type="attraction";
				break;
			}
		}}
			Intent myIntent = new Intent();
			myIntent.setClass(getActivity(), DetailsFragment.class);
			myIntent.putExtra("name", displayList.get(position));
			myIntent.putExtra("type", type);
		    startActivity(myIntent);
		}
}
