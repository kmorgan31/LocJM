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

	TextView hotelName;
	TextView hotelDescription;
	TextView hotelAddress;
	TextView hotelRating;
	Button mapButton;
	RatingBar rbar;
	
	final Hotel[] hotels = {
			new Hotel("Pegasus", "7 Knutsford Boulevard", "Lorem Ipsum is simply dummy text of the printing and typesetting industry.", 1000, new LatLng(25.002856, -76.795659), 5),
    		new Hotel("Hilton", "7 Hibiscus Drive", "Lorem ipsum", 1001, new LatLng(18.002856, -76.795659), 4),
    		new Hotel("Four Seasons", "2A Shalimar Close", "Eureka", 1002, new LatLng(28.002856, -76.795659), 6),
    		new Hotel("Holiday Inn", "83 Lore Lane", "Exciting", 1003, new LatLng(18.002856, 76.795659), 3),
    		new Hotel("Grande Palladium", "3 Old Way", "Boring", 1004, new LatLng(18.002856, 45.795659), 2),
    		new Hotel("Riu", "Ocho Rios", "nice food", 1005, new LatLng(14.002856, -7.795659), 4)
    };
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_details);
		
		
		int position = 0;
		final String name = getIntent().getStringExtra("hotel name");
		for(int i=0;i<hotels.length;i++)
		{
			if(hotels[i].getHotelName().equals(name))
			{
				position=i;
				break;
			}
		}
		if(position>-1)
		{
			hotelName = (TextView)findViewById(R.id.hotelName);
			hotelDescription = (TextView)findViewById(R.id.hotelDescription);
			hotelAddress = (TextView)findViewById(R.id.hotelAddress);
			hotelRating = (TextView)findViewById(R.id.hotelRating);
			rbar = (RatingBar) findViewById(R.id.ratingBar1);
			
			hotelName.setText(hotels[position].getHotelName());
			hotelDescription.setText(hotels[position].getDescription());
			hotelAddress.setText(hotels[position].getAddress());
			hotelRating.setText(Integer.toString(hotels[position].getHotelRating()) + "stars");
			rbar.setRating(hotels[position].getAverageUserRating());
			
			mapButton = (Button)findViewById(R.id.button1);
			addListenerOnRatingBar(position);
			
			//rbar initially user rating
			
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
