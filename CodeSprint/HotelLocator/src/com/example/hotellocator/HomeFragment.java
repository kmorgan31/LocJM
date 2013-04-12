package com.example.hotellocator;

import com.google.android.gms.maps.model.LatLng;

import android.os.Bundle;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class HomeFragment extends Fragment {

	TextView hotelName, hotelDescription, attractionName, attractionDescription, attractionThemes;
	Button hotelButton, attractionButton, nearMeButton;
	String s ="";
	
	final Hotel[] featuredHotels = {
			new Hotel("Pegasus", "7 Knutsford Boulevard", "Lorem Ipsum is simply dummy text of the printing and typesetting industry.", "Kingston", "Kingston", new LatLng(25.002856, -76.795659)),
    		new Hotel("Alhambra Inn", "1 Tucker Avenue", "Close to city", "Kingston 3", "Kingston", new LatLng(-78.3666,18.26627)),
    		new Hotel("Almond Tree", "Arawak P.O. Box 332", "Lovely weather", "Mammee Bay", "St. Ann", new LatLng(-78.3665,18.26398)),
    		new Hotel("Altamont Court Hotel", "1-3 Altamont Terrace", "5 Swimming pools", "Kingston 5", "Kinston", new LatLng(-78.3665,18.27204)),
    		new Hotel("Hotel Four Seasons", "18 Ruthven Road", "Kalooki every Tuesday", "Kingston 10", "Kingston", new LatLng(-77.9041,18.3639)),
    		new Hotel("Seaview Villa","Seaview District, Southfield", "Single and Double Occupany available", "Southfield",	"St. Elizabeth", new LatLng(-77.163,18.4268)),
    		new Hotel("Honey Comb",	"Silver Sands Estate", "Complimentary Continental Breakfast", "Duncans", "Trelawney", new LatLng(-77.9114, 18.46007))
    };
	
	final Attraction[] featuredAttractions = {
		    		new Attraction("Xayamaca", "fun","Montego Bay", "Mr. Paul Hastings", "Dalton Hastings", "Howard Cooke Blvd, Freeport", "Montego Bay", "St. James", new String[]{"844-9935"}, new String[]{"Plant Centre", "Complex Grounds", "Gift Shop", "Juice Bar"}),
		    		new Attraction("White River Valley", "boring", "Ocho Rios", "Daniel Melville", "Vaneka McKenzie", "Cascade", "Endevour", "St. Mary", new String[]{"974-2018","382-6907"}, new String[]{"River Tubing", "Horseback Riding", "Kayaking"} ),
		    		new Attraction("Caymanas Track Ltd.", "Caymanas Park is Jamaica's only race track.", "Kingston", "Mr. Michael Sibbles"," Mr. Michael Sibbles", "Caymanas Park Complex", "Gregory Park P.O.", "St. Catherine", new String[]{"988-2523", "988-2524", "988-2525", "988-2526"}, new String[]{"Horse Racing", "Children Entertainment", "Dining"}),
		    		new Attraction("KOOL RUNNINGS WATER PARK", "Delightful Negril vacation spot", "Negril",	"POINCIANA RESORTS Ltd", "MRS. NEKEISHA MYRIE-KOZER", "NORMAN MANLEY BLVD", null, null, new String[]{"957-5400", "957-5620"}, new String[]{"WATER SLIDES", "GO KARTS", "DINING", "CONFERENCE", "PAINT BALL", "RAFTING"})
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
		nearMeButton = (Button)rootView.findViewById(R.id.nearMe);
		
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
				myIntent.putExtra("type", "hotel");
			    startActivity(myIntent);
			}
			
		});
		
		attractionButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent myIntent = new Intent();
				myIntent.setClass(getActivity(), FeaturedFragment.class);
				myIntent.putExtra("type", "attraction");
			    startActivity(myIntent);
			}
			
		});
		
//		nearMeButton.setOnClickListener(new OnClickListener(){
//			@Override
//			public void onClick(View arg0) {
//				// TODO Auto-generated method stub
//				Intent myIntent = new Intent();
//				myIntent.setClass(getActivity(), GoogleMapActivity.class);
//				startActivity(myIntent);
//			}
//			
//		});
		
		return rootView;
	}

}
