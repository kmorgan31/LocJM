package com.example.hotellocator;

import java.io.Serializable;

import com.google.android.gms.maps.model.LatLng;

public class Hotel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String hotelName;
	private String address;
	private String description;
	private int id;
	private LatLng hotelCoordinates;
	private float averageUserRating;
	private int numUserRatings;
	private int hotelRating;
	
	public Hotel(String hName, String hAddress, String hDesc, int hId, LatLng hCoords, int hRating){
		hotelName = hName;
		address = hAddress;
		description = hDesc;
		id = hId;
		hotelCoordinates = hCoords;
		averageUserRating = 0;
		numUserRatings=0;
		hotelRating = hRating;
	}

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LatLng getHotelCoordinates() {
		return hotelCoordinates;
	}

	public void setHotelCoordinates(LatLng hotelCoordinates) {
		this.hotelCoordinates = hotelCoordinates;
	}

	public float getAverageUserRating() {
		return averageUserRating;
	}

	public void setAverageUserRating(float f) {
		this.averageUserRating = f;
	}

	public int getHotelRating() {
		return hotelRating;
	}

	public void setHotelRating(int hotelRating) {
		this.hotelRating = hotelRating;
	}

	public int getNumUserRatings() {
		return numUserRatings;
	}

	public void setNumUserRatings(int numUserRatings) {
		this.numUserRatings = numUserRatings;
	}

}
