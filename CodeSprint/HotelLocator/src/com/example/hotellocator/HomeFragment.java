package com.example.hotellocator;

import java.util.ArrayList;

import com.google.android.gms.maps.model.LatLng;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class HomeFragment extends Fragment {

	TextView hotelName, hotelDescription, attractionName, attractionDescription, attractionThemes;
	Button hotelButton, attractionButton;
	String s ="";
	
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
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	          Bundle savedInstanceState) {
	  	
	    View rootView = inflater.inflate(R.layout.activity_home, container, false);

	    hotelName = (TextView)rootView.findViewById(R.id.hotelName);
		hotelName.setText(featuredHotels[0].getHotelName());
		hotelDescription = (TextView)rootView.findViewById(R.id.hotelDescription);
		hotelDescription.setText(featuredHotels[0].getDescription());
		hotelButton = (Button)rootView.findViewById(R.id.hotelButton);
		attractionButton = (Button)rootView.findViewById(R.id.attractionButton);
		
		attractionName = (TextView)rootView.findViewById(R.id.attractionName);
		attractionName.setText(featuredAttractions[0].getName());
		attractionDescription = (TextView)rootView.findViewById(R.id.attractionDescription);
		attractionDescription.setText(featuredAttractions[0].getDescription());
		attractionThemes = (TextView)rootView.findViewById(R.id.attractionThemes);
		for(int i=0;i<featuredAttractions[0].getThemes().length;i++)
		{
			s = s + featuredAttractions[0].getThemes()[i] + "\n";
		}
		attractionThemes.setText(s);
		
		hotelButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent myIntent = new Intent();
				myIntent.setClass(getActivity(), FeaturedFragment.class);
				myIntent.putExtra("list", "hotel");
			    startActivity(myIntent);
			}
			
		});
		
		attractionButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent myIntent = new Intent();
				myIntent.setClass(getActivity(), FeaturedFragment.class);
				myIntent.putExtra("list", "attraction");
			    startActivity(myIntent);
			}
			
		});
		
		return rootView;
	}

}
