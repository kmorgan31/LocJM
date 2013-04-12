package com.example.hotellocator;
<<<<<<< HEAD
//
////import java.util.List;
//
//import java.io.IOException;
//import java.util.List;
=======
>>>>>>> 1840488261e4d86313a4e47a6b3738eb8640353c

import java.io.IOException;
import java.util.List;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.Geocoder;
import android.os.Bundle;
import android.widget.Toast;

public class GoogleMapActivity extends Activity {

	final Hotel[] hotels = {
			new Hotel("Pegasus", "7 Knutsford Boulevard", "Lorem Ipsum is simply dummy text of the printing and typesetting industry.", "Kingston", "Kingston", new LatLng(25.002856, -76.795659)),
			new Hotel("Akwaaba", "Silver Sands Estates, Duncans","Nice scenery", "Duncans", "Trelawney", new LatLng(-78.3671, 18.26805)),
    		new Hotel("Alamanda Inn", "39 Salem Beach, P.O. Box 65", "Lovely Beaches", "Runaway Bay", "St. Ann", new LatLng(-78.3669,18.27206)),
    		new Hotel("Alfred Ocean Place",	"Norman Manley Blvd. P.O. Box", "Luscious Palm Trees", "Negril", "Westmoreland", new LatLng(-78.3669, 18.27165)),
    		new Hotel("Alhambra Inn", "1 Tucker Avenue", "Close to city", "Kingston 3", "Kingston", new LatLng(-78.3666,18.26627)),
    		new Hotel("All Seasons Beach Resort", "Barrett  Hall, Greenwood, Lit", "Nice sandy beaches", "Greenwood", "St. James", new LatLng(-78.3666,18.26356)),
    		new Hotel("Almond Tree", "Arawak P.O. Box 332", "Lovely weather", "Mammee Bay", "St. Ann", new LatLng(-78.3665,18.26398)),
    		new Hotel("Altamont Court Hotel", "1-3 Altamont Terrace", "5 Swimming pools", "Kingston 5", "Kinston", new LatLng(-78.3665,18.27204)),
    		new Hotel("Hotel Four Seasons", "18 Ruthven Road", "Kalooki every Tuesday", "Kingston 10", "Kingston", new LatLng(-77.9041,18.3639)),
    		new Hotel("Hotel Riu", "Tropical Bay Bloody Bay Negril", "Caters to Spanish speakers as well", "Negril", "Hanover", new LatLng(-77.8975,18.47607)),
    		new Hotel("Blue Harbour","P.O. Box 50", "Best Jerk Spot within 5 minutes", "Port Maria","St. Mary",new LatLng(-78.354,18.24547)),
    		new Hotel("Seaview Villa","Seaview District, Southfield", "Single and Double Occupany available", "Southfield",	"St. Elizabeth", new LatLng(-77.163,18.4268)),
    		new Hotel("Tia Maria Villa", "136 Silver Sands Estate, P.O.", "Dancing every other Wednesday", "Duncans", "Trelawney", new LatLng(-77.0723, 18.41486)),
    		new Hotel("Honey Comb",	"Silver Sands Estate", "Complimentary Continental Breakfast", "Duncans", "Trelawney", new LatLng(-77.9114, 18.46007))
    };
	LatLng myPos;
	private GoogleMap map;
	private LocationManager locMan;
	boolean locChange;
	Location curLoc, myLoc;
	List<Address> address;
	LatLng result = null;
	String search = null;


