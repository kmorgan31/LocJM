package com.example.hotellocator;

import java.util.ArrayList;

import com.google.android.gms.maps.model.LatLng;

import android.os.Bundle;
import android.os.Parcelable;
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

public class FeaturedFragment extends ListFragment {

	TextView description;
	TextView hotelName;
	ArrayAdapter<String> listAdapter;
	Hotel[] featuredHotels = {
			new Hotel("Pegasus", "7 Knutsford Boulevard", "Lorem Ipsum is simply dummy text of the printing and typesetting industry.", 1000, new LatLng(25.002856, -76.795659), 5),
	    		new Hotel("Hilton", "7 Hibiscus Drive", "Lorem ipsum", 1001, new LatLng(18.002856, -76.795659), 4),
	    		new Hotel("Four Seasons", "2A Shalimar Close", "Eureka", 1002, new LatLng(28.002856, -76.795659), 6),
	    		new Hotel("Holiday Inn", "83 Lore Lane", "Exciting", 1003, new LatLng(18.002856, 76.795659), 3),
	    		new Hotel("Grande Palladium", "3 Old Way", "Boring", 1004, new LatLng(18.002856, 45.795659), 2),
	};
	Button seeMore;
	
	public FeaturedFragment(){
//		Bundle bundle = getArguments().getBundle("hotels");
//		Parcelable[] parcels = bundle.getParcelableArray("hotels");
//
//		Hotel[] hotels = new Hotel[parcels.length];
//		for (Parcelable par : parcels){
//		     hotels.add((Hotel) par);              
//		}
		
	}
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
          Bundle savedInstanceState) {
  	
      	View rootView = inflater.inflate(R.layout.activity_featured, container, false);
      	hotelName = (TextView)rootView.findViewById(R.id.hotelName);
		hotelName.setText(featuredHotels[0].getHotelName());
		description = (TextView)rootView.findViewById(R.id.hotelDescription);
		description.setText(featuredHotels[0].getDescription());
		ArrayList<String> hotelList = new ArrayList<String>();
		for (int i=1;i<featuredHotels.length;i++){
			hotelList.add(featuredHotels[i].getHotelName());
		}
		listAdapter = new ArrayAdapter<String>(getActivity(),
		        android.R.layout.simple_list_item_1, hotelList);
		
		setListAdapter(listAdapter);
		
		seeMore = (Button)rootView.findViewById(R.id.button1);
		seeMore.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent myIntent = new Intent();
				myIntent.setClass(getActivity(), DetailsFragment.class);
				myIntent.putExtra("hotel name", featuredHotels[0].getHotelName());
			    startActivity(myIntent);
			}
			
		});
		
		return rootView;
	}
		
		@Override
		public void onListItemClick(ListView l, View v, int position, long id) {
				 //TODO Auto-generated method stub
				 //Go to details of hotel
				Intent myIntent = new Intent();
//				Bundle b = new Bundle();
//				b.putSerializable("Hotels", (Serializable[]) hotels);
				myIntent.setClass(getActivity(), DetailsFragment.class);
				//myIntent.putExtra("hotels", hotels);
				myIntent.putExtra("hotel name", featuredHotels[position+1].getHotelName());
				Toast.makeText(getActivity(),featuredHotels[position+1].getHotelName(),
						Toast.LENGTH_SHORT).show();
			    startActivity(myIntent);
			}
	}
