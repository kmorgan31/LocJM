package com.example.hotellocator;

import java.util.ArrayList;

import com.google.android.gms.maps.model.LatLng;
import android.os.Bundle;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;

public class Favourites extends Fragment {

	final Hotel[] favouriteHotels = {
			new Hotel("Pegasus", "7 Knutsford Boulevard", "Lorem Ipsum is simply dummy text of the printing and typesetting industry.", 1000, new LatLng(25.002856, -76.795659), 5),
	    		new Hotel("Grande Palladium", "3 Old Way", "Boring", 1004, new LatLng(18.002856, 45.795659), 2),
	};
	
	final Attraction[] favouriteAttractions = {
    		new Attraction("White River Valley", "boring", "Ocho Rios", "Daniel Melville", "Vaneka McKenzie", "Cascade", "Endevour", "St. Mary", new String[]{"974-2018","382-6907"}, new String[]{"River Tubing", "Horseback Riding", "Kayaking"} ),
    };
	
	ArrayList<String> favourites = new ArrayList<String>();
	ExpandableListView exv;


	// HorizontalScrollView hv = (HorizontalScrollView)
	// findViewById(R.id.SlideMap);
	// int maxScrollX =
	// hv.getChildAt(0).getMeasuredWidth()-hv.getMeasuredWidth();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	          Bundle savedInstanceState) {
	  	
	    View rootView = inflater.inflate(R.layout.activity_favourites, container, false);

		exv = (ExpandableListView) rootView.findViewById(R.id.tabListView);
		exv.setAdapter(new MyAdapter(getActivity(), favouriteHotels, favouriteAttractions));
//		exv.setOnGroupClickListener(new OnGroupClickListener() {
//			@Override
//			public boolean onGroupClick(ExpandableListView arg0, View arg1,
//					int groupPosition, long arg3) {
//				// TODO Auto-generated method stub
//				// change mapview
//				// show route from current location to destination
//				String itemClicked = MyAdapter.parentList[groupPosition];
//				Toast.makeText(Favourites.this,
//						itemClicked + " is clicked", Toast.LENGTH_SHORT).show();
//				String deliveryList = "";
//				for (int i = 0; i < MyAdapter.childList[groupPosition].length; i++) {
//					deliveryList += MyAdapter.childList[groupPosition][i]
//							+ ", ";
//					deliveryList.substring(0, deliveryList.length());
//				}
//			}
//		});

		exv.setOnChildClickListener(new OnChildClickListener() {
			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {
				// TODO Auto-generated method stub
				// call number
				String name = MyAdapter.parentList[groupPosition];
				if (childPosition==0) {
					String type = determineType(name, favouriteHotels, favouriteAttractions);
					Intent myIntent = new Intent();
					myIntent.setClass(getActivity(), DetailsFragment.class);
					if(type.equals("hotel")){
						myIntent.putExtra("name", name);
						myIntent.putExtra("type", type);}
					else
						if(type.equals("attraction")){
							myIntent.putExtra("name", name);
							myIntent.putExtra("type", type);
						}
				    startActivity(myIntent);
				}
				else{if(childPosition==1){
					//call hotel/attraction
				}}
				return false;
			}
			

		});
		return rootView;
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		// TODO Auto-generated method stub
		super.onConfigurationChanged(newConfig);
	}
	
	public String determineType(String name, Hotel[] favouriteHotels, Attraction[] favouriteAttractions){
		String type = null;
		boolean typeFound=false;
		for(int i=0;i<favouriteHotels.length;i++)
		{
			if(favouriteHotels[i].getHotelName().equals(name))
			{
				type="hotel";
				typeFound=true;
				break;
			}
		}
		if(typeFound==false){
		for(int i=0;i<favouriteAttractions.length;i++)
		{
			if(favouriteAttractions[i].getName().equals(name))
			{
				type="attraction";
				break;
			}
		}}
		return type;
	}

}
