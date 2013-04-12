package com.example.hotellocator;

import com.google.android.gms.maps.model.LatLng;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;

public class DetailsFragment extends Activity {

	String name,type;
	TextView featureName;
	TextView description;
	TextView address;
	TextView themes;
	TextView rating;
	Button mapButton;
	RatingBar rbar;
	int position = -1;
	
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
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_details);
		
		
		type = getIntent().getStringExtra("type");
		name = getIntent().getStringExtra("name");
		if(type.equals("hotel")){
			for(int i=0;i<hotels.length;i++)
			{
				if(hotels[i].getHotelName().equals(name))
				{
					position=i;
					break;
				}
			}
		}
		else{
			System.out.println(type);
			for(int i=0;i<attractions.length;i++)
			{
				if(attractions[i].getName().equals(name))
				{
					position=i;
					break;
				}
			}
		}
		
		if(position>-1)
		{
			featureName = (TextView)findViewById(R.id.name);
			description = (TextView)findViewById(R.id.hotelDescription);
			address = (TextView)findViewById(R.id.hotelAddress);
			rating = (TextView)findViewById(R.id.hotelRating);
			rbar = (RatingBar) findViewById(R.id.ratingBar1);
			themes = (TextView)findViewById(R.id.themes);
			
			if(type.equals("hotel"))
			{
				featureName.setText(hotels[position].getHotelName());
				description.setText(hotels[position].getDescription());
				address.setText(hotels[position].getAddress());
				rating.setText(Integer.toString(hotels[position].getHotelRating()) + " stars");
				rbar.setRating(hotels[position].getAverageUserRating());
			}
			else
				if(type.equals("attraction")){
					featureName.setText(attractions[position].getName());
					description.setText(attractions[position].getDescription());
					address.setText(attractions[position].getAddress());
					String s = "";
					if(attractions[position].getThemes()!=null){
	    			for(int i=0;i<attractions[position].getThemes().length;i++)
	    			{
	    				s = s + attractions[position].getThemes()[i] + "\n";
	    			}
	    			themes.setText(s);}
					rbar.setRating(0);
			}}
			
			mapButton = (Button)findViewById(R.id.button1);
			addListenerOnRatingBar(position);
			
			//rbar initially user rating
			
		mapButton = (Button)findViewById(R.id.mapButton);
		mapButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				//send LatLng at hotel
				Intent myIntent = new Intent(DetailsFragment.this,
						GoogleMapActivity.class).putExtra("latlng", hotels[position].getHotelCoordinates());
			    startActivity(myIntent);	
			}
		});
//		mapButton.setOnClickListener(new OnClickListener(){
//
//
//			@Override
//			public void onClick(View arg0) {
//				// TODO Auto-generated method stub
//				//send LatLng at hotel
//				Intent myIntent = new Intent(DetailsFragment.this,
//						GoogleMapFragment.class).putExtra("latlng", hotels[position].getHotelCoordinates());
//			    startActivity(myIntent);
//				
//			}
//			
//		});
		}
	
	
	public void addListenerOnRatingBar(final int position) {
		 
		//if rating value is changed,
		//display the current rating value in the result (textview) automatically
		rbar.setOnRatingBarChangeListener(new OnRatingBarChangeListener() {
			public void onRatingChanged(RatingBar ratingBar, float rating,
				boolean fromUser) {
				
				Toast.makeText(DetailsFragment.this,
						"Your feedback is appreciated",
							Toast.LENGTH_SHORT).show();
				float avgRating = ((ratingBar.getRating() + (hotels[position].getAverageUserRating() * hotels[position].getNumUserRatings()))/(hotels[position].getNumUserRatings()+1));
				hotels[position].setAverageUserRating(avgRating);
			}
		});
	  }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.details, menu);
		return true;
	}

}