	GoogleMapOptions option = new GoogleMapOptions();
	Marker[] places = new Marker[hotels.length];

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);
		myLoc = getBestLocation();
		myPos = new LatLng(myLoc.getLatitude(), myLoc.getLongitude());
		Bundle b = getIntent().getExtras();
		try{
		if(b.containsKey("latlng")){
		result = new LatLng((double) b.getDoubleArray("latlng")[0],(double) b.getDoubleArray("latlng")[1]);
			}
		}catch(NullPointerException e){
			e.printStackTrace();
		}
			if (map == null) {
				map = ((MapFragment) getFragmentManager().findFragmentById(
						R.id.map)).getMap();
				map.setMyLocationEnabled(true);
				map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
			}
		
		if (result!=null)
		{
			map.moveCamera(CameraUpdateFactory.newLatLngZoom(result, 16));
			option.zoomControlsEnabled(true);
		}
		else{
			map.moveCamera(CameraUpdateFactory.newLatLngZoom(myPos, 16));
			option.zoomControlsEnabled(true);
		}
		for(int i=0;i<hotels.length;i++)
		{
		places[i] = map.addMarker(new MarkerOptions().position(hotels[i].getHotelCoordinates()).title(hotels[i].getHotelName())
				.snippet(hotels[i].getDescription())
				.icon(BitmapDescriptorFactory
				.fromResource(R.drawable.hotel5stars)));
		}
		map.setOnMarkerClickListener(new OnMarkerClickListener() {
			@Override
			public boolean onMarkerClick(Marker arg0) {
				// TODO Auto-generated method stub
				// go to details page
				 try {
					 Bundle b = new Bundle();
					 b.putString("name", arg0.getTitle().toString());
					 b.putString("type", "hotel");
					 Intent chosenLayout = new Intent(GoogleMapActivity.this,DetailsFragment.class).putExtras(b);
				 startActivity(chosenLayout);
				 } catch (NullPointerException e) {
				 e.printStackTrace();
				 }
				return false;
			}
		});
	}

	public GoogleMap getMap() {
		return map;
	}

	LocationListener gpsListener = new LocationListener() {
		public void onLocationChanged(Location loc) {
			if (curLoc == null) {
				curLoc = loc;
				locChange = true;
			} else if (curLoc.getLatitude() == loc.getLatitude()
					&& curLoc.getLongitude() == loc.getLongitude()) {
				locChange = false;
				return;
			} else
				locChange = true;

			curLoc = loc;

			if (locChange)
				locMan.removeUpdates(gpsListener);

		}

		@Override
		public void onProviderDisabled(String provider) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onProviderEnabled(String provider) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
			// TODO Auto-generated method stub

		}
	};

	private Location getBestLocation() {
		Location gpslocation = null;
		Location networkLocation = null;

		if (locMan == null)
			locMan = (LocationManager) getApplicationContext()
					.getSystemService(Context.LOCATION_SERVICE);
		try {
			if (locMan.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
				locMan.requestLocationUpdates(LocationManager.GPS_PROVIDER,
						100, 1, gpsListener);
				gpslocation = locMan
						.getLastKnownLocation(LocationManager.GPS_PROVIDER);
			}
			if (locMan.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
				locMan.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
						100, 1, gpsListener);
				networkLocation = locMan
						.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		if (gpslocation == null && networkLocation == null)
			return null;

		if (gpslocation != null && networkLocation != null) {
			if (gpslocation.getTime() < networkLocation.getTime())
				return networkLocation;
			else
				return gpslocation;
		}
		if (gpslocation == null) {
			return networkLocation;
		}
		if (networkLocation == null) {
			return gpslocation;
		}
		return null;
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}
}
<<<<<<< HEAD

