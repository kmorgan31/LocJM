package com.example.hotellocator;

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

	static LatLng origin = new LatLng(18.002856, -76.795659);
	LatLng myPos;
	private GoogleMap map;
	private LocationManager locMan;
	boolean locChange;
	Location curLoc, myLoc;
	List<Address> address;
	LatLng result = null;


	GoogleMapOptions option = new GoogleMapOptions();
	Marker location;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);
		myLoc = getBestLocation();
		myPos = new LatLng(myLoc.getLatitude(), myLoc.getLongitude());
		Bundle b = getIntent().getExtras();
		try{
			if (b.getDoubleArray("latlng") != null)
			{
				result = new LatLng((double) b.getDoubleArray("latlng")[0],(double) b.getDoubleArray("latlng")[1]);
			}
		}catch(NullPointerException e){
			e.printStackTrace();
		}
			try{
				if (b.getString("search") != null) {
				searchMap(b.getString("search"));
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
		map.setOnMarkerClickListener(new OnMarkerClickListener() {
			@Override
			public boolean onMarkerClick(Marker arg0) {
				// TODO Auto-generated method stub
				// go to details page
				// try {
				// String s = getIntent().getStringExtra("delivery info");
				// Intent chosenLayout = new Intent(GoogleMapActivity.this,
				// LoginActivity.class).putExtra("delivery info", s);
				// startActivity(chosenLayout);
				// } catch (NullPointerException e) {
				// e.printStackTrace();
				// }
				return false;
			}
		});
	}

	public GoogleMap getMap() {
		return map;
	}

	public void searchMap(String addr) {
		Geocoder coder = new Geocoder(this);

		try {
			address = coder.getFromLocationName(addr, 5);
			Address location = address.get(0);
			result = new LatLng((location.getLatitude() * 1E6),
					(location.getLongitude() * 1E6));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (address != null) {
				map.addMarker(new MarkerOptions().position(result));
			} else {
				Toast.makeText(GoogleMapActivity.this, "Location not found",
						Toast.LENGTH_SHORT);
			}
		}
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
