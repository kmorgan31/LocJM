package com.example.hotellocator;

import java.util.ArrayList;

import com.google.android.gms.maps.model.LatLng;

import android.net.Uri;
import android.os.Bundle;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.Toast;
import android.widget.ExpandableListView.OnChildClickListener;

public class Favourites extends Fragment {

	final Hotel[] favouriteHotels = {
			new Hotel("Pegasus", "7 Knutsford Boulevard", "Lorem Ipsum is simply dummy text of the printing and typesetting industry.", "Kingston", "Kingston", new LatLng(25.002856, -76.795659)),
			new Hotel("Akwaaba", "Silver Sands Estates, Duncans","Nice scenery", "Duncans", "Trelawney", new LatLng(-78.3671, 18.26805)),
    		new Hotel("All Seasons Beach Resort", "Barrett  Hall, Greenwood, Lit", "Nice sandy beaches", "Greenwood", "St. James", new LatLng(-78.3666,18.26356)),
    		new Hotel("Almond Tree", "Arawak P.O. Box 332", "Lovely weather", "Mammee Bay", "St. Ann", new LatLng(-78.3665,18.26398)),
    		new Hotel("Hotel Four Seasons", "18 Ruthven Road", "Kalooki every Tuesday", "Kingston 10", "Kingston", new LatLng(-77.9041,18.3639)),
    		new Hotel("Hotel Riu", "Tropical Bay Bloody Bay Negril", "Caters to Spanish speakers as well", "Negril", "Hanover", new LatLng(-77.8975,18.47607)),
    		new Hotel("Blue Harbour","P.O. Box 50", "Best Jerk Spot within 5 minutes", "Port Maria","St. Mary",new LatLng(-78.354,18.24547)),
    		new Hotel("Tia Maria Villa", "136 Silver Sands Estate, P.O.", "Dancing every other Wednesday", "Duncans", "Trelawney", new LatLng(-77.0723, 18.41486)),
    		new Hotel("Honey Comb",	"Silver Sands Estate", "Complimentary Continental Breakfast", "Duncans", "Trelawney", new LatLng(-77.9114, 18.46007))
    };
	
	final Attraction[] favouriteAttractions = {
			new Attraction("Bob Marley Museum", "Historic", "Kingston", "Jacqueline Stewart", "Jacqueline Stewart", "56 Hope Road", "Kingston 6", "St. Andrew", new String[]{"927-9152"}, new String[]{"Tour of the former home of Bob Marley"}),
		    new Attraction("KOOL RUNNINGS WATER PARK", "Delightful Negril vacation spot", "Negril",	"POINCIANA RESORTS Ltd", "MRS. NEKEISHA MYRIE-KOZER", "NORMAN MANLEY BLVD", null, null, new String[]{"957-5400", "957-5620"}, new String[]{"WATER SLIDES", "GO KARTS", "DINING", "CONFERENCE", "PAINT BALL", "RAFTING"}),
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
				String type = determineType(name, favouriteHotels, favouriteAttractions);
				if (childPosition==0) {
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
				else{
					if(childPosition==1 && type.equals("attraction") ){
					int position = -1;
					Toast.makeText(getActivity(),
							name,
								Toast.LENGTH_SHORT).show();
					for(int i=0;i<favouriteAttractions.length;i++)
					{
						if(favouriteAttractions[i].getName().equals(name))
						{
							position = i;
							break;
						}
					}	
				
					if(position>-1){
						Intent callIntent = new Intent(Intent.ACTION_CALL);
						callIntent.setData(Uri.parse("tel:"+ favouriteAttractions[position].getContact()[0])); 
						startActivity(callIntent);
					}
				}
				else{
					if(childPosition==1 && type.equals("hotel")){
							Toast.makeText(getActivity(),
									"No contact number",
										Toast.LENGTH_SHORT).show();
							}
						}
					}
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