//
//import com.google.android.gms.maps.GoogleMap;
//import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
//import com.google.android.gms.maps.GoogleMapOptions;
//import com.google.android.gms.maps.MapFragment;
//import com.google.android.gms.maps.model.LatLng;
//import com.google.android.gms.maps.CameraUpdateFactory;
//import com.google.android.gms.maps.model.BitmapDescriptorFactory;
//import com.google.android.gms.maps.model.Marker;
//import com.google.android.gms.maps.model.MarkerOptions;
//import android.app.Activity;
//import android.content.Context;
//import android.content.Intent;
//import android.location.Address;
//import android.location.Location;
//import android.location.LocationListener;
//import android.location.LocationManager;
//import android.location.Geocoder;
//import android.os.Bundle;
//import android.widget.Toast;
//
//public class GoogleMapActivity extends Activity {
//
//	static LatLng origin = new LatLng(18.002856, -76.795659);
//	LatLng myPos;
//	private GoogleMap map;
//	private LocationManager locMan;
//	boolean locChange;
//	Location curLoc, myLoc;
//	List<Address> address;
//	LatLng result;
//	String search;
//	
//	GoogleMapOptions option = new GoogleMapOptions();
//	Marker location;
//
//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_map);
//		myLoc = getBestLocation();
//		try {
//			Bundle b = getIntent().getExtras();
//			if (b != null) {
//				search = b.getString("search");
//				searchMap(search);
//			}
//			}catch(NullPointerException e){
//				e.printStackTrace();
//			}finally {
//			if (map == null) {
//				map = ((MapFragment) getFragmentManager().findFragmentById(
//						R.id.map)).getMap();
//				map.setMyLocationEnabled(true);
//				map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
//				myPos = new LatLng(myLoc.getLatitude(), myLoc.getLongitude());
//				map.moveCamera(CameraUpdateFactory.newLatLngZoom(myPos, 16));
//				option.zoomControlsEnabled(true);
//			}
//		}
//		map.setOnMarkerClickListener(new OnMarkerClickListener() {
//			@Override
//			public boolean onMarkerClick(Marker arg0) {
//				// TODO Auto-generated method stub
//				// go to details page
//				// try {
//				// String s = getIntent().getStringExtra("delivery info");
//				// Intent chosenLayout = new Intent(GoogleMapActivity.this,
//				// LoginActivity.class).putExtra("delivery info", s);
//				// startActivity(chosenLayout);
//				// } catch (NullPointerException e) {
//				// e.printStackTrace();
//				// }
//				return false;
//			}
//		});
//	}
//
//	public GoogleMap getMap() {
//		return map;
//	}
//
//	public void searchMap(String addr) {
//		Geocoder coder = new Geocoder(this);
//
//		try {
//			address = coder.getFromLocationName(addr, 5);
//			Address location = address.get(0);
//			result = new LatLng((location.getLatitude() * 1E6),
//					(location.getLongitude() * 1E6));
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} finally {
//			if (address != null) {
//				map.addMarker(new MarkerOptions().position(result));
//			} else {
//				Toast.makeText(GoogleMapActivity.this, "Location not found",
//						Toast.LENGTH_SHORT);
//			}
//
//
//	LocationListener gpsListener = new LocationListener() {
//		public void onLocationChanged(Location loc) {
//			if (curLoc == null) {
//				curLoc = loc;
//				locChange = true;
//			} else if (curLoc.getLatitude() == loc.getLatitude()
//					&& curLoc.getLongitude() == loc.getLongitude()) {
//				locChange = false;
//				return;
//			} else
//				locChange = true;
//
//			curLoc = loc;
//
//			if (locChange)
//				locMan.removeUpdates(gpsListener);
//
//		}
//
//		@Override
//		public void onProviderDisabled(String provider) {
//			// TODO Auto-generated method stub
//
//		}
//
//		@Override
//		public void onProviderEnabled(String provider) {
//			// TODO Auto-generated method stub
//
//		}
//
//		@Override
//		public void onStatusChanged(String provider, int status, Bundle extras) {
//			// TODO Auto-generated method stub
//
//		}
//	};
//
//	private Location getBestLocation() {
//		Location gpslocation = null;
//		Location networkLocation = null;
//
//		if (locMan == null)
//			locMan = (LocationManager) getApplicationContext()
//					.getSystemService(Context.LOCATION_SERVICE);
//		try {
//			if (locMan.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
//				locMan.requestLocationUpdates(LocationManager.GPS_PROVIDER,
//						100, 1, gpsListener);
//				gpslocation = locMan
//						.getLastKnownLocation(LocationManager.GPS_PROVIDER);
//			}
//			if (locMan.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
//				locMan.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
//						100, 1, gpsListener);
//				networkLocation = locMan
//						.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
//			}
//		} catch (IllegalArgumentException e) {
//			e.printStackTrace();
//		}
//		if (gpslocation == null && networkLocation == null)
//			return null;
//
//		if (gpslocation != null && networkLocation != null) {
//			if (gpslocation.getTime() < networkLocation.getTime())
//				return networkLocation;
//			else
//				return gpslocation;
//		}
//		if (gpslocation == null) {
//			return networkLocation;
//		}
//		if (networkLocation == null) {
//			return gpslocation;
//		}
//		return null;
//	}
//
//	@Override
//	protected void onPause() {
//		// TODO Auto-generated method stub
//		super.onPause();
//		finish();
//	}
//}
=======
>>>>>>> 1840488261e4d86313a4e47a6b3738eb8640353c
