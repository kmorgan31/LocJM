package com.example.hotellocator;

//import java.util.List;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class GoogleMapFragment extends SupportMapFragment {

	static LatLng origin = new LatLng(18.002856, -76.795659);
	LatLng myPos;
	private GoogleMap map;
	private LocationManager locMan;
	boolean locChange;
	Location curLoc, myLoc;

	GoogleMapOptions option = new GoogleMapOptions();
	Marker location;

	public View onCreateView(LayoutInflater arg0, ViewGroup arg1, Bundle arg2) {
		// TODO Auto-generated method stub
		View rootView = arg0.inflate(R.layout.activity_map, arg1, false);
		if (map == null) {
			map = ((SupportMapFragment)(getFragmentManager().findFragmentById(R.id.map))).getMap();
			map.setMyLocationEnabled(true);
			map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
			myLoc = getBestLocation();
			myPos = new LatLng(myLoc.getLatitude(), myLoc.getLongitude());
			map.moveCamera(CameraUpdateFactory.newLatLngZoom(myPos, 16));
			option.zoomControlsEnabled(true);
		}
		if (map != null) {
			location = map.addMarker(new MarkerOptions().position(origin)
					.title("Delivery")
					// .snippet("Kiel is cool")
					.icon(BitmapDescriptorFactory
							.fromResource(R.drawable.ic_launcher)));
		}
		map.setOnMarkerClickListener(new OnMarkerClickListener() {
			@Override
			public boolean onMarkerClick(Marker arg0) {
				// TODO Auto-generated method stub
				// go to details page
//				try {
//					String s = getIntent().getStringExtra("delivery info");
//					Intent chosenLayout = new Intent(GoogleMapActivity.this,
//							LoginActivity.class).putExtra("delivery info", s);
//					startActivity(chosenLayout);
//				} catch (NullPointerException e) {
//					e.printStackTrace();
//				}
				return false;
			}
		});
		return rootView;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//double[] d = getIntent().getDoubleArrayExtra("co-ordinates");
		//LatLng LOCATION = new LatLng(d[0],d[1]);
//		if (map == null) {
//			map = ((SupportMapFragment)(getFragmentManager().findFragmentById(R.id.map))).getMap();
//			map.setMyLocationEnabled(true);
//			map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
//			myLoc = getBestLocation();
//			myPos = new LatLng(myLoc.getLatitude(), myLoc.getLongitude());
//			map.moveCamera(CameraUpdateFactory.newLatLngZoom(myPos, 16));
//			option.zoomControlsEnabled(true);
//		}
//		if (map != null) {
//			location = map.addMarker(new MarkerOptions().position(origin)
//					.title("Delivery")
//					// .snippet("Kiel is cool")
//					.icon(BitmapDescriptorFactory
//							.fromResource(R.drawable.ic_launcher)));
//		}
//		map.setOnMarkerClickListener(new OnMarkerClickListener() {
//			@Override
//			public boolean onMarkerClick(Marker arg0) {
//				// TODO Auto-generated method stub
//				// go to details page
////				try {
////					String s = getIntent().getStringExtra("delivery info");
////					Intent chosenLayout = new Intent(GoogleMapActivity.this,
////							LoginActivity.class).putExtra("delivery info", s);
////					startActivity(chosenLayout);
////				} catch (NullPointerException e) {
////					e.printStackTrace();
////				}
//				return false;
//			}
//		});
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
			locMan = (LocationManager) getActivity()
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
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}
}