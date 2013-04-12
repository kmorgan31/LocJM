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
	private String community;
	private String parish;
	private LatLng hotelCoordinates;
	private float averageUserRating;
	private int numUserRatings;
	
	public Hotel(String hName, String hAddress, String hDesc, String hCommunity, String hParish, LatLng hCoords){
		hotelName = hName;
		address = hAddress;
		description = hDesc;
		community = hCommunity;
		parish = hParish;
		hotelCoordinates = hCoords;
		averageUserRating = 0;
		numUserRatings=0;
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

	public String getCommunity() {
		return community;
	}

	public void setCommunity(String community) {
		this.community = community;
	}

	public String getParish() {
		return parish;
	}

	public void setParish(String parish) {
		this.parish = parish;
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

	public int getNumUserRatings() {
		return numUserRatings;
	}

	public void setNumUserRatings(int numUserRatings) {
		this.numUserRatings = numUserRatings;
	}

}
